package br.com.iouone.pagamento.config.feign;

import br.com.iouone.pagamento.config.FeignConfig;
import br.com.iouone.pagamento.models.Customer;
import br.com.iouone.pagamento.requests.AssinaturaCartaoRequest;
import br.com.iouone.pagamento.requests.PixRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "pagarmeClient", url = "https://api.pagar.me/core/v5", configuration = FeignConfig.class)
public interface PagarmeClient {

    @PostMapping("/subscriptions")
    ResponseEntity<Object> createSubscription(@RequestBody AssinaturaCartaoRequest assinaturaCartaoRequest);

    @PostMapping("/customers")
    ResponseEntity<Customer> criarCliente(@RequestBody Customer customer);
    /* @PostMapping("/subscriptions")
    ResponseEntity<SubscriptionResponse> criarAssinatura(@RequestHeader("Authorization") String authorization,
                                                         @RequestBody SubscriptionRequest request);

    @GetMapping("/subscriptions/{subscriptionId}")
    SubscriptionResponse obterAssinatura(@RequestHeader("Authorization") String authorization,
                                         @PathVariable("subscriptionId") String subscriptionId);

     */

    @PostMapping("/orders")
    ResponseEntity<String> criarTransacaoPix(@RequestBody PixRequest orderRequest);

    @GetMapping("/orders/{order_id}")
    ResponseEntity<String> obterPedido(@PathVariable("order_id") String orderId);
}
