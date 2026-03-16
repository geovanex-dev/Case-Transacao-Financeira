package com.geovane.transacaofinanceira.domain.model;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class Conta {

    private final long id;
    private BigDecimal saldo;

    public Conta(long id, BigDecimal saldoInicial) {
        this.id = id;
        this.saldo = saldoInicial != null ? saldoInicial : BigDecimal.ZERO;
    }

    public void debitar(BigDecimal valor) {
        validarValor(valor);
        if (this.saldo.compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }
        this.saldo = this.saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        validarValor(valor);
        this.saldo = this.saldo.add(valor);
    }

    private void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
    }
}
