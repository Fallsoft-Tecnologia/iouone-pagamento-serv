package br.com.iouone.pagamento.requests.pix;

public class Pix {
    private int expires_in;

    public Pix() {
    }

    public Pix(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
