package br.com.iouone.pagamento.requests;

import jakarta.validation.constraints.NotBlank;

public record AssinaturaCartaoRequest (
        @NotBlank(message = "O método de pagamento não pode estar em branco")
        String metodoPagamento,
        @NotBlank(message = "O id do cliente pagarme não pode estar em branco")
        String customerId,
        @NotBlank(message = "O número do cartao não pode estar em branco")
        String numero,
        @NotBlank(message = "O nome do titular do cartao não pode estar em branco")
        String nomeTitular,
        @NotBlank(message = "O documento do titular do cartao não pode estar em branco")
        String documentoTitular,
        @NotBlank(message = "O mes de expiração do cartao não pode estar em branco")
        int mesExpiracao,
        @NotBlank(message = "O ano de expiração do cartao não pode estar em branco")
        int anoExpiracao,
        @NotBlank(message = "O codigo verificador do cartao não pode estar em branco")
        String cvv,
        @NotBlank(message = "A bandeira do cartao não pode estar em branco")
        String brand

) {

}
