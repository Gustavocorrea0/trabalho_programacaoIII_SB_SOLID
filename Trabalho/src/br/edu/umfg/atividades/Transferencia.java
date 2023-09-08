package br.edu.umfg.atividades;

import br.edu.umfg.conta.Conta;

import java.util.Objects;

public class Transferencia {
    public static boolean transferir(Conta envio, Conta destino, Double valor, int senha) {
        if (envio.getSenha() == senha && envio.getSenha() == senha && envio.getValorDeDinheiroEmConta() >= valor) {
            Saque.sacar(envio, valor, senha);
            Deposito.depositar(destino, valor, destino.getSenha());
            return true;
        } else {
            return false;
        }
    }
}
