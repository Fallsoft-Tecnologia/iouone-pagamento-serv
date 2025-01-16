package br.com.iouone.pagamento.services;

import br.com.iouone.pagamento.requests.PagamentoRequest;
import br.com.iouone.pagamento.responses.PagamentoResponse;
import br.com.iouone.pagamento.responses.PagamentoStatusResponse;

import java.util.List;

public interface PagamentoService {
    List<PagamentoResponse> obterPagamentosPorAssinatura(Long assinaturaId);

    PagamentoResponse createPagamento(PagamentoRequest request);

    PagamentoStatusResponse getStatusPagamento(String id);

    // Método para atualizar o status de um pagamento
    void atualizarStatusPagamento(String paymentId, String newStatus);

    // Método para enviar notificação ao cliente
    void enviarNotificacaoCliente(String paymentId, String status);
}
