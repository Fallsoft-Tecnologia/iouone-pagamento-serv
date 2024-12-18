package br.com.iouone.pagamento.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AssinaturaRequest {

    @NotBlank(message = "O método de pagamento é obrigatório.")
    private String payment_method;
    @NotBlank(message = "O intervalo de pagamento é obrigatório.")
    private String interval;
    @NotNull(message = "A quantidade de intervalos é obrigatória.")
    private Integer interval_count;
    @NotBlank(message = "O tipo de cobrança é obrigatório.")
    private String billing_type;
    @NotNull(message = "O cartão de pagamento é obrigatório.")
    private Card card;
    @NotNull(message = "O esquema de preços é obrigatório.")
    private PricingScheme pricing_scheme;
    @NotNull(message = "A quantidade de itens é obrigatória.")
    private Integer quantity;
    @NotBlank(message = "O ID do cliente é obrigatório.")
    private String customer_id;
    @NotNull(message = "A lista de itens não pode ser nula.")
    private List<Item> items;
    @NotNull(message = "O endereço de cobrança é obrigatório.")
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
