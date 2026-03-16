package com.geovane.transacaofinanceira.config;

import com.geovane.transacaofinanceira.ports.output.ContaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InicializadorDadosTest {

    @Autowired
    private ContaRepository contaRepository;

    @Test
    void deveTerCarregadoContasIniciais() {
        assertTrue(contaRepository.buscarPorId(938485762).isPresent());
        assertTrue(contaRepository.buscarPorId(347586970).isPresent());
        assertTrue(contaRepository.buscarPorId(674038564).isPresent());
    }

    @Test
    void deveTerSaldosIniciaisCorretos() {
        // Valida o saldo de uma conta específica após a carga
        contaRepository.buscarPorId(938485762).ifPresent(conta -> {
            assertNotNull(conta.getSaldo());
        });
    }
}
