package br.com.iouone.pagamento.mappers;

import br.com.iouone.pagamento.models.Assinatura;
import br.com.iouone.pagamento.models.ItemAssinatura;
import br.com.iouone.pagamento.requests.AssinaturaCartaoRequest;
import br.com.iouone.pagamento.responses.AssinaturaResponse;
import org.springframework.stereotype.Component;

@Component
public class AssinaturaMapper {
    public Assinatura toDomain(AssinaturaCartaoRequest request, ItemAssinatura itemAssinatura) {
        return new Assinatura(
                request.customerId(),
                request.metodoPagamento(),
                "status",
                itemAssinatura
        );
    }

    public AssinaturaResponse toResponse(Assinatura assinatura) {
        return new AssinaturaResponse(
                assinatura.getPaymentMethod(),
                assinatura.getStartAt(),
                assinatura.getStatus(),
                assinatura.getItemAssinatura().getUnitPrice()
        );
    }
}
