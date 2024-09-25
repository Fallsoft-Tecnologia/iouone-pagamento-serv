package br.com.iouone.pagamento.config.feign;

import br.com.iouone.pagamento.config.FeignConfig;
import br.com.iouone.pagamento.requests.AssinaturaCartaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagarmeClient", url = "https://api.pagar.me/core/v5", configuration = FeignConfig.class)
public interface PagarMeClient {

    @PostMapping("/subscriptions")
    ResponseEntity<Object> createSubscription(@RequestBody AssinaturaCartaoRequest assinaturaCartaoRequest);
}
