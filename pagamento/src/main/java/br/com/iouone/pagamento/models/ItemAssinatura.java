package br.com.iouone.pagamento.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item_assinatura")
public class ItemAssinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM_ASSINATURA")
    private Long id;
    private String subscriptionId;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal unitPrice;

}
