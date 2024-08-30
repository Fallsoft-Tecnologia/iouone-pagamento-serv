package br.com.iouone.pagamento.services;

import br.com.iouone.pagamento.requests.AssinaturaCancelRequest;
import br.com.iouone.pagamento.requests.AssinaturaCartaoRequest;
import br.com.iouone.pagamento.requests.AssinaturaPixRequest;
import br.com.iouone.pagamento.requests.AssinaturaUpdateRequest;
import br.com.iouone.pagamento.responses.AssinaturaResponse;

public interface AssinaturaService {
    AssinaturaResponse createAssinaturaCartao(AssinaturaCartaoRequest request);
    AssinaturaResponse getAssinaturaById(String id);
    AssinaturaResponse updateAssinaturaById(String id, AssinaturaUpdateRequest request);
    AssinaturaResponse cancelAssinatura(AssinaturaCancelRequest request);
    AssinaturaResponse createAssinaturaPix(AssinaturaPixRequest request);
}
