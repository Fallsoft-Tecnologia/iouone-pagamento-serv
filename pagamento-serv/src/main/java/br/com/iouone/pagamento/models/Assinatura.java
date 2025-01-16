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
    private String paymentMethod;
    private String intervalAssinatura;
    private int intervalCount;
    private String billingType;
    private LocalDateTime startAt;
    private int installments;
    private String statusAssinatura;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "item_assinatura", referencedColumnName = "id_item_assinatura")
    private ItemAssinatura itemAssinatura;

}
