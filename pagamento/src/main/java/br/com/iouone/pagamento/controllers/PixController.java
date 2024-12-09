package br.com.iouone.pagamento.controllers;

import br.com.iouone.pagamento.requests.PixRequest;
import br.com.iouone.pagamento.services.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pix")
public class PixController {

    private final PixService pixService;

    @Autowired
    public PixController(PixService pixService) {
        this.pixService = pixService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> criarTransacaoPix(@RequestBody PixRequest pixRequest) {
        try {
            PixRequest request = new PixRequest(pixRequest.getCustomer_id());
            String response = pixService.criarTransacaoPix(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar transação Pix: " + e.getMessage());
        }
    }


    @GetMapping("/pedido/{orderId}")
    public ResponseEntity<String> obterPedido(@PathVariable String orderId) {
        try {
            String response = pixService.obterPedido(orderId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao obter pedido: " + e.getMessage());
        }
    }

    @GetMapping("/status/{orderId}")
    public ResponseEntity<String> obterStatusPagamento(@PathVariable String orderId) {
        try {
            String status = pixService.obterStatusPedido(orderId);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter status do pagamento: " + e.getMessage());
        }
    }
}
