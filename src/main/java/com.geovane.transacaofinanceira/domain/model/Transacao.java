package com.geovane.transacaofinanceira.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Transacao {

    private final long id;
    private final long contaOrigem;
    private final long contaDestino;
    private final BigDecimal valor;
}