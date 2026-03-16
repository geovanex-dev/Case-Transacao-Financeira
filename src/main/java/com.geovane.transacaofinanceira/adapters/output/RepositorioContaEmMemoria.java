package com.geovane.transacaofinanceira.adapters.output;

import com.geovane.transacaofinanceira.domain.model.Conta;
import com.geovane.transacaofinanceira.ports.output.ContaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RepositorioContaEmMemoria implements ContaRepository {

    private final Map<Long, Conta> contas = new ConcurrentHashMap<>();

    @Override
    public Optional<Conta> buscarPorId(long id) {
        return Optional.ofNullable(contas.get(id));
    }

    @Override
    public void salvar(Conta conta) {
        contas.put(conta.getId(), conta);
    }

}
