package br.com.iouone.pagamento.requests;

import jakarta.validation.constraints.NotBlank;

public class AssinaturaCartaoRequest {

    @NotBlank(message = "O método de pagamento não pode estar em branco")
    private String metodoPagamento;

    @NotBlank(message = "O id do cliente pagarme não pode estar em branco")
    private String customerId;

    @NotBlank(message = "O número do cartao não pode estar em branco")
    private String numero;

    @NotBlank(message = "O nome do titular do cartao não pode estar em branco")
    private String nomeTitular;

    @NotBlank(message = "O documento do titular do cartao não pode estar em branco")
    private String documentoTitular;

    @NotBlank(message = "O mes de expiração do cartao não pode estar em branco")
    private int mesExpiracao;

    @NotBlank(message = "O ano de expiração do cartao não pode estar em branco")
    private int anoExpiracao;

    @NotBlank(message = "O codigo verificador do cartao não pode estar em branco")
    private String cvv;

    @NotBlank(message = "A bandeira do cartao não pode estar em branco")
    private String brand;


}
