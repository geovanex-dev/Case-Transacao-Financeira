package com.geovane.transacaoFinanceira.application.service;

import com.geovane.transacaofinanceira.application.service.ServicoTransacao;
import com.geovane.transacaofinanceira.domain.model.Conta;
import com.geovane.transacaofinanceira.domain.model.Transacao;
import com.geovane.transacaofinanceira.ports.output.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ServicoTransacaoTest {

    private ContaRepository contaRepository;
    private ServicoTransacao servicoTransacao;

    @BeforeEach
    void setup() {
        contaRepository = mock(ContaRepository.class);
        servicoTransacao = new ServicoTransacao(contaRepository);
    }

    @Test
    void deveExecutarTransferenciaComSaldoSuficiente() {

        Conta origem = new Conta(1, 1000);
        Conta destino = new Conta(2, 500);

        when(contaRepository.buscarPorId(1)).thenReturn(origem);
        when(contaRepository.buscarPorId(2)).thenReturn(destino);

        Transacao transacao = new Transacao(100, 1, 2, 200);
        servicoTransacao.executar(transacao);

        assertEquals(800, origem.getSaldo());
        assertEquals(700, destino.getSaldo());
    }

    @Test
    void naoDeveExecutarTransferenciaSemSaldo() {
        Conta origem = new Conta(1, 100);
        Conta destino = new Conta(2, 500);

        when(contaRepository.buscarPorId(1)).thenReturn(origem);
        when(contaRepository.buscarPorId(2)).thenReturn(destino);

        Transacao transacao = new Transacao(200, 1, 2, 200);

        servicoTransacao.executar(transacao);

        assertEquals(100, origem.getSaldo());
        assertEquals(500, destino.getSaldo());
    }
}
