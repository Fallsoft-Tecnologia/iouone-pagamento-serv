package br.com.iouone.pagamento.services.impl;

import br.com.iouone.pagamento.requests.PixRequest;
import br.com.iouone.pagamento.requests.pix.Item;
import br.com.iouone.pagamento.requests.pix.Payment;
import br.com.iouone.pagamento.requests.pix.Pix;
import br.com.iouone.pagamento.client.PagarmeClient;
import br.com.iouone.pagamento.services.PixService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Collections;

@Service
public class PixServiceImpl implements PixService {

    private final PagarmeClient pagarmeClient;

    @Value("${pagarme.api.username}")
    private String username;

    @Value("${pagarme.api.password}")
    private String password;

    @Autowired
    public PixServiceImpl(PagarmeClient pagarmeClient) {
        this.pagarmeClient = pagarmeClient;
    }

    private String getAuthorizationHeader() {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    @Override
    public String criarTransacaoPix(PixRequest pixRequest) {
        String authorizationHeader = getAuthorizationHeader();
        return pagarmeClient.criarTransacaoPix(authorizationHeader, pixRequest).getBody();
    }

    @Override
    public String obterPedido(String orderId) {
        String authorizationHeader = getAuthorizationHeader();
        String pedidoJson = pagarmeClient.obterPedido(authorizationHeader, orderId).getBody();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(pedidoJson);
            String createdAt = root.path("created_at").asText();

            System.out.println("Data de criação do pedido: " + createdAt);

            if (isAssinaturaExpirada(createdAt)) {
                System.out.println("A assinatura expirou. Gerando nova cobrança...");
                gerarNovaCobranca(orderId);
            } else {
                System.out.println("A assinatura ainda está ativa.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pedidoJson;
    }

    private boolean isAssinaturaExpirada(String createdAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        LocalDateTime dataCriacao = LocalDateTime.parse(createdAt, formatter);
        LocalDateTime agora = LocalDateTime.now(ZoneOffset.UTC);

        // Forçar expiração para teste
        /*
        if (true) {
            System.out.println("Simulação de expiração de assinatura.");
            return true;
        }
        */

        long diasPassados = java.time.temporal.ChronoUnit.DAYS.between(dataCriacao, agora);
        System.out.println("Dias desde a criação do pedido: " + diasPassados);

        return diasPassados > 31;
    }

    private void gerarNovaCobranca(String orderId) {
        try {
            String authorizationHeader = getAuthorizationHeader();
            String pedidoJson = pagarmeClient.obterPedido(authorizationHeader, orderId).getBody();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(pedidoJson);
            String customerId = root.path("customer").path("id").asText();
            int amount = root.path("amount").asInt();
            String description = root.path("items").get(0).path("description").asText();

            PixRequest novaTransacaoPix = new PixRequest();

            Item item = new Item();
            item.setAmount(amount);
            item.setDescription(description);
            item.setQuantity(1);
            novaTransacaoPix.setItems(Collections.singletonList(item));

            novaTransacaoPix.setCustomer_id(customerId);

            Pix pixPayment = new Pix(3600);
            Payment payment = new Payment("pix", pixPayment);
            novaTransacaoPix.setPayments(Collections.singletonList(payment));

            String respostaPix = criarTransacaoPix(novaTransacaoPix);
            System.out.println("Nova cobrança gerada: " + respostaPix);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String obterStatusPedido(String orderId) {
        String authorizationHeader = getAuthorizationHeader();
        String pedidoJson = pagarmeClient.obterPedido(authorizationHeader, orderId).getBody();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(pedidoJson);
            
            String status = root.path("status").asText();
            System.out.println("Status do pedido: " + status);

            return status;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao processar o pedido", e);
        }
    }
}
