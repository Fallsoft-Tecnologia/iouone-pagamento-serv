package br.com.iouone.pagamento.requests;

public class Card {
    private BillingAddress billing_address;
    private String number;
    private Integer exp_month;
    private Integer exp_year;
    private String holder_name;

    public Card() {
    }

    public Card(BillingAddress billing_address, String number, Integer exp_month, Integer exp_year, String holder_name) {
        this.billing_address = billing_address;
        this.number = number;
        this.exp_month = exp_month;
        this.exp_year = exp_year;
        this.holder_name = holder_name;
    }

    // Getters e Setters
    public BillingAddress getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(BillingAddress billing_address) {
        this.billing_address = billing_address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getExp_month() {
        return exp_month;
    }

    public void setExp_month(Integer exp_month) {
        this.exp_month = exp_month;
    }

    public Integer getExp_year() {
        return exp_year;
    }

    public void setExp_year(Integer exp_year) {
        this.exp_year = exp_year;
    }

    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }
}
