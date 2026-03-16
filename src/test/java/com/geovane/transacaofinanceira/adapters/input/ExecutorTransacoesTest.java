package com.geovane.transacaofinanceira.adapters.input;

import com.geovane.transacaofinanceira.domain.model.Transacao;
import com.geovane.transacaofinanceira.ports.input.ExecutarTransacaoUseCase;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.mockito.Mockito.*;

class ExecutorTransacoesTest {

    @Test
    void deveChamarUseCaseParaCadaTransacaoDaLista() {
        ExecutarTransacaoUseCase useCase = mock(ExecutarTransacaoUseCase.class);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorTransacoes executor = new ExecutorTransacoes(useCase, executorService);

        List<Transacao> transacoes = List.of(
                new Transacao(1, 1, 2, new BigDecimal("100")),
                new Transacao(2, 2, 3, new BigDecimal("200"))
        );

        executor.executarTransacoes(transacoes);

        verify(useCase, timeout(1000).times(2)).executar(any(Transacao.class));
    }
}
