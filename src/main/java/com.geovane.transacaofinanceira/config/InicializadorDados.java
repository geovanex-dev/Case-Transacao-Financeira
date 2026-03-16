package com.geovane.transacaofinanceira.config;

import com.geovane.transacaofinanceira.adapters.input.ExecutorTransacoes;
import com.geovane.transacaofinanceira.domain.model.Conta;
import com.geovane.transacaofinanceira.domain.model.Transacao;
import com.geovane.transacaofinanceira.ports.output.ContaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Configuration
public class InicializadorDados {

    @Bean
    CommandLineRunner iniciar(ContaRepository contaRepository, ExecutorTransacoes executorTransacoes) {
        return args -> {
            log.info("Iniciando carga inicial conforme o Case...");

            try {
                contaRepository.salvar(new Conta(938485762L, new BigDecimal("180")));
                contaRepository.salvar(new Conta(347586970L, new BigDecimal("1200")));
                contaRepository.salvar(new Conta(2147483649L, BigDecimal.ZERO));
                contaRepository.salvar(new Conta(675869708L, new BigDecimal("4900")));
                contaRepository.salvar(new Conta(238596054L, new BigDecimal("478")));
                contaRepository.salvar(new Conta(573659065L, new BigDecimal("787")));
                contaRepository.salvar(new Conta(210385733L, new BigDecimal("10")));
                contaRepository.salvar(new Conta(674038564L, new BigDecimal("400")));
                contaRepository.salvar(new Conta(563856300L, new BigDecimal("1200")));

                List<Transacao> transacoes = List.of(
                        new Transacao(1, 938485762L, 2147483649L, new BigDecimal("150")),
                        new Transacao(2, 2147483649L, 210385733L, new BigDecimal("149")),
                        new Transacao(3, 347586970L, 238596054L, new BigDecimal("1100")),
                        new Transacao(4, 675869708L, 210385733L, new BigDecimal("5300")),
                        new Transacao(5, 238596054L, 674038564L, new BigDecimal("1489")),
                        new Transacao(6, 573659065L, 563856300L, new BigDecimal("49")),
                        new Transacao(7, 938485762L, 2147483649L, new BigDecimal("44")),
                        new Transacao(8, 573659065L, 675869708L, new BigDecimal("150"))
                );

                executorTransacoes.executarTransacoes(transacoes);

                log.info("Processamento de carga e disparo finalizado.");

            } catch (Exception e) {
                log.error("Erro fatal na carga de dados: {}", e.getMessage());
            }
        };
    }
}