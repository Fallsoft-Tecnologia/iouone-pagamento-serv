package br.com.iouone.pagamento.mappers;

import br.com.iouone.pagamento.models.Customer;
import br.com.iouone.pagamento.requests.CustomerRequest;
import br.com.iouone.pagamento.requests.MobilePhoneRequest;
import br.com.iouone.pagamento.requests.PhoneRequest;

public class PessoaToCustomerMapper {

    public static Customer toCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setDocument(customerRequest.getDocument());
        customer.setType(customerRequest.getType());
        customer.setGender(customerRequest.getGender());
        customer.setBirthdate(customerRequest.getBirthdate());

        String celular = customerRequest.getCelular();
        System.out.println("Celular recebido: " + celular);

        if (celular != null && celular.length() == 13) {
            String countryCode = celular.substring(0, 2);
            String areaCode = celular.substring(2, 4);
            String number = celular.substring(4);

            MobilePhoneRequest mobilePhone = new MobilePhoneRequest(countryCode, areaCode, number);
            PhoneRequest phoneDTO = new PhoneRequest(mobilePhone);
            customer.setPhone(phoneDTO);
            System.out.println("Telefone configurado: " + phoneDTO);
        } else {
            customer.setPhone(null);
            System.out.println("Telefone não configurado, valor null ou formato inválido");
        }

        return customer;
    }
}
