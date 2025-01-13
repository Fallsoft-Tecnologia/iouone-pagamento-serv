package br.com.iouone.pagamento.models;

import jakarta.persistence.*;

@Entity
@Table(name = "meio_de_pagamento")
public class OrdenadorPagamento extends DefaultCriacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDENADOR_PAGAMENTO")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEIO_DE_PAGAMENTO")
    private MetodoPagamento meioDePagamento;

    public OrdenadorPagamento() {
    }

    public OrdenadorPagamento(Integer id, MetodoPagamento meioDePagamento) {
        this.id = id;
        this.meioDePagamento = meioDePagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MetodoPagamento getMeioDePagamento() {
        return meioDePagamento;
    }

    public void setMeioDePagamento(MetodoPagamento meioDePagamento) {
        this.meioDePagamento = meioDePagamento;
    }
}
