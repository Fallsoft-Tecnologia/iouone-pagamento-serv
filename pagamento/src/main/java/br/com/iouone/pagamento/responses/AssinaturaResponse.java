package br.com.iouone.pagamento.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AssinaturaResponse (
        String paymentMethod,
        LocalDateTime startAt,
        String status,
        BigDecimal unitPrice
) {
}
