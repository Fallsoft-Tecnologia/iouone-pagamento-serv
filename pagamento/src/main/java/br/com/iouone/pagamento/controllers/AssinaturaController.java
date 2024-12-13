package br.com.iouone.pagamento.controllers;

import br.com.iouone.pagamento.requests.AssinaturaCancelRequest;
import br.com.iouone.pagamento.requests.AssinaturaRequest;
import br.com.iouone.pagamento.requests.AssinaturaUpdateRequest;
import br.com.iouone.pagamento.responses.AssinaturaResponse;
import br.com.iouone.pagamento.responses.PagamentoResponse;
import br.com.iouone.pagamento.services.PagamentoService;
import br.com.iouone.pagamento.services.impl.AssinaturaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assinaturas")
@CrossOrigin
public class AssinaturaController {

    private final AssinaturaServiceImpl assinaturaService;
    private final PagamentoService pagamentoService;

    @Autowired
    public AssinaturaController (AssinaturaServiceImpl assinaturaService, PagamentoService pagamentoService) {
        this.assinaturaService = assinaturaService;
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<AssinaturaResponse> criarAssinatura(@RequestBody AssinaturaRequest request) {
        AssinaturaResponse response = assinaturaService.createAssinatura(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<AssinaturaResponse> buscarAssinatura(@PathVariable String id) {
        AssinaturaResponse assinatura = assinaturaService.getAssinaturaById(id);
        return ResponseEntity.ok(assinatura);
    }

    @GetMapping("/{assinaturaId}/pagamentos")
    public ResponseEntity<List<PagamentoResponse>> retornarPagamentoStatus(@PathVariable Long assinaturaId) {
        List<PagamentoResponse> pagamentos = pagamentoService.obterPagamentosPorAssinatura(assinaturaId);
        return ResponseEntity.ok(pagamentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssinaturaResponse> editarAssinatura(@PathVariable String id, @Valid @RequestBody AssinaturaUpdateRequest request) {
        AssinaturaResponse assinatura = assinaturaService.updateAssinaturaById(id, request);
        return ResponseEntity.ok(assinatura);
    }

    @PostMapping("/cancelamentos")
    public ResponseEntity<AssinaturaResponse> cancelarAssinatura(@Valid @RequestBody AssinaturaCancelRequest request) {
        AssinaturaResponse assinatura = assinaturaService.cancelAssinatura(request);
        return ResponseEntity.ok(assinatura);
    }

     */

}
