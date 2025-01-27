package br.com.iouone.pagamento.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAGAMENTO")
    private Integer id;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String statusPagamento;

    @ManyToOne
    @JoinColumn(name = "assinatura")
    private Assinatura assinatura;
    @ManyToOne
    @JoinColumn(name = "paymentMethod")
    private OrdenadorPagamento paymentMethod;

    public Pagamento() {
    }

    public Pagamento(Assinatura assinatura, String statusPagamento, LocalDateTime paymentDate, BigDecimal amount, OrdenadorPagamento paymentMethod) {
        this.assinatura = assinatura;
        this.statusPagamento = statusPagamento;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrdenadorPagamento getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(OrdenadorPagamento paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }
}
