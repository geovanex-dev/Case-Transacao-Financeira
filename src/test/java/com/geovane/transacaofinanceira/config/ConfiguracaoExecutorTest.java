package com.geovane.transacaofinanceira.config;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.*;

class ConfiguracaoExecutorTest {

    @Test
    void deveCriarExecutorService() {

        ConfiguracaoExecutor configuracao = new ConfiguracaoExecutor();

        ExecutorService executor = configuracao.executorService();

        assertNotNull(executor);
    }
}