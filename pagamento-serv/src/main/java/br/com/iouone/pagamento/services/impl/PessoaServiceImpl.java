package br.com.iouone.pagamento.services.impl;

import br.com.iouone.pagamento.client.PessoaApiClient;
import br.com.iouone.pagamento.responses.DadosEnderecoPessoaResponse;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl {

    private final PessoaApiClient pessoaApiClient;


    public PessoaServiceImpl(PessoaApiClient pessoaApiClient) {
        this.pessoaApiClient = pessoaApiClient;
    }

    public DadosEnderecoPessoaResponse getEnderecoPessoa(String fluxoId){
        try {
            return pessoaApiClient.getDadosPessoaEndereco(fluxoId);
        }catch (Exception e){
            throw new RuntimeException("Erro na comunicacao com a api plataforma",e);
        }
    }
}
