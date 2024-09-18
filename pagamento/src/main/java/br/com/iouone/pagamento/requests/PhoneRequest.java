package br.com.iouone.pagamento.requests;

import java.util.Map;

public class PhoneRequest {
    private Map<String, MobilePhoneRequest> phones;

    public PhoneRequest(MobilePhoneRequest mobilePhone) {
        this.phones = Map.of("mobile_phone", mobilePhone);
    }

    public Map<String, MobilePhoneRequest> getPhones() {
        return phones;
    }

    public void setPhones(Map<String, MobilePhoneRequest> phones) {
        this.phones = phones;
    }
}