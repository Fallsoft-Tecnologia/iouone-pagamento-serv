package br.com.iouone.pagamento.requests;

public class WebhookRequest {
    private String event;
    private Transaction transaction;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
