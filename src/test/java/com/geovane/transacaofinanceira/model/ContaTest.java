package com.geovane.transacaofinanceira.model;

import com.geovane.transacaofinanceira.domain.model.Conta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    @Test
    @DisplayName("Deve debitar saldo corretamente")
    void deveDebitarSaldo() {
        Conta conta = new Conta(1, new BigDecimal("1000"));
        conta.debitar(new BigDecimal("200"));
        assertEquals(0, new BigDecimal("800").compareTo(conta.getSaldo()));
    }

    @Test
    @DisplayName("Deve creditar saldo corretamente")
    void deveCreditarSaldo() {
        Conta conta = new Conta(1, new BigDecimal("1000"));
        conta.creditar(new BigDecimal("300"));
        assertEquals(0, new BigDecimal("1300").compareTo(conta.getSaldo()));
    }
}
