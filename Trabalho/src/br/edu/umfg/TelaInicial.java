package br.edu.umfg;

import br.edu.umfg.atividades.Transferencia;
import br.edu.umfg.cliente.PessoaFisica;
import br.edu.umfg.conta.Conta;
import br.edu.umfg.conta.ContaCorrente;
import br.edu.umfg.conta.ContaPoupanca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TelaInicial {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Scanner scanner = new Scanner(System.in);
    //private static final String DOCUMENTO_DO_CLIENTE = "documento do cliente: ";
    static List<PessoaFisica> pessoaFisicas = new ArrayList<>();
    static List<ContaCorrente> contaCorrentes = new ArrayList<>();
    static List<ContaPoupanca> contaPoupancas = new ArrayList<>();
    static HashMap<String, List<Conta>> contas = new HashMap<>();

    public static void menuDeOpcoes() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                      Menu Bancario                      |");
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|        1 - Cadastrar Cliente Pessoa Fisica              |");
        System.out.println("|        2 - Cadastrar Conta Corrente                     |");
        System.out.println("|        3 - Cadastrar Conta Poupanca                     |");
        System.out.println("|        4 - Efetuar Deposito                             |");
        System.out.println("|        5 - Efetuar Saque                                |");
        System.out.println("|        6 - Efetuar Transferencia                        |");
        System.out.println("|        7 - Verificar Cliente                            |");
        System.out.println("|        9 - Sair                                         |");
        System.out.println("+---------------------------------------------------------+");
        System.out.println("| > Qual a opção desejada: ");
        String opcao = reader.readLine();

        switch (opcao) {
            case "1":
                cadastrarCliente();
                break;
            case "2":
                criarContaCorrente();
                break;
            case "3":
                criarContaPoupanca();
                break;
            case "4":
                depositar();
                break;
            case "5":
                sacar();
                break;
            case "6":
                trasferir();
                break;
            case "7":
                verificarCliente();
                break;
            case "9":
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                        Saindo...                        |");
                System.exit(0);
                break;
            default:
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                    Opcao invalida                       |");
                menuDeOpcoes();
                break;
        }
    }

    public static void cadastrarCliente() throws IOException {
        Long cpf;
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                   Cadastrar Cliente                     |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Qual é o nome do cliente: ");
        String nomeCliente = reader.readLine();
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Qual é o cpf do cliente(sem pontos): ");
        cpf = Long.valueOf(reader.readLine());

        if (String.valueOf(cpf).length() != 11) {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|      CPF inválido, o mesmo deve possuir 11 digitos      |");
            menuDeOpcoes();
        }
        PessoaFisica pessoaFisica1 = new PessoaFisica(nomeCliente, cpf);
        pessoaFisicas.add(pessoaFisica1);
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                  Cadastro Confirmado                    |");
        menuDeOpcoes();
    }

    public static void criarContaCorrente() throws IOException {
        Random randomCorrente = new Random();
        int numeroDaContaCorrente = randomCorrente.nextInt(1000);

        String nomeQueSeraCadastradoNaContaCorrente;
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                Cadastrar Conta Corrente                 |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Qual é o nome do cliente: ");
        nomeQueSeraCadastradoNaContaCorrente = reader.readLine();

        boolean nomeEncontrado = false;
        PessoaFisica clienteEncontrado = null;

        for (PessoaFisica pessoaFisica : pessoaFisicas) {
            if (pessoaFisica.getNome().equals(nomeQueSeraCadastradoNaContaCorrente)) {
                nomeEncontrado = true;
                clienteEncontrado = pessoaFisica;
                break;
            }
        }

        if (nomeEncontrado) {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                    Nome encontrado                      |");
            ContaCorrente contaCorrente1 = new ContaCorrente(clienteEncontrado.getNome(), clienteEncontrado.getCpf(), 0001, setSenha(), setValorInicialDepositadoEmConta(), numeroDaContaCorrente);
            contaCorrentes.add(contaCorrente1);

            for (Conta conta : contaCorrentes) {
                if (conta.getNome().equals(nomeQueSeraCadastradoNaContaCorrente)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                Conta Corrente Cadastrada                |");
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| > Nome: " + conta.getNome());
                    System.out.println("| > CPF: " + conta.getCpf());
                    System.out.println("| > Agencia: " + conta.getCodigoDaAgencia());
                    System.out.println("| > Numero da Conta: " + conta.getNumeroDaConta());
                    System.out.println("| > Valor: " + conta.getValorDeDinheiroEmConta());
                    menuDeOpcoes();
                }
            }
        } else {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                    Nome não encontrado                  |");
            menuDeOpcoes();
        }
    }

    public static void criarContaPoupanca() throws IOException {
        Random randomPoupanca = new Random();
        int numeroDaContaPoupanca = randomPoupanca.nextInt(1000);

        String nomeQueSeraCadastradoNaContaPoupanca;
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                Cadastrar Conta Poupanca                 |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Qual é o nome do cliente: ");
        nomeQueSeraCadastradoNaContaPoupanca = reader.readLine();

        boolean nomeEncontrado = false;
        PessoaFisica clienteEncontrado = null;

        for (PessoaFisica pessoaFisica : pessoaFisicas) {
            if (pessoaFisica.getNome().equals(nomeQueSeraCadastradoNaContaPoupanca)) {
                nomeEncontrado = true;
                clienteEncontrado = pessoaFisica;
                break;
            }
        }

        if (nomeEncontrado) {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                   Nome encontrado                       |");
            ContaPoupanca contaPoupanca1 = new ContaPoupanca(clienteEncontrado.getNome(), clienteEncontrado.getCpf(), 0001, setSenha(), setValorInicialDepositadoEmConta(), numeroDaContaPoupanca);
            contaPoupancas.add(contaPoupanca1);

            for (Conta conta : contaPoupancas) {
                if (conta.getNome().equals(nomeQueSeraCadastradoNaContaPoupanca)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                 Conta Poupanca Cadastrada!!!            |");
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| > Nome: " + conta.getNome());
                    System.out.println("| > CPF: " + conta.getCpf());
                    System.out.println("| > Agencia: " + conta.getCodigoDaAgencia());
                    System.out.println("| > Numero da Conta: " + conta.getNumeroDaConta());
                    System.out.println("| > Valor: " + conta.getValorDeDinheiroEmConta());
                    menuDeOpcoes();
                }
            }
            menuDeOpcoes();
        } else {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                    Nome não encontrado                  |");
            menuDeOpcoes();
        }
    }

    private static Conta selecionarTipoConta(List<Conta> contas, String tipo) {
        for (Conta c : contas) {
            if ((tipo.equalsIgnoreCase("C") && c instanceof ContaCorrente) || (tipo.equalsIgnoreCase("P") && c instanceof ContaPoupanca)) {
                return c;
            }
        }
        return null;
    }

    public static void depositar() throws IOException {
        try {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                    Realizar Deposito                    |");
            System.out.println("+---------------------------------------------------------+");
            System.out.print("| > Qual é o nome do cliente: ");
            String nomeCliente = reader.readLine();

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| > Qual é o CPF(sem pontos): ");
            Long cpfCliente = Long.valueOf(reader.readLine());

            System.out.println("+---------------------------------------------------------+");
            System.out.println("|               Qual é o tipo de conta?                   |");
            System.out.println("|                   C - corrente                          |");
            System.out.println("|                   P - poupanca                          |");
            System.out.print("| > ");
            String tipoConta = reader.readLine();

            Conta conta;
            if (tipoConta.equalsIgnoreCase("P")) {
                for (ContaPoupanca contaPoupanca : contaPoupancas) {
                    if (contaPoupanca.getNome().equals(nomeCliente)) {
                        System.out.println("+---------------------------------------------------------+");
                        System.out.println("|                      Nome encontrado                    |");
                        System.out.println("+---------------------------------------------------------+");
                        System.out.print("| > Qual é o codigo da agencia: ");
                        int codAgencia = Integer.parseInt(reader.readLine());

                        System.out.println("+---------------------------------------------------------+");
                        System.out.print("| > Qual é o numero da conta: ");
                        int numeroDaConta = Integer.parseInt(reader.readLine());

                        for (Conta conta1 : contaPoupancas) {
                            if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta && conta1.getCpf() == cpfCliente) {
                                //conta = conta1;
                                System.out.println("+---------------------------------------------------------+");
                                System.out.print("| > Qual é o valor de deposito: ");
                                Double valorSaque = Double.parseDouble(reader.readLine());

                                System.out.println("+---------------------------------------------------------+");
                                System.out.print("| > Qual é a senha: ");
                                int senha = Integer.parseInt(reader.readLine());

                                if (conta1.fazerDeposito(valorSaque, senha)) {
                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.println("|                    Deposito Confirmado                  |");
                                    menuDeOpcoes();
                                } else {
                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.println("|        Deposito nao realizado, tente novamente          |");
                                    menuDeOpcoes();
                                }
                                break;
                            }
                        }
                    }
                }
            } else if (tipoConta.equalsIgnoreCase("C")) {
                for (ContaCorrente contaCorrente : contaCorrentes) {
                    if (contaCorrente.getNome().equals(nomeCliente)) {
                        System.out.println("+---------------------------------------------------------+");
                        System.out.println("|                      Nome encontrado                    |");
                        System.out.println("+---------------------------------------------------------+");
                        System.out.print("| > Qual é o codigo da agencia: ");
                        int codAgencia = Integer.parseInt(reader.readLine());

                        System.out.println("+---------------------------------------------------------+");
                        System.out.print("| > Qual é o numero da conta: ");
                        int numeroDaConta = Integer.parseInt(reader.readLine());

                        for (Conta conta1 : contaCorrentes) {
                            if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta && conta1.getCpf() == cpfCliente) {
                                conta = conta1;
                                System.out.println("+---------------------------------------------------------+");
                                System.out.print("| > Qual é o valor de deposito: ");
                                Double valorSaque = Double.parseDouble(reader.readLine());

                                System.out.println("+---------------------------------------------------------+");
                                System.out.print("| > Qual é a senha: ");
                                int senha = Integer.parseInt(reader.readLine());

                                if (conta.fazerDeposito(valorSaque, senha)) {
                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.println("|                    Deposito Confirmado                  |");
                                    menuDeOpcoes();
                                } else {
                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.println("|        Deposito nao realizado, tente novamente          |");
                                    menuDeOpcoes();
                                }
                                break;
                            }
                        }
                    }
                }
            } else {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                Conta não encontrada                     |");
                menuDeOpcoes();
            }
        } catch (IOException ex) {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("| Falha de Digitação | Tipo : " + ex);
            menuDeOpcoes();
        }
    } //DEPOSITO OK

    public static void sacar() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                     Realizar Saque                      |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Qual é o nome do cliente: ");
        String nomeCliente = reader.readLine();

        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Qual é o CPF(sem pontos): ");
        Long cpfCliente = Long.valueOf(reader.readLine());

        System.out.println("+---------------------------------------------------------+");
        System.out.println("|               Qual é o tipo de conta?                   |");
        System.out.println("|                   C - corrente                          |");
        System.out.println("|                   P - poupanca                          |");
        System.out.print("| > ");
        String tipoConta = reader.readLine();

        Conta conta;
        if (tipoConta.equalsIgnoreCase("P")) {
            for (ContaPoupanca contaPoupanca : contaPoupancas) {
                if (contaPoupanca.getNome().equals(nomeCliente)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                      Nome encontrado                    |");
                    System.out.println("+---------------------------------------------------------+");
                    System.out.print("| > Qual é o codigo da agencia: ");
                    int codAgencia = Integer.parseInt(reader.readLine());

                    System.out.println("+---------------------------------------------------------+");
                    System.out.print("| > Qual é o numero da conta: ");
                    int numeroDaConta = Integer.parseInt(reader.readLine());

                    for (Conta conta1 : contaPoupancas) {
                        if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta && conta1.getCpf() == cpfCliente) {
                            conta = conta1;
                            System.out.println("+---------------------------------------------------------+");
                            System.out.print("| > Qual é o valor do saque: ");
                            Double valorSaque = Double.parseDouble(reader.readLine());

                            System.out.println("+---------------------------------------------------------+");
                            System.out.print("| > Qual é a senha: ");
                            int senha = Integer.parseInt(reader.readLine());

                            if (conta1.fazerSaque(valorSaque, senha)) {
                                System.out.println("+---------------------------------------------------------+");
                                System.out.println("|                    Saque Confirmado                     |");
                                menuDeOpcoes();
                            } else {
                                System.out.println("+---------------------------------------------------------+");
                                System.out.println("|           Saque nao realizado, tente novamente          |");
                                menuDeOpcoes();
                            }
                            break;
                        }
                    }
                }
            }
        } else if (tipoConta.equalsIgnoreCase("C")) {
            for (ContaCorrente contaCorrente : contaCorrentes) {
                if (contaCorrente.getNome().equals(nomeCliente)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                      Nome encontrado                    |");
                    System.out.println("+---------------------------------------------------------+");
                    System.out.print("| > Qual é o codigo da agencia: ");
                    int codAgencia = Integer.parseInt(reader.readLine());

                    System.out.println("+---------------------------------------------------------+");
                    System.out.print("| > Qual é o numero da conta: ");
                    int numeroDaConta = Integer.parseInt(reader.readLine());

                    for (Conta conta1 : contaCorrentes) {
                        if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta && conta1.getCpf() == cpfCliente) {
                            conta = conta1;
                            System.out.println("+---------------------------------------------------------+");
                            System.out.print("| > Qual é o valor saque: ");
                            Double valorSaque = Double.parseDouble(reader.readLine());

                            System.out.println("+---------------------------------------------------------+");
                            System.out.print("| > Qual é a senha: ");
                            int senha = Integer.parseInt(reader.readLine());

                            if (conta.fazerSaque(valorSaque, senha)) {
                                System.out.println("+---------------------------------------------------------+");
                                System.out.println("|                    Saque Confirmado                     |");
                                menuDeOpcoes();
                            } else {
                                System.out.println("+---------------------------------------------------------+");
                                System.out.println("|          Saque nao realizado, tente novamente           |");
                                menuDeOpcoes();
                            }
                            break;
                        }
                    }
                }
            }
        } else {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                Conta não encontrada                     |");
            menuDeOpcoes();
        }
    }

    public static void trasferir() throws IOException {

        try {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                 Realizar Transferencia                  |");
            System.out.println("+---------------------------------------------------------+");
            System.out.print("| > Qual é o seu nome: ");
            String nomeCliente = reader.readLine();

            System.out.println("+---------------------------------------------------------+");
            System.out.println("|             Qual é o tipo da sua conta?                 |");
            System.out.println("|                   C - corrente                          |");
            System.out.println("|                   P - poupanca                          |");
            System.out.print("| > ");
            String tipoConta = reader.readLine();

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| > Qual é o seu cpf: ");
            long cpf = Long.parseLong(reader.readLine());

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| > Qual é o codigo da sua agencia: ");
            int codAgencia = Integer.parseInt(reader.readLine());

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| > Qual é o numero da sua conta: ");
            int numeroDaConta = Integer.parseInt(reader.readLine());
//----------------------------------------------------------------------------------------------------------------------------------------
            if (tipoConta.equalsIgnoreCase("C")) {
                Conta conta;
                for (Conta conta1 : contaCorrentes) {
                    if (Objects.equals(conta1.getNome(), nomeCliente) && conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                        conta = conta1;
                        System.out.println("+---------------------------------------------------------+");
                        System.out.println("|            Qual é o tipo de conta do destinario?        |");
                        System.out.println("|                   C - corrente                          |");
                        System.out.println("|                   P - poupanca                          |");
                        System.out.print("| > ");
                        String tipoContaDestinaria = reader.readLine();

                        if (tipoContaDestinaria.equalsIgnoreCase("C")) {
                            for (Conta conta10 : contaCorrentes) {
                                if (Objects.equals(conta10.getNome(), nomeCliente) && conta10.getCodigoDaAgencia() == codAgencia && conta10.getNumeroDaConta() == numeroDaConta) {
                                    conta = conta10;
                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.print("| > Qual é o cpf do destinario: ");
                                    long cpfDestinario = Long.parseLong(reader.readLine());

                                    List<Conta> listaContaOrigem = contas.getOrDefault(cpf, new ArrayList<>());
                                    List<Conta> listaContaDestino = contas.getOrDefault(cpfDestinario, new ArrayList<>());

                                    Conta destino = selecionarTipoConta(listaContaDestino, tipoContaDestinaria);
                                    Conta origem = selecionarTipoConta(listaContaOrigem, tipoConta);

                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.print("| > Qual o valor da transferencia: ");
                                    Double valorSaque = Double.parseDouble(reader.readLine());

                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.print("| > Qual é a sua senha: ");
                                    int senha = Integer.parseInt(reader.readLine());

                                    //assert destino != null;
                                    if (Transferencia.transferir(destino, origem, valorSaque, senha)) {
                                        System.out.println("+---------------------------------------------------------+");
                                        System.out.println("|              Transferencia Confirmada!!!                |");
                                        menuDeOpcoes();
                                    } else {
                                        System.out.println("+---------------------------------------------------------+");
                                        System.out.println("|      Transferencia nao realizada, dados inválidos       |");
                                        menuDeOpcoes();
                                    }
                                    break;
                                }
                            }
                        } else if (tipoContaDestinaria.equalsIgnoreCase("P")) {
                            for (Conta conta11 : contaPoupancas) {
                                if (Objects.equals(conta11.getNome(), nomeCliente) && conta11.getCodigoDaAgencia() == codAgencia && conta11.getNumeroDaConta() == numeroDaConta) {
                                    conta = conta11;
                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.print("| > Qual é o cpf do destinario: ");
                                    long cpfDestinario = Long.parseLong(reader.readLine());

                                    List<Conta> listaContaOrigem = contas.getOrDefault(cpf, new ArrayList<>());
                                    List<Conta> listaContaDestino = contas.getOrDefault(cpfDestinario, new ArrayList<>());

                                    Conta destino = selecionarTipoConta(listaContaDestino, tipoContaDestinaria);
                                    Conta origem = selecionarTipoConta(listaContaOrigem, tipoConta);

                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.print("| > Qual é o valor da transferencia: ");
                                    Double valorSaque = Double.parseDouble(reader.readLine());

                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.print("| > Qual é a sua senha: ");
                                    int senha = Integer.parseInt(reader.readLine());

                                    //assert destino != null;
                                    if (Transferencia.transferir(destino, origem, valorSaque, senha)) {
                                        System.out.println("+---------------------------------------------------------+");
                                        System.out.println("|              Transferencia Confirmada!!!                |");
                                        menuDeOpcoes();
                                    } else {
                                        System.out.println("+---------------------------------------------------------+");
                                        System.out.println("|      Transferencia nao realizada, dados inválidos       |");
                                        menuDeOpcoes();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            } else if (tipoConta.equalsIgnoreCase("P")) {
                Conta conta = null;
                for (Conta conta2 : contaPoupancas) {
                    if (Objects.equals(conta2.getNome(), nomeCliente) && conta2.getCodigoDaAgencia() == codAgencia && conta2.getNumeroDaConta() == numeroDaConta) {
                        conta = conta2;

                        System.out.println("+---------------------------------------------------------+");
                        System.out.println("|            Qual é o tipo de conta do destinario?        |");
                        System.out.println("|                   C - corrente                          |");
                        System.out.println("|                   P - poupanca                          |");
                        System.out.print("| > ");
                        String tipoContaDestinaria = reader.readLine();

                        //Conta conta;

                        if (tipoContaDestinaria.equalsIgnoreCase("C")) {
                            for (Conta conta1 : contaCorrentes) {
                                if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                                    conta = conta1;
                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.print("| > Qual é o cpf do destinario: ");
                                    long cpfDestinario = Long.parseLong(reader.readLine());

                                    List<Conta> listaContaOrigem = contas.getOrDefault(cpf, new ArrayList<>());
                                    List<Conta> listaContaDestino = contas.getOrDefault(cpfDestinario, new ArrayList<>());

                                    Conta destino = selecionarTipoConta(listaContaDestino, tipoContaDestinaria);
                                    Conta origem = selecionarTipoConta(listaContaOrigem, tipoConta);

                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.print("| > Qual é o valor da transferencia: ");
                                    Double valorSaque = Double.parseDouble(reader.readLine());

                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.print("| > Qual é a sua senha: ");
                                    int senha = Integer.parseInt(reader.readLine());

                                    //assert destino != null;
                                    if (Transferencia.transferir(destino, origem, valorSaque, senha)) {
                                        System.out.println("+---------------------------------------------------------+");
                                        System.out.println("|              Transferência Confirmada!!!                |");
                                        menuDeOpcoes();
                                    } else {
                                        System.out.println("+---------------------------------------------------------+");
                                        System.out.println("|      Transferência nao realizada, dados inválidos       |");
                                        menuDeOpcoes();
                                    }
                                    break;
                                }
                            }
                        } else if (tipoContaDestinaria.equalsIgnoreCase("P")) {
                            //tipoContaDestinaria = "poupanca";
                            for (Conta conta1 : contaPoupancas) {
                                if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                                    conta = conta1;
                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.print("| > Qual é o cpf do destinario: ");
                                    long cpfDestinario = Long.parseLong(reader.readLine());

                                    List<Conta> listaContaOrigem = contas.getOrDefault(cpf, new ArrayList<>());
                                    List<Conta> listaContaDestino = contas.getOrDefault(cpfDestinario, new ArrayList<>());

                                    Conta destino = selecionarTipoConta(listaContaDestino, tipoContaDestinaria);
                                    Conta origem = selecionarTipoConta(listaContaOrigem, tipoConta);

                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.print("| > Qual é o valor da transferencia: ");
                                    Double valorSaque = Double.parseDouble(reader.readLine());

                                    System.out.println("+---------------------------------------------------------+");
                                    System.out.print("| > Qual é a sua senha: ");
                                    int senha = Integer.parseInt(reader.readLine());

                                    //assert destino != null;
                                    if (Transferencia.transferir(destino, origem, valorSaque, senha)) {
                                        System.out.println("+---------------------------------------------------------+");
                                        System.out.println("|              Transferência Confirmada!!!                |");
                                        menuDeOpcoes();
                                    } else {
                                        System.out.println("+---------------------------------------------------------+");
                                        System.out.println("|      Transferência nao realizada, dados inválidos       |");
                                        menuDeOpcoes();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
//----------------------------------------------------------------------------------------------------------------------------------------
            } else {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                 Conta não encontrada                    |");
                menuDeOpcoes();
            }

        } catch (Exception ex) {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|      Transferencia nao realizada, tente novamente       |");
            menuDeOpcoes();
            //System.out.println(ex);
        }
    }

    private static void verificarCliente() throws IOException {
        scanner = new Scanner(System.in);
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                    Verificar Cliente                    |");
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|               Qual é o tipo de conta?                   |");
        System.out.println("|                   C - corrente                          |");
        System.out.println("|                   P - poupanca                          |");
        System.out.print("| > ");
        String tipoConta = reader.readLine();

        if (tipoConta.equalsIgnoreCase("C")) {
            tipoConta = "Corrente";
            System.out.println("+---------------------------------------------------------+");
            System.out.print("| > Qual é o nome do cliente: ");
            String nome = reader.readLine();
            for (Conta conta : contaCorrentes) {
                if (conta.getNome().equals(nome)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| > Nome: " + conta.getNome());
                    System.out.println("| > CPF: " + conta.getCpf());
                    System.out.println("| > Tipo de Conta: " + tipoConta);
                    System.out.println("| > Agencia: " + conta.getCodigoDaAgencia());
                    System.out.println("| > Numero da Conta: " + conta.getNumeroDaConta());
                    System.out.println("| > Valor: " + conta.getValorDeDinheiroEmConta());
                } else {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                    Nome não encontrado                  |");
                    menuDeOpcoes();
                }
            }
            menuDeOpcoes();
        } else if (tipoConta.equalsIgnoreCase("P")) {
            tipoConta = "Poupanca";
            System.out.println("+---------------------------------------------------------+");
            System.out.print("| > Qual é o nome do cliente: ");
            String nome = reader.readLine();
            for (Conta conta : contaPoupancas) {
                if (conta.getNome().equals(nome)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| > Nome: " + conta.getNome());
                    System.out.println("| > CPF: " + conta.getCpf());
                    System.out.println("| > Tipo de Conta: " + tipoConta);
                    System.out.println("| > Agencia: " + conta.getCodigoDaAgencia());
                    System.out.println("| > Numero da Conta: " + conta.getNumeroDaConta());
                    System.out.println("| > Valor: " + conta.getValorDeDinheiroEmConta());
                } else {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                    Nome não encontrado                  |");
                }
            }
        } else {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                     Conta Inválida                      |");
        }
        menuDeOpcoes();
    }

    private static Long setCpf(String mensagem) throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite o " + mensagem);
        return Long.parseLong(reader.readLine());
    }

    private static String setNome(String mensagem) throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite o " + mensagem);
        return reader.readLine();
    }

    private static String setDocumento(String mensagem) throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite a " + mensagem);
        return reader.readLine();
    }

    private static int setSenha() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite a senha: ");
        return Integer.parseInt(reader.readLine());
    }

    private static Double setValorInicialDepositadoEmConta() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite o valor incial da conta: ");
        return Double.parseDouble(reader.readLine());
    }
}
