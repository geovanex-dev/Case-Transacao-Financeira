package com.geovane.transacaofinanceira.model;

import com.geovane.transacaofinanceira.domain.model.Transacao;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class TransacaoTest {

    @Test
    void deveCriarTransacaoCorretamente() {
        Transacao transacao = new Transacao(1, 10, 20, new BigDecimal("300"));

        assertEquals(1, transacao.getId());
        assertEquals(10, transacao.getContaOrigem());
        assertEquals(20, transacao.getContaDestino());
        assertEquals(0, new BigDecimal("300").compareTo(transacao.getValor()));
    }
}
