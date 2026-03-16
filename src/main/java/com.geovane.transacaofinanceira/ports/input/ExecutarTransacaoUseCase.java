package com.geovane.transacaofinanceira.ports.input;

import com.geovane.transacaofinanceira.domain.model.Transacao;

public interface ExecutarTransacaoUseCase {

    void executar(Transacao transacao);

}