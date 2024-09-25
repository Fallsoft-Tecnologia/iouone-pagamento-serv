package br.com.iouone.pagamento.services;

import br.com.iouone.pagamento.requests.ItemAssinaturaRequest;

public interface ItemAssinaturaService {
    void createItemAssinatura(ItemAssinaturaRequest itemAssinaturaRequest);
}
