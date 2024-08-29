package br.com.iouone.pagamento.services;

import br.com.iouone.pagamento.requests.PagamentoRequest;
import br.com.iouone.pagamento.responses.PagamentoResponse;
import br.com.iouone.pagamento.responses.PagamentoStatusResponse;

import java.util.List;

public interface PagamentoService {
    List<PagamentoResponse> obterPagamentosPorAssinatura(Long assinaturaId);

    PagamentoResponse createPagamento(PagamentoRequest request);

    PagamentoStatusResponse getStatusPagamento(String id);
}
