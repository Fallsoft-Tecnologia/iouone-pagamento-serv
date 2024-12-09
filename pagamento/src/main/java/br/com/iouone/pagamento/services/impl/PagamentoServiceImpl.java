package br.com.iouone.pagamento.services.impl;

import br.com.iouone.pagamento.models.Pagamento;
import br.com.iouone.pagamento.repositories.PagamentoRepository;
import br.com.iouone.pagamento.requests.PagamentoRequest;
import br.com.iouone.pagamento.responses.PagamentoResponse;
import br.com.iouone.pagamento.responses.PagamentoStatusResponse;
import br.com.iouone.pagamento.services.PagamentoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoServiceImpl(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public List<PagamentoResponse> obterPagamentosPorAssinatura(Long assinaturaId) {
        List<Pagamento> pagamentos = pagamentoRepository.findByAssinaturaId(assinaturaId);
        return pagamentos.stream()
                .map(PagamentoResponse::new)
                .toList();
    }

    @Override
    public PagamentoResponse createPagamento(PagamentoRequest request) {
        return null;
    }

    @Override
    public PagamentoStatusResponse getStatusPagamento(String id) {
        try {
            Long paymentId = Long.parseLong(id); // Converte a String para Long
            Optional<Pagamento> pagamentoOptional = pagamentoRepository.findById(paymentId);
            if (pagamentoOptional.isPresent()) {
                Pagamento pagamento = pagamentoOptional.get();
                return new PagamentoStatusResponse(pagamento.getStatus(), pagamento.getId());
            } else {
                // Retornar uma resposta indicando que o pagamento não foi encontrado
                throw new RuntimeException("Pagamento não encontrado");
            }
        } catch (NumberFormatException e) {
            // Tratar o erro se a conversão falhar
            throw new IllegalArgumentException("ID do pagamento deve ser um número válido", e);
        }
    }

    public void atualizarStatusPagamento(String paymentId, String newStatus) {
        try {
            Long id = Long.parseLong(paymentId); // Converte a String para Long
            Optional<Pagamento> pagamentoOptional = pagamentoRepository.findById(id);
            if (pagamentoOptional.isPresent()) {
                Pagamento pagamento = pagamentoOptional.get();
                pagamento.setStatus(newStatus);
                pagamentoRepository.save(pagamento);
            } else {
                throw new RuntimeException("Pagamento não encontrado");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID do pagamento deve ser um número válido", e);
        }
    }

    // Método para enviar notificação ao cliente sobre o status do pagamento
    @Override
    public void enviarNotificacaoCliente(String paymentId, String status) {
        // Lógica para enviar notificação ao cliente (por exemplo, por e-mail)
        System.out.println("Enviando notificação para o cliente: Pagamento ID: " + paymentId + ", Status: " + status);
    }
}
