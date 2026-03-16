package com.geovane.transacaofinanceira.config;

import com.geovane.transacaofinanceira.adapters.input.ExecutorTransacoes;
import com.geovane.transacaofinanceira.domain.model.Conta;
import com.geovane.transacaofinanceira.domain.model.Transacao;
import com.geovane.transacaofinanceira.ports.output.ContaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class InicializadorDados {

    @Bean
    CommandLineRunner iniciar(ContaRepository contaRepository, ExecutorTransacoes executorTransacoes) {
        return args -> {
            log.info("Iniciando carga inicial e processamento de transações...");

            try {
                contaRepository.salvar(new Conta(938485762L, 1000));
                contaRepository.salvar(new Conta(2147483649L, 0));
                contaRepository.salvar(new Conta(347586970L, 1500));
                contaRepository.salvar(new Conta(238596054L, 2000));
                contaRepository.salvar(new Conta(573659065L, 500));
                contaRepository.salvar(new Conta(563856300L, 100));
                contaRepository.salvar(new Conta(675869708L, 300));
                contaRepository.salvar(new Conta(674038564L, 50));

                List<Transacao> transacoes = List.of(
                        new Transacao(1, 938485762L, 2147483649L, 150),
                        new Transacao(2, 2147483649L, 210385733L, 149),
                        new Transacao(3, 347586970L, 238596054L, 1100),
                        new Transacao(4, 238596054L, 573659065L, 2000),
                        new Transacao(5, 238596054L, 674038564L, 1489),
                        new Transacao(6, 573659065L, 563856300L, 49),
                        new Transacao(7, 563856300L, 938485762L, 200),
                        new Transacao(8, 573659065L, 675869708L, 150)
                );

                executorTransacoes.executarTransacoes(transacoes);
                log.info("Processamento finalizado com sucesso. Total de transações: {}", transacoes.size());

            } catch (Exception e) {
                log.error("Falha fatal na inicialização: {}", e.getMessage());
            }
        };
    }
}
