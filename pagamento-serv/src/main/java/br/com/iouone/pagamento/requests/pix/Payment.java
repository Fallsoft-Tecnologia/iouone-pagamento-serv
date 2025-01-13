package br.com.iouone.pagamento.requests.pix;

public class Payment {
    private String payment_method;
    private Pix pix;

    public Payment() {
    }

    public Payment(String payment_method, Pix pix) {
        this.payment_method = payment_method;
        this.pix = pix;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public Pix getPix() {
        return pix;
    }

    public void setPix(Pix pix) {
        this.pix = pix;
    }
}
