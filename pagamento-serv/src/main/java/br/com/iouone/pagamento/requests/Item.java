package br.com.iouone.pagamento.requests;

public class Item {
    private PricingScheme pricing_scheme;
    private String description;
    private Integer quantity;
    private String name;

    public Item() {
    }

    public Item(PricingScheme pricing_scheme, String description, Integer quantity, String name) {
        this.pricing_scheme = pricing_scheme;
        this.description = description;
        this.quantity = quantity;
        this.name = name;
    }

    // Getters e Setters
    public PricingScheme getPricing_scheme() {
        return pricing_scheme;
    }

    public void setPricing_scheme(PricingScheme pricing_scheme) {
        this.pricing_scheme = pricing_scheme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
