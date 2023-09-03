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
    private static final String NOME_DO_CLIENTE = "nome do cliente: ";
    private static final String DOCUMENTO_DO_CLIENTE = "documento do cliente: ";
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
        System.out.print("| > ");
        Scanner scanner = new Scanner(System.in);
        String opcao = scanner.nextLine();

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

        if (tipoConta.equalsIgnoreCase("C")){
            System.out.print("| > Qual é o nome do cliente");
            String nome = scanner.nextLine();
            for (Conta conta : contaCorrentes) {
                if (conta.getNome().equals(nome)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| > Nome: " + conta.getNome());
                    System.out.println("| > CPF: " + conta.getCpf());
                    System.out.println("| > Agencia: " + conta.getCodigoDaAgencia());
                    System.out.println("| > Numero da Conta: " + conta.getNumeroDaConta());
                    System.out.println("| > Valor: " + conta.getValorDeDinheiroEmConta());
                    menuDeOpcoes();
                } else {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                    Nome não encontrado                  |");
                    menuDeOpcoes();
                }
            }
        } else if (tipoConta.equalsIgnoreCase("P")) {
            System.out.print("| > Qual é o nome do cliente");
            String nome = scanner.nextLine();
            for (Conta conta : contaPoupancas) {
                if (conta.getNome().equals(nome)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| > Nome: " + conta.getNome());
                    System.out.println("| > CPF: " + conta.getCpf());
                    System.out.println("| > Agencia: " + conta.getCodigoDaAgencia());
                    System.out.println("| > Numero da Conta: " + conta.getNumeroDaConta());
                    System.out.println("| > Valor: " + conta.getValorDeDinheiroEmConta());
                    menuDeOpcoes();
                } else {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                    Nome não encontrado                  |");
                    menuDeOpcoes();
                }
            }
        } else {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                     Conta Inválida                      |");
            menuDeOpcoes();
        }

    }

    public static void cadastrarCliente() throws IOException {
        Long cpf;
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                     Cadastrar Cliente                   |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Qual é o cpf do cliente: ");
        cpf = scanner.nextLong();
        if (String.valueOf(cpf).length() != 11) {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|      CPF inválido, o mesmo deve possuir 11 digitos       |");
            menuDeOpcoes();
        }
        PessoaFisica pessoaFisica1 = new PessoaFisica(setNome(NOME_DO_CLIENTE), cpf);
        pessoaFisicas.add(pessoaFisica1);
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                  Cadastro Confirmado                    |");
        menuDeOpcoes();
    }
    public static void criarContaCorrente() throws IOException {

        Random randomCorrente = new Random();
        int numeroDaContaCorrente = randomCorrente.nextInt(1000);

        Scanner scanner1 = new Scanner(System.in);

        String nomeQueSeraCadastradoNaContaCorrente;
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                Cadastrar Conta Corrente                 |");
        System.out.println("+---------------------------------------------------------+");
        System.out.println("| > Qual é o nome do cliente: ");
        nomeQueSeraCadastradoNaContaCorrente = scanner1.nextLine();

        for (PessoaFisica pessoaFisica : pessoaFisicas) {
            if (pessoaFisica.getNome().equals(nomeQueSeraCadastradoNaContaCorrente)) {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                      Nome encontrado                    |");
                ContaCorrente contaCorrente1 = new ContaCorrente(pessoaFisica.getNome(), pessoaFisica.getCpf(), setDocumento(DOCUMENTO_DO_CLIENTE), 0001, setSenha(), setValorInicialDepositadoEmConta(), numeroDaContaCorrente);
                contaCorrentes.add(contaCorrente1);
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                Conta Corrente Cadastrada                |");
                menuDeOpcoes();
            } else {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                    Nome não encontrado                  |");
                menuDeOpcoes();
            }
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
        nomeQueSeraCadastradoNaContaPoupanca = String.valueOf(setNome(NOME_DO_CLIENTE));

        for (PessoaFisica pessoaFisica : pessoaFisicas) {
            if (pessoaFisica.getNome().equals(nomeQueSeraCadastradoNaContaPoupanca)) {
                ContaPoupanca contaPoupanca1 = new ContaPoupanca(pessoaFisica.getNome(), pessoaFisica.getCpf(), setDocumento(DOCUMENTO_DO_CLIENTE), 0001, setSenha(), setValorInicialDepositadoEmConta(), numeroDaContaPoupanca);
                contaPoupancas.add(contaPoupanca1);
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                 Conta Poupanca Cadastrada!!!            |");
                menuDeOpcoes();
            } else {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                    Nome não encontrado                  |");
                menuDeOpcoes();
            }
        }
    }
    private static Conta selecionarTipoConta(List<Conta> contas, String tipo) {
        for (Conta c : contas) {
            if ((tipo.equalsIgnoreCase("corrente") && c instanceof ContaCorrente) || (tipo.equalsIgnoreCase("poupanca") && c instanceof ContaPoupanca)) {
                return c;
            }
        }
        return null;
    }
    public static void sacar() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                    Realizar Saque                       |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Qual e o nome do cliente: ");
        String nomeDoCliente = scanner.nextLine();

        System.out.print("| > Qual e o nome do cliente: ");
        String documentoCliente = scanner.nextLine();

        Scanner scanner2 = new Scanner(System.in);
        for (ContaPoupanca contaPoupanca : contaPoupancas) {
            if (contaPoupanca.getNome().equals(nomeDoCliente)) {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|               Qual é o tipo de conta?                   |");
                System.out.println("|                   C - corrente                          |");
                System.out.println("|                   P - poupanca                          |");
                System.out.print("| > ");
                String tipoConta = reader.readLine();

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual é o codigo da agencia ?");
                int codAgencia = Integer.parseInt(reader.readLine());

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual é o numero da conta?");
                int numeroDaConta = Integer.parseInt(reader.readLine());

                Conta conta = null;
                if (tipoConta.equalsIgnoreCase("C")) {
                    for (Conta conta1 : contaCorrentes) {
                        if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                            conta = conta1;
                            break;
                        }
                    }
                } else if (tipoConta.equalsIgnoreCase("P")) {
                    for (Conta conta1 : contaPoupancas) {
                        if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                            conta = conta1;
                            break;
                        }
                    }
                } else if (conta == null) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                  Conta não encontrada                   |");
                }

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual o valor de saque ?");
                Double valorSaque = Double.parseDouble(reader.readLine());

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual é a senha: ");
                int senha = Integer.parseInt(reader.readLine());

                if (conta.fazerSaque(valorSaque, documentoCliente, senha)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                    Saque Confirmado                     |");
                } else {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|            Saque nao realizado, tente novamente         |");
                    menuDeOpcoes();
                }
            } else {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                   Documento inválido                    |");
                menuDeOpcoes();
            }
        }
    }
    public static void depositar() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                    Realizar Deposito                    |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Qual e o nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("| > Qual e o documento do cliente: ");
        String documentoCliente = scanner.nextLine();

        Scanner scanner2 = new Scanner(System.in);
        for (ContaPoupanca contaPoupanca : contaPoupancas) {
            if (contaPoupanca.getNome().equals(nomeCliente)) {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|               Qual é o tipo de conta?                   |");
                System.out.println("|                   C - corrente                          |");
                System.out.println("|                   P - poupanca                          |");
                System.out.print("| > ");
                String tipoConta = reader.readLine();

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual é o codigo da agencia ?");
                int codAgencia = Integer.parseInt(reader.readLine());

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual é o numero da conta?");
                int numeroDaConta = Integer.parseInt(reader.readLine());

                Conta conta = null;
                if (tipoConta.equalsIgnoreCase("C")) {
                    for (Conta conta1 : contaCorrentes) {
                        if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                            conta = conta1;
                            break;
                        }
                    }
                } else if (tipoConta.equalsIgnoreCase("P")) {
                    for (Conta conta1 : contaPoupancas) {
                        if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                            conta = conta1;
                            break;
                        }
                    }
                } else if (conta == null) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                Conta não encontrada                     |");
                }

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual o valor de saque ?");
                Double valorSaque = Double.parseDouble(reader.readLine());

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual é a senha: ");
                int senha = Integer.parseInt(reader.readLine());

                if (conta.fazerDeposito(valorSaque, documentoCliente, senha)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                    Deposito Confirmado                  |");
                } else {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|        Deposito nao realizado, tente novamente          |");
                    menuDeOpcoes();
                }
            } else {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                    Documento inválido                   |");
                menuDeOpcoes();
            }
        }
    }
    public static void trasferir() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                 Realizar Transferencia                  |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Qual e o nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("| > Qual e o documento do cliente: ");
        String documentoCliente = scanner.nextLine();

        Scanner scanner2 = new Scanner(System.in);
        for (ContaPoupanca contaPoupanca : contaPoupancas) {
            if (contaPoupanca.getNome().equals(nomeCliente)) {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|               Qual é o tipo de conta?                   |");
                System.out.println("|                   C - corrente                          |");
                System.out.println("|                   P - poupanca                          |");
                System.out.println("| > ");
                String tipoConta = reader.readLine();

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual é o codigo da agencia:");
                int codAgencia = Integer.parseInt(reader.readLine());

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual é o numero da sua conta:");
                int numeroDaConta = Integer.parseInt(reader.readLine());

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual é o numero da conta destinataria:");
                int contaDestinaria = Integer.parseInt(reader.readLine());

                Conta conta = null;
                if (tipoConta.equalsIgnoreCase("C")) {
                    for (Conta conta1 : contaCorrentes) {
                        if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                            conta = conta1;
                            break;
                        }
                    }
                } else if (tipoConta.equalsIgnoreCase("P")) {
                    for (Conta conta1 : contaPoupancas) {
                        if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                            conta = conta1;
                            break;
                        }
                    }
                } else if (conta == null) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                 Conta não encontrada                    |");
                    menuDeOpcoes();
                }

                System.out.println("+---------------------------------------------------------+");
                System.out.println("|            Qual é o tipo de conta do destinario?        |");
                System.out.println("|                   C - corrente                          |");
                System.out.println("|                   P - poupanca                          |");
                System.out.println("| > ");
                String tipoContaDestinaria = reader.readLine();
                if (tipoContaDestinaria.equalsIgnoreCase("C")) {
                    tipoContaDestinaria = "corrente";
                    for (Conta conta1 : contaCorrentes) {
                        if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                            conta = conta1;
                            break;
                        }
                    }
                } else if (tipoContaDestinaria.equalsIgnoreCase("P")) {
                    tipoContaDestinaria = "poupanca";
                    for (Conta conta1 : contaPoupancas) {
                        if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                            conta = conta1;
                            break;
                        }
                    }
                }

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual é o seu cpf: ");
                long cpf = reader.read();

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual é o cpf do destinario: ");
                long cpfDestinario = reader.read();

                List<Conta> listaContaOrigem = contas.getOrDefault(cpf, new ArrayList<>());
                List<Conta> listaContaDestino = contas.getOrDefault(cpfDestinario, new ArrayList<>());

                Conta destino = selecionarTipoConta(listaContaDestino, tipoContaDestinaria);
                Conta origem = selecionarTipoConta(listaContaOrigem, tipoConta);

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual o valor da transferencia: ");
                Double valorSaque = Double.parseDouble(reader.readLine());

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| > Qual é a senha: ");
                int senha = Integer.parseInt(reader.readLine());

                if (Transferencia.transferir(destino, origem, documentoCliente, valorSaque, senha)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|              Transferencia Confirmada!!!                |");
                    menuDeOpcoes();
                } else {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|      Transferencia nao realizada, tente novamente       |");
                    menuDeOpcoes();
                }
            } else {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                    Documento inválido                   |");

                menuDeOpcoes();
            }
        }
    }

    private static Long setCpf(String mensagem) throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("| > Digite o " + mensagem);
        return Long.parseLong(reader.readLine());
    }
    private static String setNome(String mensagem) throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("| > Digite o " + mensagem);
        return reader.readLine();
    }
    private static String setDocumento(String mensagem) throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite a " + mensagem);
        return reader.readLine();
    }
    private static int setSenha() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite senha: ");
        return Integer.parseInt(reader.readLine());
    }
    private static Double setValorInicialDepositadoEmConta() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("| > Digite o valor incial da conta: ");
        return Double.parseDouble(reader.readLine());
    }
}
