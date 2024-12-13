package br.com.iouone.pagamento.requests;

import java.util.List;

public class AssinaturaRequest {

    private String payment_method;
    private String interval;
    private Integer interval_count;
    private String billing_type;
    private Card card;
    private PricingScheme pricing_scheme;
    private Integer quantity;
    private String customer_id;
    private List<Item> items;
    private BillingAddress billing_address;

    public BillingAddress getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(BillingAddress billing_address) {
        this.billing_address = billing_address;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Integer getInterval_count() {
        return interval_count;
    }

    public void setInterval_count(Integer interval_count) {
        this.interval_count = interval_count;
    }

    public String getBilling_type() {
        return billing_type;
    }

    public void setBilling_type(String billing_type) {
        this.billing_type = billing_type;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public PricingScheme getPricing_scheme() {
        return pricing_scheme;
    }

    public void setPricing_scheme(PricingScheme pricing_scheme) {
        this.pricing_scheme = pricing_scheme;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
}
