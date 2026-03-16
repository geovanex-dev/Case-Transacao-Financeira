package com.geovane.transacaofinanceira.adapters.output;

import com.geovane.transacaofinanceira.domain.model.Conta;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class RepositorioContaEmMemoriaTest {

    @Test
    void deveSalvarEBucarConta() {
        RepositorioContaEmMemoria repositorio = new RepositorioContaEmMemoria();
        Conta conta = new Conta(1, new BigDecimal("500"));

        repositorio.salvar(conta);
        Optional<Conta> resultado = repositorio.buscarPorId(1);

        assertTrue(resultado.isPresent());
        assertEquals(0, new BigDecimal("500").compareTo(resultado.get().getSaldo()));
    }

    @Test
    void deveRetornarVazioQuandoContaNaoExiste() {
        RepositorioContaEmMemoria repositorio = new RepositorioContaEmMemoria();
        Optional<Conta> resultado = repositorio.buscarPorId(99);
        assertTrue(resultado.isEmpty());
    }
}
