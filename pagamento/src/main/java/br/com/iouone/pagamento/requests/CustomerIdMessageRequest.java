package br.com.iouone.pagamento.requests;

public class CustomerIdMessageRequest {

    private Integer pessoaId;
    private String customerId;

    public CustomerIdMessageRequest(Integer pessoaId, String customerId) {
        this.pessoaId = pessoaId;
        this.customerId = customerId;
    }

    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
