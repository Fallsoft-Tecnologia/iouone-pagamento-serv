package br.com.iouone.pagamento.responses;

public class PagamentoStatusResponse {
    private String status;
    private Integer id;

    public PagamentoStatusResponse(String status, Integer id) {
        this.status = status;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
