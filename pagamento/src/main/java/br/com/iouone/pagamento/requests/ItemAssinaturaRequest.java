package br.com.iouone.pagamento.requests;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ItemAssinaturaRequest(
        @NotBlank(message = "O nome do item não pode estar em branco")
        String name,
        @NotBlank(message = "A descrição do item não pode estar em branco")
        String description,

        String status,
        @NotBlank(message = "O método de pagamento não pode estar em branco")
        BigDecimal unitPrice
) {
}
