package com.geovane.transacaofinanceira.adapters.input;

import com.geovane.transacaofinanceira.domain.model.Transacao;
import com.geovane.transacaofinanceira.ports.input.ExecutarTransacaoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExecutorTransacoes {

    private final ExecutarTransacaoUseCase executarTransacaoUseCase;
    private final ExecutorService executorService;

    public void executarTransacoes(List<Transacao> transacoes) {

        log.info("Iniciando execução de {} transações", transacoes.size());

        for (Transacao transacao : transacoes) {

            executorService.submit(() ->
                    executarTransacaoUseCase.executar(transacao)
            );

        }

        executorService.shutdown();
    }
}