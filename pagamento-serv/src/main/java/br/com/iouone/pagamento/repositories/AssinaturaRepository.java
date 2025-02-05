package br.com.iouone.pagamento.repositories;

import br.com.iouone.pagamento.models.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {

    @Query("SELECT a " +
            "FROM Assinatura a " +
            "WHERE a.customerId =:customerId " +
            "AND a.statusAssinatura = 'active' " +
            "ORDER BY a.startAt DESC")
    List<Assinatura> buscarDataInicialAssinatura(String customerId);
}
