package br.com.iouone.pagamento.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item_assinatura")
public class ItemAssinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM_ASSINATURA")
    private Integer id;
    private String subscriptionId;
    private String nameItemAssinatura;
    private String descriptionItemAssinatura;
    private int quantity;
    private BigDecimal unitPrice;

    @OneToOne(mappedBy = "itemAssinatura", cascade = CascadeType.ALL)
    private Assinatura assinatura;

    public ItemAssinatura() {
    }

    public ItemAssinatura(Integer id) {
        this.id = id;
    }

    public ItemAssinatura(Integer id, String subscriptionId, String nameItemAssinatura, String descriptionItemAssinatura, int quantity, BigDecimal unitPrice, Assinatura assinatura) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.nameItemAssinatura = nameItemAssinatura;
        this.descriptionItemAssinatura = descriptionItemAssinatura;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.assinatura = assinatura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getNameItemAssinatura() {
        return nameItemAssinatura;
    }

    public void setNameItemAssinatura(String nameItemAssinatura) {
        this.nameItemAssinatura = nameItemAssinatura;
    }

    public String getDescriptionItemAssinatura() {
        return descriptionItemAssinatura;
    }

    public void setDescriptionItemAssinatura(String descriptionItemAssinatura) {
        this.descriptionItemAssinatura = descriptionItemAssinatura;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }
}
