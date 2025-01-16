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

}
