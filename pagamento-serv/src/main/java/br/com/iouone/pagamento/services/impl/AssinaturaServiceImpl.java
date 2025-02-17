package br.com.iouone.pagamento.services.impl;

import br.com.iouone.pagamento.client.PagarmeClient;
import br.com.iouone.pagamento.models.*;
import br.com.iouone.pagamento.repositories.AssinaturaRepository;
import br.com.iouone.pagamento.repositories.ItemAssinaturaRepository;
import br.com.iouone.pagamento.repositories.MeioPagamentoRepository;
import br.com.iouone.pagamento.repositories.PagamentoRepository;
import br.com.iouone.pagamento.requests.AssinaturaRequest;
import br.com.iouone.pagamento.requests.BillingAddress;
import br.com.iouone.pagamento.requests.Card;
import br.com.iouone.pagamento.requests.PagamentoAssinaturaRequest;
import br.com.iouone.pagamento.responses.AssinaturaResponse;
import br.com.iouone.pagamento.responses.DadosEnderecoPessoaResponse;
import br.com.iouone.pagamento.services.AssinaturaService;
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
    private final ItemAssinaturaRepository itemAssinaturaRepository;

    @Value("${pagarme.api.username}")
    private String username;

    @Value("${pagarme.api.password}")
    private String password;


    @Autowired
    public AssinaturaServiceImpl(PagarmeClient pagarmeClient, PessoaServiceImpl pessoaService, AssinaturaRepository assinaturaRepository, PagamentoRepository pagamentoRepository, MeioPagamentoRepository meioPagamentoRepository, ItemAssinaturaRepository itemAssinaturaRepository) {
        this.pagarmeClient = pagarmeClient;
        this.pessoaService = pessoaService;
        this.assinaturaRepository = assinaturaRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.meioPagamentoRepository = meioPagamentoRepository;
        this.itemAssinaturaRepository = itemAssinaturaRepository;
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

        var assinatura = assinaturaRepository.save(new Assinatura(dadosEndereco.getCustomerId(),
                response.getBody().getId(), "month", LocalDateTime.now(),
                response.getBody().getStatus(), buscaItemAssinatura(1)));

        if(response.getBody().getStatus().equals("failed")) {
            throw new RuntimeException("Pagamento Failed!");
        }


        var meioPagamento = buscaOrdenadorPagamento(request.getFormaPagamento());

        pagamentoRepository.save(new Pagamento(assinatura, "certo", LocalDateTime.now(), null, meioPagamento));


        return new AssinaturaResponse();
    }

    private static AssinaturaRequest getAssinaturaRequest(PagamentoAssinaturaRequest request, String[] dataValidade, DadosEnderecoPessoaResponse dadosEndereco) {
        String mes = dataValidade[0];
        String ano = dataValidade[1];

        var endereco = new BillingAddress(dadosEndereco.getEndereco(), dadosEndereco.getEstado(),
                "BR", dadosEndereco.getCidade(), dadosEndereco.getCep());

        var card = new Card(endereco, request.getNumeroCartao(), Integer.parseInt(mes), Integer.parseInt(ano), request.getNomeCartao(), request.getCvv());

        return new AssinaturaRequest(request.getFormaPagamento(), card, dadosEndereco.getCustomerId());

    }

    private static String[] separardataDeValidade(String dataValidade) {
        return dataValidade.split("/");
    }


    public Enum meioPagamento(String meioPagamento) {
        return switch (meioPagamento) {
            case "credito" -> MetodoPagamento.CREDIT_CARD;
            case "debito" -> MetodoPagamento.DEBIT_CARD;
            case "pix" -> MetodoPagamento.PIX;
            default -> MetodoPagamento.NAO_ENCONTRADO;
        };
    }

    private ItemAssinatura buscaItemAssinatura(Integer idItemAssinatura) {
        return itemAssinaturaRepository.findById(idItemAssinatura)
                .orElseThrow(() -> new RuntimeException("Item Assinatura não encontrado!!"));
    }

    private OrdenadorPagamento buscaOrdenadorPagamento(String meioPagamento) {
        var valorMeioPagamento = meioPagamento(meioPagamento);
        if (Objects.equals(valorMeioPagamento, MetodoPagamento.NAO_ENCONTRADO)) {
            throw new RuntimeException("Nao foi encontrado meio de Pagamento - Enum");
        }
        return meioPagamentoRepository.findByMeioDePagamento(valorMeioPagamento);

    }

    public Boolean assinaturaAtivada(String customerId) {
        var getAssinatura = assinaturaRepository.buscarDataInicialAssinatura(customerId);
        if (getAssinatura.isEmpty()) {
            return false;
        }
        var dataFuturo = getAssinatura.get(0).getStartAt().plusMonths(1L);
        var dataAtual = LocalDateTime.now();

        if (!dataFuturo.isAfter(dataAtual)) {
            var assinatura = getAssinatura.get(0);
            assinaturaRepository.save(new Assinatura(assinatura.getId(), assinatura.getCustomerId(),
                    assinatura.getCodeAssinatura(), assinatura.getIntervalAssinatura(), assinatura.getStartAt(),
                    "expired-time", assinatura.getItemAssinatura()));
            return false;
        }
        return true;
    }
}
