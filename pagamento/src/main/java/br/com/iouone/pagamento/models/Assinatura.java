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
    private String code;
    private String paymentMethod;
    private String interval;
    private int intervalCount;
    private String billingType;
    private LocalDateTime startAt;
    private int installments;
    private String status;

    @OneToOne
    private ItemAssinatura itemAssinatura;

}
