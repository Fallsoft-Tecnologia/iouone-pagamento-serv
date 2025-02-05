package br.com.iouone.pagamento.requests;

public class PagamentoAssinaturaRequest {

    private String numeroCartao;
    private String dataValidade;
    private String cvv;
    private String nomeCartao;
    private String formaPagamento;

    public PagamentoAssinaturaRequest() {
    }

    public PagamentoAssinaturaRequest(String numeroCartao, String dataValidade, String cvv, String nomeCartao, String formaPagamento) {
        this.numeroCartao = numeroCartao;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
        this.nomeCartao = nomeCartao;
        this.formaPagamento = formaPagamento;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
