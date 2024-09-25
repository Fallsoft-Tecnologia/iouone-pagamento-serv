package br.com.iouone.pagamento.services.impl;

import br.com.iouone.pagamento.config.feign.PagarmeClient;
import br.com.iouone.pagamento.mappers.AssinaturaMapper;
import br.com.iouone.pagamento.models.Assinatura;
import br.com.iouone.pagamento.models.ItemAssinatura;
import br.com.iouone.pagamento.repositories.AssinaturaRepository;
import br.com.iouone.pagamento.repositories.ItemAssinaturaRepository;
import br.com.iouone.pagamento.requests.AssinaturaCancelRequest;
import br.com.iouone.pagamento.requests.AssinaturaCartaoRequest;
import br.com.iouone.pagamento.requests.AssinaturaPixRequest;
import br.com.iouone.pagamento.requests.AssinaturaUpdateRequest;
import br.com.iouone.pagamento.responses.AssinaturaResponse;
import br.com.iouone.pagamento.services.AssinaturaService;
import org.springframework.stereotype.Service;

@Service
public class AssinaturaServiceImpl implements AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final AssinaturaMapper assinaturaMapper;
    private final ItemAssinaturaRepository itemAssinaturaRepository;
    private final PagarmeClient pagarMeClient;

    public AssinaturaServiceImpl(AssinaturaRepository assinaturaRepository,
                                 AssinaturaMapper assinaturaMapper,
                                 ItemAssinaturaRepository itemAssinaturaRepository,
                                 PagarmeClient pagarMeClient) {
        this.assinaturaRepository = assinaturaRepository;
        this.assinaturaMapper = assinaturaMapper;
        this.itemAssinaturaRepository  = itemAssinaturaRepository;
        this.pagarMeClient = pagarMeClient;
    }

    public AssinaturaResponse createAssinaturaCartao(AssinaturaCartaoRequest request) {

        pagarMeClient.createSubscription(request);

        ItemAssinatura itemAssinatura = itemAssinaturaRepository.findByName("AssinaturaPadrao")
                .orElseThrow();
        Assinatura assinatura = assinaturaMapper.toDomain(request, itemAssinatura);
        assinaturaRepository.save(assinatura);
        return assinaturaMapper.toResponse(assinatura);
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
