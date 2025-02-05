package br.com.iouone.pagamento.requests;

public class PricingScheme {
    private String scheme_type;
    private Integer price;

    public PricingScheme() {
    }

    public PricingScheme(String scheme_type, Integer price) {
        this.scheme_type = scheme_type;
        this.price = price;
    }

    // Getters e Setters
    public String getScheme_type() {
        return scheme_type;
    }

    public void setScheme_type(String scheme_type) {
        this.scheme_type = scheme_type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
