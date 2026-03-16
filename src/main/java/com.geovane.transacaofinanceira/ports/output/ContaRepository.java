package com.geovane.transacaofinanceira.ports.output;

import com.geovane.transacaofinanceira.domain.model.Conta;

import java.util.Optional;

public interface ContaRepository {

    Optional <Conta> buscarPorId(long id); // Mudança aqui


    void salvar(Conta conta);

}