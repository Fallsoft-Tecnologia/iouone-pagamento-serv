package br.com.iouone.pagamento.client;

import br.com.iouone.pagamento.responses.DadosEnderecoPessoaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pessoaClient", url = "${urls.api.pessoa}")
public interface PessoaApiClient {

    @GetMapping("/pagamento/dados-endereco")
     DadosEnderecoPessoaResponse getDadosPessoaEndereco(@RequestParam("fluxoId") String fluxoId);
}
