package br.com.iouone.pagamento.services.impl;

import br.com.iouone.pagamento.client.PessoaApiClient;
import br.com.iouone.pagamento.models.Assinatura;
import br.com.iouone.pagamento.models.ItemAssinatura;
import br.com.iouone.pagamento.models.MetodoPagamento;
import br.com.iouone.pagamento.models.Pagamento;
import br.com.iouone.pagamento.repositories.AssinaturaRepository;
import br.com.iouone.pagamento.repositories.MeioPagamentoRepository;
import br.com.iouone.pagamento.repositories.PagamentoRepository;
import br.com.iouone.pagamento.requests.AssinaturaRequest;
import br.com.iouone.pagamento.requests.BillingAddress;
import br.com.iouone.pagamento.requests.Card;
import br.com.iouone.pagamento.requests.PagamentoAssinaturaRequest;
import br.com.iouone.pagamento.responses.AssinaturaResponse;
import br.com.iouone.pagamento.responses.DadosEnderecoPessoaResponse;
import br.com.iouone.pagamento.services.AssinaturaService;
import br.com.iouone.pagamento.client.PagarmeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

@Service
public class AssinaturaServiceImpl implements AssinaturaService {

    private final PagarmeClient pagarmeClient;
    private final PessoaServiceImpl pessoaService;
    private final AssinaturaRepository assinaturaRepository;
    private final PagamentoRepository pagamentoRepository;
    private final MeioPagamentoRepository meioPagamentoRepository;

    @Value("${pagarme.api.username}")
    private String username;

    @Value("${pagarme.api.password}")
    private String password;


    @Autowired
    public AssinaturaServiceImpl(PagarmeClient pagarmeClient, PessoaServiceImpl pessoaService, AssinaturaRepository assinaturaRepository, PagamentoRepository pagamentoRepository, MeioPagamentoRepository meioPagamentoRepository) {
        this.pagarmeClient = pagarmeClient;
        this.pessoaService = pessoaService;
        this.assinaturaRepository = assinaturaRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.meioPagamentoRepository = meioPagamentoRepository;
    }

    private String getAuthorizationHeader() {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    @Override
    public AssinaturaResponse createAssinatura(AssinaturaRequest request) {
        String authorizationHeader = getAuthorizationHeader();
        ResponseEntity<AssinaturaResponse> response = pagarmeClient.criarAssinatura(authorizationHeader, request);
        return response.getBody();
    }

    public AssinaturaResponse createAssinaturav2(PagamentoAssinaturaRequest request, String fluxoId) {
        var dadosEndereco = pessoaService.getEnderecoPessoa(fluxoId);

        var dataValidade = separardataDeValidade(request.getDataValidade());
        var requestPagarme = getAssinaturaRequest(request, dataValidade, dadosEndereco);

        String authorizationHeader = getAuthorizationHeader();
        ResponseEntity<AssinaturaResponse> response = pagarmeClient.criarAssinatura(authorizationHeader, requestPagarme);

        if (response.getStatusCode().value() == 200) {
            if (!Objects.equals(response.getBody().getStatus(), "failed")) {
                var assinatura = assinaturaRepository.save(new Assinatura(dadosEndereco.getCustomerId(),
                        response.getBody().getId(), "month", LocalDateTime.now(),
                        response.getBody().getStatus(), new ItemAssinatura(1)));

                var meioPagamento = meioPagamentoRepository.findByMeioPagamento(meioPagamento(request.getFormaPagamento()));
                pagamentoRepository.save(new Pagamento(assinatura, response.getBody().getStatus(), LocalDateTime.now(), null, meioPagamento));
            }
        }


        return response.getBody();
    }

    private static AssinaturaRequest getAssinaturaRequest(PagamentoAssinaturaRequest request, String[] dataValidade, DadosEnderecoPessoaResponse dadosEndereco) {
        String mes = dataValidade[0];
        String ano = dataValidade[1];

        var endereco = new BillingAddress(dadosEndereco.getEndereco(), dadosEndereco.getEstado(),
                dadosEndereco.getPais(), dadosEndereco.getCidade(), dadosEndereco.getCep());

        var card = new Card(endereco, request.getNumeroCartao(), Integer.parseInt(mes), Integer.parseInt(ano), request.getNomeCartao());

        return new AssinaturaRequest(request.getFormaPagamento(), card, dadosEndereco.getCustomerId());

    }

    private static String[] separardataDeValidade(String dataValidade) {
        return dataValidade.split("/");
    }


    public String meioPagamento(String meioPagamento) {
        return switch (meioPagamento) {
            case "credito" -> MetodoPagamento.CREDIT_CARD.name();
            case "debito" -> MetodoPagamento.DEBIT_CARD.name();
            case "pix" -> MetodoPagamento.PIX.name();
            default -> "nao encontrado";
        };
    }
}
