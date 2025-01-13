package br.com.iouone.pagamento.models;

import java.util.Map;

public class WebhookPayload {

    private Long id;
    private String event;
    private Map<String, Object> data;

}
