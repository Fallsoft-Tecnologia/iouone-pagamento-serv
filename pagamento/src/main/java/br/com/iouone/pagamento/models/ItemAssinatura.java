package br.com.iouone.pagamento.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "item_assinatura")
public class ItemAssinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM_ASSINATURA")
    private Long id;
    private String name;
    private String description;
    private String quantity;
    private LocalDate created_at;
    private LocalDate updated_at;
    private BigDecimal unitPrice;

}
