package br.com.iouone.pagamento.mapper;


import br.com.iouone.pagamento.models.Customer;
import br.com.iouone.pagamento.requests.CustomerRequest;
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

        if (celular != null && !celular.isEmpty()) {
            PhoneRequest phoneDTO = new PhoneRequest();
            phoneDTO.setCountry_code(celular.substring(0, 2));  // Código do país
            phoneDTO.setArea_code(celular.substring(2, 4));     // Código de área
            phoneDTO.setNumber(celular.substring(4));          // Número
            customer.setPhone(phoneDTO);
            System.out.println("Telefone configurado: " + phoneDTO);
        } else {
            customer.setPhone(null);
            System.out.println("Telefone não configurado, valor null");
        }
        return customer;
    }
}