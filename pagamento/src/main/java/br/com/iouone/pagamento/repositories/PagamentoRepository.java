package br.com.iouone.pagamento.repositories;

import br.com.iouone.pagamento.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByAssinaturaId(Long assinaturaId);
}
