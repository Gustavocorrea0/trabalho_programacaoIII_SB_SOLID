package br.edu.umfg.atividades;

import br.edu.umfg.conta.Conta;

public class Saque {
    public static boolean sacar(Conta conta, Double valor, int senha){
        if (conta.getSenha() == senha && conta.getValorDeDinheiroEmConta() >= valor){
            conta.setValorDeDinheiroEmConta(conta.getValorDeDinheiroEmConta() - valor);
            return true;
        } else {
            return false;
        }
    }
}
