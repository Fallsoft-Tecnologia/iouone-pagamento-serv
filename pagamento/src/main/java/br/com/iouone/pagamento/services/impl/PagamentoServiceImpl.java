package br.com.iouone.pagamento.services.impl;

import br.com.iouone.pagamento.models.Pagamento;
import br.com.iouone.pagamento.repositories.PagamentoRepository;
import br.com.iouone.pagamento.requests.PagamentoRequest;
import br.com.iouone.pagamento.responses.PagamentoResponse;
import br.com.iouone.pagamento.responses.PagamentoStatusResponse;
import br.com.iouone.pagamento.services.PagamentoService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }
}
