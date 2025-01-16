package br.com.iouone.pagamento.services.impl;

import br.com.iouone.pagamento.requests.AssinaturaRequest;
import br.com.iouone.pagamento.responses.AssinaturaResponse;
import br.com.iouone.pagamento.services.AssinaturaService;
import br.com.iouone.pagamento.services.PagarmeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AssinaturaServiceImpl implements AssinaturaService {

    private final PagarmeClient pagarmeClient;

    @Value("${pagarme.api.username}")
    private String username;

    @Value("${pagarme.api.password}")
    private String password;

    @Autowired
    public AssinaturaServiceImpl(PagarmeClient pagarmeClient) {
        this.pagarmeClient = pagarmeClient;
    }

    private String getAuthorizationHeader() {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    @Override
    public AssinaturaResponse createAssinatura(AssinaturaRequest request) {
        String authorizationHeader = getAuthorizationHeader();
        ResponseEntity<AssinaturaResponse> response = pagarmeClient.criarAssinatura(authorizationHeader, request);
        return response.getBody();
    }
}
