package com.geovane.transacaofinanceira.ports.output;

import com.geovane.transacaofinanceira.domain.model.Conta;

public interface ContaRepository {

    Conta buscarPorId(long id);

    void salvar(Conta conta);

}