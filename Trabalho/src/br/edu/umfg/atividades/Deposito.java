package br.edu.umfg.atividades;
import br.edu.umfg.conta.Conta;

public class Deposito {
    public static boolean depositar(Conta conta, Double valor, int senha){
        if (conta.getSenha() == senha && valor > 0){
            conta.setValorDeDinheiroEmConta(conta.getValorDeDinheiroEmConta() + valor);
            return true;
        } else {
            return false;
        }
    }
}
