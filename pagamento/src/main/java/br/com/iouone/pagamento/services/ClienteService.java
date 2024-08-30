package br.com.iouone.pagamento.services;

import br.com.iouone.pagamento.mapper.PessoaToCustomerMapper;
import br.com.iouone.pagamento.models.Customer;
import br.com.iouone.pagamento.requests.CustomerRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ClienteService {

    private final PagarmeClient pagarmeClient;

    @Value("${pagarme.api.username}")
    private String username;

    @Value("${pagarme.api.password}")
    private String password;

    public ClienteService(PagarmeClient pagarmeClient) {
        this.pagarmeClient = pagarmeClient;
    }

    private String getAuthorizationHeader() {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    public ResponseEntity<String> criarCliente(CustomerRequest customerRequest) {
        Customer customer = PessoaToCustomerMapper.toCustomer(customerRequest);
        String authorizationHeader = getAuthorizationHeader();
        ResponseEntity<Customer> response = pagarmeClient.criarCliente(authorizationHeader, customer);

        if (response.getStatusCode().is2xxSuccessful()) {
            Customer createdCustomer = response.getBody();
            // Armazena o ID do cliente e retorna como ResponseEntity
            if (createdCustomer != null) {
                return ResponseEntity.ok(createdCustomer.getId());
            } else {
                return ResponseEntity.status(500).body("Cliente criado, mas resposta está vazia.");
            }
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Falha ao criar cliente na API do Pagar.me: " + response.getBody());
        }
    }
}
