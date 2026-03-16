package com.geovane.transacaofinanceira.application.service;

import com.geovane.transacaofinanceira.domain.model.Conta;
import com.geovane.transacaofinanceira.domain.model.Transacao;
import com.geovane.transacaofinanceira.ports.output.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Optional;

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
        Conta origem = new Conta(1, new BigDecimal("1000"));
        Conta destino = new Conta(2, new BigDecimal("500"));

        when(contaRepository.buscarPorId(1)).thenReturn(Optional.of(origem));
        when(contaRepository.buscarPorId(2)).thenReturn(Optional.of(destino));

        Transacao transacao = new Transacao(100, 1, 2, new BigDecimal("200"));
        servicoTransacao.executar(transacao);

        assertEquals(0, new BigDecimal("800").compareTo(origem.getSaldo()));
        assertEquals(0, new BigDecimal("700").compareTo(destino.getSaldo()));
    }

    @Test
    void naoDeveExecutarTransferenciaSemSaldo() {
        Conta origem = new Conta(1, new BigDecimal("100"));
        Conta destino = new Conta(2, new BigDecimal("500"));

        when(contaRepository.buscarPorId(1)).thenReturn(Optional.of(origem));
        when(contaRepository.buscarPorId(2)).thenReturn(Optional.of(destino));

        Transacao transacao = new Transacao(200, 1, 2, new BigDecimal("200"));
        servicoTransacao.executar(transacao);

        assertEquals(0, new BigDecimal("100").compareTo(origem.getSaldo()));
        assertEquals(0, new BigDecimal("500").compareTo(destino.getSaldo()));
    }
}
