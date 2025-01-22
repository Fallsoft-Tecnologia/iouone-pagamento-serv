package br.com.iouone.pagamento.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
@CrossOrigin
public class WebhookController {

    @PostMapping
    public ResponseEntity<String> receiveWebhook(
            @RequestBody String payload, String signature) {

        System.out.println("Payload recebido do Pagar.me: " + payload);

        return ResponseEntity.ok("Webhook recebido com sucesso");
    }

}

