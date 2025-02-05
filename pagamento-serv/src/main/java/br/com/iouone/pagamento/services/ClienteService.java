package br.com.iouone.pagamento.services;

import br.com.iouone.pagamento.client.PagarmeClient;
import br.com.iouone.pagamento.mapper.PessoaToCustomerMapper;
import br.com.iouone.pagamento.models.Customer;
import br.com.iouone.pagamento.requests.CustomerRequest;
import br.com.iouone.pagamento.requests.CustomerIdMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Base64;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private final PagarmeClient pagarmeClient;
    private final RabbitTemplate rabbitTemplate;

    @Value("${pagarme.api.username}")
    private String username;

    @Value("${pagarme.api.password}")
    private String password;

    public ClienteService(PagarmeClient pagarmeClient, RabbitTemplate rabbitTemplate) {
        this.pagarmeClient = pagarmeClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    private String getAuthorizationHeader() {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    public ResponseEntity<String> criarCliente(CustomerRequest customerRequest) {
        Customer customer = PessoaToCustomerMapper.toCustomer(customerRequest);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String customerJson = objectMapper.writeValueAsString(customer);
            logger.info("JSON do cliente a ser enviado para o Pagar.me: {}", customerJson);
        } catch (Exception e) {
            logger.error("Erro ao converter cliente para JSON", e);
        }

        String authorizationHeader = getAuthorizationHeader();
        ResponseEntity<Customer> response = pagarmeClient.criarCliente(authorizationHeader, customer);

        if (response.getStatusCode().is2xxSuccessful()) {
            Customer createdCustomer = response.getBody();
            if (createdCustomer != null) {
                CustomerIdMessageRequest message = new CustomerIdMessageRequest(customerRequest.getId(), createdCustomer.getId());
                logger.info("Enviando mensagem para a fila com PessoaId: {} e CustomerId: {}", customerRequest.getId(), createdCustomer.getId());
                rabbitTemplate.convertAndSend("receiving_customer_id_queue", message);

                return ResponseEntity.ok(createdCustomer.getId());
            } else {
                return ResponseEntity.status(500).body("Cliente criado, mas resposta está vazia.");
            }
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Falha ao criar cliente na API do Pagar.me: " + response.getBody());
        }
    }
}