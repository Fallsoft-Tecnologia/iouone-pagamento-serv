package br.com.iouone.pagamento.controllers;

import br.com.iouone.pagamento.requests.PagamentoRequest;
import br.com.iouone.pagamento.responses.PagamentoResponse;
import br.com.iouone.pagamento.responses.PagamentoStatusResponse;
import br.com.iouone.pagamento.services.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagamentos")
@CrossOrigin
public class PagamentoController {

    private PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponse> iniciarPagamento(@Valid @RequestBody PagamentoRequest request) {
        PagamentoResponse response = pagamentoService.createPagamento(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<PagamentoStatusResponse> retornarPagamentoStatus(@PathVariable String id) {
        PagamentoStatusResponse response = pagamentoService.getStatusPagamento(id);
        return ResponseEntity.ok(response);
    }

}
