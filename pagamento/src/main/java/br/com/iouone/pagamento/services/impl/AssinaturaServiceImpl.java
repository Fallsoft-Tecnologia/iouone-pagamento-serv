package br.com.iouone.pagamento.services.impl;

import br.com.iouone.pagamento.repositories.AssinaturaRepository;
import br.com.iouone.pagamento.requests.AssinaturaCancelRequest;
import br.com.iouone.pagamento.requests.AssinaturaCartaoRequest;
import br.com.iouone.pagamento.requests.AssinaturaPixRequest;
import br.com.iouone.pagamento.requests.AssinaturaUpdateRequest;
import br.com.iouone.pagamento.responses.AssinaturaResponse;
import br.com.iouone.pagamento.services.AssinaturaService;
import org.springframework.stereotype.Service;

@Service
public class AssinaturaServiceImpl implements AssinaturaService {

    private AssinaturaRepository assinaturaRepository;

    public AssinaturaServiceImpl(AssinaturaRepository assinaturaRepository) {
        this.assinaturaRepository = assinaturaRepository;
    }

    public AssinaturaResponse createAssinaturaCartao(AssinaturaCartaoRequest request) {
        return null;
    }

    public AssinaturaResponse getAssinaturaById(String id) {
        return null;
    }

    public AssinaturaResponse updateAssinaturaById(String id, AssinaturaUpdateRequest request) {
        return null;
    }

    public AssinaturaResponse cancelAssinatura(AssinaturaCancelRequest request) {
        return null;
    }

    @Override
    public AssinaturaResponse createAssinaturaPix(AssinaturaPixRequest request) {
        return null;
    }
}
