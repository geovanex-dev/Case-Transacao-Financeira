package com.geovane.transacaofinanceira.application.service;

import com.geovane.transacaofinanceira.domain.model.Conta;
import com.geovane.transacaofinanceira.domain.model.Transacao;
import com.geovane.transacaofinanceira.ports.input.ExecutarTransacaoUseCase;
import com.geovane.transacaofinanceira.ports.output.ContaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServicoTransacao implements ExecutarTransacaoUseCase {

    private final ContaRepository contaRepository;

    public ServicoTransacao(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public void executar(Transacao transacao) {
        try {
            Conta origem = contaRepository.buscarPorId(transacao.getContaOrigem())
                    .orElseThrow(() -> new RuntimeException("Conta origem não encontrada: " + transacao.getContaOrigem()));

            Conta destino = contaRepository.buscarPorId(transacao.getContaDestino())
                    .orElseThrow(() -> new RuntimeException("Conta destino não encontrada: " + transacao.getContaDestino()));

            Conta primeira = origem.getId() < destino.getId() ? origem : destino;
            Conta segunda = origem.getId() < destino.getId() ? destino : origem;

            synchronized (primeira) {
                synchronized (segunda) {
                    if (origem.getSaldo().compareTo(transacao.getValor()) < 0) {
                        log.warn("Transação {} cancelada: Saldo insuficiente na conta {}", transacao.getId(), origem.getId());
                        return;
                    }

                    origem.debitar(transacao.getValor());
                    destino.creditar(transacao.getValor());

                    log.info("Transação {} executada com sucesso. Origem: {} Destino: {} Valor: {}",
                            transacao.getId(), origem.getId(), destino.getId(), transacao.getValor());
                }
            }
        } catch (Exception e) {
            log.error("Falha ao processar Transação {}: {}", transacao.getId(), e.getMessage());
        }
    }
}