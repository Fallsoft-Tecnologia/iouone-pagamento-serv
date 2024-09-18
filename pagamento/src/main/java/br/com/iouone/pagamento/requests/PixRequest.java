package br.com.iouone.pagamento.requests;

import br.com.iouone.pagamento.requests.pix.Item;
import br.com.iouone.pagamento.requests.pix.Payment;
import br.com.iouone.pagamento.requests.pix.Pix;

import java.util.Collections;
import java.util.List;

public class PixRequest {
    private String customer_id;
    private List<Item> items;
    private List<Payment> payments;

    public PixRequest() {
    }

    public PixRequest(String customer_id) {
        this.customer_id = customer_id;

        this.items = Collections.singletonList(new Item(10, "Assinatura Ioune", 1));
        this.payments = Collections.singletonList(new Payment("pix", new Pix(3600)));
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
