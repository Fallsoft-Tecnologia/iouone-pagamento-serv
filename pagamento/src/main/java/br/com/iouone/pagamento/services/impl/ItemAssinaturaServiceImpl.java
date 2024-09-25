package br.com.iouone.pagamento.services.impl;


import br.com.iouone.pagamento.mappers.ItemAssinaturaMapper;
import br.com.iouone.pagamento.models.ItemAssinatura;
import br.com.iouone.pagamento.repositories.ItemAssinaturaRepository;
import br.com.iouone.pagamento.requests.ItemAssinaturaRequest;
import br.com.iouone.pagamento.services.ItemAssinaturaService;
import org.springframework.stereotype.Service;

@Service
public class ItemAssinaturaServiceImpl implements ItemAssinaturaService {

    private final ItemAssinaturaMapper itemAssinaturaMapper;
    private final ItemAssinaturaRepository itemAssinaturaRepository;

    public ItemAssinaturaServiceImpl(ItemAssinaturaRepository itemAssinaturaRepository, ItemAssinaturaMapper itemAssinaturaMapper) {
        this.itemAssinaturaMapper = itemAssinaturaMapper;
        this.itemAssinaturaRepository = itemAssinaturaRepository;
    }

    @Override
    public void createItemAssinatura(ItemAssinaturaRequest itemAssinaturaRequest) {
        ItemAssinatura itemAssinatura = itemAssinaturaMapper.toDomain(itemAssinaturaRequest);
        itemAssinaturaRepository.save(itemAssinatura);
    }
}
