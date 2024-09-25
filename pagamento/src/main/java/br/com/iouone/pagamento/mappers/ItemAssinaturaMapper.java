package br.com.iouone.pagamento.mappers;

import br.com.iouone.pagamento.models.ItemAssinatura;
import br.com.iouone.pagamento.models.ItemStatus;
import br.com.iouone.pagamento.requests.ItemAssinaturaRequest;
import org.springframework.stereotype.Component;

@Component
public class ItemAssinaturaMapper {

    public ItemAssinatura toDomain(ItemAssinaturaRequest request) {
        ItemStatus itemStatus;

        if(request.status().equals("active")) {
            itemStatus = ItemStatus.ACTIVE;
        } else {
            itemStatus = ItemStatus.INACTIVE;
        }

        return new ItemAssinatura(
                request.name(),
                request.description(),
                itemStatus,
                request.unitPrice()
        );
    }
}
