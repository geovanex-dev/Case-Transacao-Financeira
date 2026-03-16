package com.geovane.transacaofinanceira.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class Conta {

    private final long id;
    private long saldo;

    public Conta(long id, long saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public void debitar(double valor) {
        this.saldo -= valor;
    }

    public void creditar(double valor) {
        this.saldo += valor;
    }
}