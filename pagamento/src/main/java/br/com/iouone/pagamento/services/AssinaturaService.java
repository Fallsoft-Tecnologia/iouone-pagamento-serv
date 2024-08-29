package br.com.iouone.pagamento.services;

import br.com.iouone.pagamento.requests.AssinaturaCancelRequest;
import br.com.iouone.pagamento.requests.AssinaturaRequest;
import br.com.iouone.pagamento.requests.AssinaturaUpdateRequest;
import br.com.iouone.pagamento.responses.AssinaturaResponse;

public interface AssinaturaService {
    AssinaturaResponse createAssinatura(AssinaturaRequest request);
    AssinaturaResponse getAssinaturaById(String id);
    AssinaturaResponse updateAssinaturaById(String id, AssinaturaUpdateRequest request);
    AssinaturaResponse cancelAssinatura(AssinaturaCancelRequest request);
}
