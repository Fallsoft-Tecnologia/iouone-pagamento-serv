package br.com.iouone.pagamento.repositories;

import br.com.iouone.pagamento.models.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
}
