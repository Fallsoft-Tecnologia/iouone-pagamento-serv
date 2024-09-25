package br.com.iouone.pagamento.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "assinatura")
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ASSINATURA")
    private Long id;
    private String customerId;
//    private String code;
    private String paymentMethod;
    private LocalDateTime startAt;
    private String status;
    @OneToOne
    private ItemAssinatura itemAssinatura;

    public Assinatura() {
    }

    public Assinatura(String customerId, String paymentMethod, String status, ItemAssinatura itemAssinatura) {
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.startAt = LocalDateTime.now();
        this.status = status;
        this.itemAssinatura = itemAssinatura;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ItemAssinatura getItemAssinatura() {
        return itemAssinatura;
    }

    public void setItemAssinatura(ItemAssinatura itemAssinatura) {
        this.itemAssinatura = itemAssinatura;
    }
}
