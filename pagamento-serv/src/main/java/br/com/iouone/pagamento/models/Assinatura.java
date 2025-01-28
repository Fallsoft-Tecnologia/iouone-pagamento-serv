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
    private Integer id;
    private String customerId;
    private String codeAssinatura;
    private String intervalAssinatura;
    private LocalDateTime startAt;
    private String statusAssinatura;

    @ManyToOne
    @JoinColumn(name = "itemAssinatura")
    private ItemAssinatura itemAssinatura;

    public Assinatura() {
    }

    public Assinatura( String customerId, String codeAssinatura, String intervalAssinatura, LocalDateTime startAt,
                       String statusAssinatura, ItemAssinatura itemAssinatura) {
        this.customerId = customerId;
        this.codeAssinatura = codeAssinatura;
        this.intervalAssinatura = intervalAssinatura;
        this.startAt = startAt;
        this.statusAssinatura = statusAssinatura;
        this.itemAssinatura = itemAssinatura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCodeAssinatura() {
        return codeAssinatura;
    }

    public void setCodeAssinatura(String codeAssinatura) {
        this.codeAssinatura = codeAssinatura;
    }

    public String getIntervalAssinatura() {
        return intervalAssinatura;
    }

    public void setIntervalAssinatura(String intervalAssinatura) {
        this.intervalAssinatura = intervalAssinatura;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public String getStatusAssinatura() {
        return statusAssinatura;
    }

    public void setStatusAssinatura(String statusAssinatura) {
        this.statusAssinatura = statusAssinatura;
    }

    public ItemAssinatura getItemAssinatura() {
        return itemAssinatura;
    }

    public void setItemAssinatura(ItemAssinatura itemAssinatura) {
        this.itemAssinatura = itemAssinatura;
    }
}
