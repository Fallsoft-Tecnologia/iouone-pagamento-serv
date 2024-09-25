package br.com.iouone.pagamento.repositories;

import br.com.iouone.pagamento.models.ItemAssinatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemAssinaturaRepository extends JpaRepository<ItemAssinatura, Long> {
    Optional<ItemAssinatura> findByName(String name);
}
