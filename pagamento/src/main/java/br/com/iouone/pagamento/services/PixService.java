package br.com.iouone.pagamento.services;


import br.com.iouone.pagamento.requests.PixRequest;

public interface PixService {
    String criarTransacaoPix(PixRequest pixRequest);
    String obterPedido(String orderId);
}
