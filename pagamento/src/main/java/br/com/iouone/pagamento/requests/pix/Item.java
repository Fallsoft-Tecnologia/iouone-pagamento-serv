package br.com.iouone.pagamento.requests.pix;

public class Item {
    private Integer amount;
    private String description;
    private Integer quantity;

    public Item() {
    }

    public Item(Integer amount, String description, Integer quantity) {
        this.amount = amount;
        this.description = description;
        this.quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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
}
