package br.com.iouone.pagamento.repositories;

import br.com.iouone.pagamento.models.OrdenadorPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeioPagamentoRepository extends JpaRepository<OrdenadorPagamento,Integer> {

    OrdenadorPagamento findByMeioDePagamento(Enum meioPagamento);
}
