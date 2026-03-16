package com.geovane.transacaofinanceira.adapters.output;

import com.geovane.transacaofinanceira.domain.model.Conta;
import com.geovane.transacaofinanceira.ports.output.ContaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RepositorioContaEmMemoria implements ContaRepository {

    private final Map<Long, Conta> contas = new ConcurrentHashMap<>();

    @Override
    public Conta buscarPorId(long id) {
        return contas.get(id);
    }

    @Override
    public void salvar(Conta conta) {
        contas.put(conta.getId(), conta);
    }

}
