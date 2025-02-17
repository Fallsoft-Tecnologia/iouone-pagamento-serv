package br.com.iouone.pagamento.models;

import br.com.iouone.pagamento.requests.MobilePhoneRequest;

import java.util.Map;

public class Customer {
    private String id;
    private String name;
    private String email;
    private String document;
    private String type; // "individual" ou "company"
    private String gender; // "male" ou "female"
    private String birthdate; // "yyyy-MM-dd"
    private Map<String, MobilePhoneRequest> phones;




    public Customer() {
    }

    public Customer(String id, String name, String email, String document, String type, String gender, String birthdate, Map<String, MobilePhoneRequest> phones) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.type = type;
        this.gender = gender;
        this.birthdate = birthdate;
        this.phones = phones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


    public Map<String, MobilePhoneRequest> getPhones() {
        return phones;
    }

    public void setPhones(Map<String, MobilePhoneRequest> phones) {
        this.phones = phones;
    }
}
