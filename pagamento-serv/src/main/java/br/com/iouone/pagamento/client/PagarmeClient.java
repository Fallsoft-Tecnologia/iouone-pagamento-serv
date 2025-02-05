package br.com.iouone.pagamento.client;


import br.com.iouone.pagamento.models.Customer;
import br.com.iouone.pagamento.requests.AssinaturaRequest;
import br.com.iouone.pagamento.requests.PixRequest;
import br.com.iouone.pagamento.responses.AssinaturaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "pagarmeClient", url = "${urls.api.pagarme}")
public interface PagarmeClient {

    @PostMapping("/customers")
    ResponseEntity<Customer> criarCliente(@RequestHeader("Authorization") String authorizationHeader,
                                          @RequestBody Customer customer);

    @PostMapping("/subscriptions")
    ResponseEntity<AssinaturaResponse> criarAssinatura(@RequestHeader("Authorization") String authorization,
                                                       @RequestBody AssinaturaRequest request);
    /*
    @GetMapping("/subscriptions/{subscriptionId}")
    ResponseEntity<SubscriptionResponse> obterAssinatura(@RequestHeader("Authorization") String authorization,
                                                         @PathVariable("subscriptionId") String subscriptionId);

     */
    @PostMapping("/orders")
    ResponseEntity<String> criarTransacaoPix(@RequestHeader("Authorization") String authorization,
                                             @RequestBody PixRequest orderRequest);

    @GetMapping("/orders/{order_id}")
    ResponseEntity<String> obterPedido(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("order_id") String orderId);



}
