package br.com.iouone.pagamento.repositories;

import br.com.iouone.pagamento.models.ItemAssinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemAssinaturaRepository extends JpaRepository<ItemAssinatura,Integer> {

}
