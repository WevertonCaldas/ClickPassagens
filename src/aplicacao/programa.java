package aplicacao;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Random;
import br.edu.catolica.classes.*;
import java.util.ArrayList;

public class programa extends ObjetoPassagem {


    public programa() throws RemoteException {
        super();
    }

    public static void main(String args[]) {

        try {
            Corrida obj = (Corrida) Naming.lookup("//" + "localhost:2021" + "/Corrida");

            int op = 0;

            System.out.println(
                    "1 - cadastrar cliente \n" +
                    "2 - cadastrar prestador de serviços \n" +
                    "3 - Login do cliente \n" +
                    "4 - Login do prestador de serviços"
            );

            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o número de uma opção: ");
            op = Integer.parseInt(scanner.nextLine());

            if (op == 1) {
                System.out.print("nome: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Senha: ");
                String senha = scanner.nextLine();

                boolean cadastrar = obj.cadastrarCliente(new Cliente(nome, cpf, senha));

                if (cadastrar){
                    System.out.println("Digite 'Sair' para fechar ou qualquer tecla para continuar");
                    String opcao = scanner.nextLine();

                    while(!opcao.equalsIgnoreCase("Sair")){
                        ArrayList <Passagem> passagem = obj.mostrarPassagem();
                        System.out.println("PASSAGENS");
                        for (int i=0; i < passagem.size(); i++){
                            System.out.println("Código da passagem: " + passagem.get(i).getId());
                            System.out.println("Nome: " + passagem.get(i).getNome());
                            System.out.println("Origem: " + passagem.get(i).getOrigem());
                            System.out.println("Destino: " + passagem.get(i).getDestino());
                            System.out.println("Valor: " + passagem.get(i).getValor());
                            System.out.println("Data: " + passagem.get(i).getData()+ "       " + "Hora: " + passagem.get(i).getHora());
                            System.out.println("---------------------------------------------");
                        }

                        System.out.print("Digite o código da passagem que você deseja comprar: ");
                        int codigo = Integer.parseInt(scanner.nextLine());
                        System.out.print("Digite seu CPF: ");
                        cpf  = scanner.nextLine();
                        boolean compra = obj.comprarPassagem(codigo,cpf);
                        if (compra){
                            System.out.println("*****PASSAGEM COMPRADA COM SUCESSO!!!*****\n");
                        }
                        System.out.println("Digite 'Sair' ou 'Continuar' para comprar mais passagens: ");
                        opcao = scanner.nextLine();
                    }
                }

            }

            else if (op == 2) {
                System.out.println("nome: ");
                String nome = scanner.nextLine();
                System.out.println("registro cnh: ");
                String registrocnh = scanner.nextLine();
                System.out.println("Informe seu CPF: ");
                String cpf = scanner.nextLine();
                System.out.println("Informe uma senha: ");
                String senha = scanner.nextLine();

                boolean cadastro = obj.cadastrarPrestadorServico(new PrestadorDeServico(nome, registrocnh, cpf, senha));

                if (cadastro){
                    System.out.println("Digite 'Sair' para fechar ou qualquer tecla para cadastrar uma passagem");
                    String opcao = scanner.nextLine();

                    while(!opcao.equalsIgnoreCase("Sair")){
                        int id = new Random().nextInt();
                        System.out.println("Origem: ");
                        String origem = scanner.nextLine();
                        System.out.println("Destino: ");
                        String destino = scanner.nextLine();
                        System.out.println("Nome da passagem: ");
                        String nomePassagem = scanner.nextLine();
                        System.out.println("valor: ");
                        Float valor = Float.parseFloat(scanner.nextLine());
                        System.out.println("Data: ");
                        String data = scanner.nextLine();
                        System.out.println("Hora: ");
                        String hora = scanner.nextLine();

                        boolean cadastrar = obj.cadastrarPassagem(new Passagem(id, origem, destino, nomePassagem, valor, data, hora, cpf));

                        if(cadastrar) {
                            System.out.println("***** PASSAGEM CADASTRADA COM SUCESSO!!! *****");
                        }

                        System.out.println("Digite 'Sair' para fechar ou qualquer tecla para continuar");
                        opcao = scanner.nextLine();
                    }
                }
            }

            else if (op == 3){
                System.out.println("CPF: ");
                String cpf = scanner.nextLine();
                System.out.println("Senha: ");
                String senha = scanner.nextLine();
                boolean login = obj.loginCliente(new Cliente(cpf,senha));

                while (!login){
                    System.out.println("xxxxxx DADOS INCORRETOS. xxxxxx\n----Digite os dados novamente----");
                    System.out.println("CPF: ");
                    cpf = scanner.nextLine();
                    System.out.println("Senha: ");
                    senha = scanner.nextLine();
                    login = obj.loginCliente(new Cliente(cpf,senha));
                }
                System.out.println("Digite 'Sair' para fechar ou qualquer tecla para continuar");
                String opcao = scanner.nextLine();

                while(!opcao.equalsIgnoreCase("Sair")){
                    ArrayList <Passagem> passagem = obj.mostrarPassagem();
                    System.out.println("PASSAGENS");
                    for (int i=0; i < passagem.size(); i++){
                        System.out.println("Código da passagem: " + passagem.get(i).getId());
                        System.out.println("Nome: " + passagem.get(i).getNome());
                        System.out.println("Origem: " + passagem.get(i).getOrigem());
                        System.out.println("Destino: " + passagem.get(i).getDestino());
                        System.out.println("Valor: " + passagem.get(i).getValor());
                        System.out.println("Data: " + passagem.get(i).getData()+ "       " + "Hora: " + passagem.get(i).getHora());
                        System.out.println("---------------------------------------------");
                    }

                    System.out.print("Digite o código da passagem que você deseja comprar: ");
                    int codigo = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite seu CPF: ");
                    cpf  = scanner.nextLine();
                    boolean compra = obj.comprarPassagem(codigo,cpf);
                    if (compra){
                        System.out.println("*****PASSAGEM COMPRADA COM SUCESSO!!!*****\n");
                    }
                    System.out.println("Digite 'Sair' ou 'Continuar' para comprar mais passagens: ");
                    opcao = scanner.nextLine();
                }
            }

            else if (op == 4){
                System.out.println("CPF: ");
                String cpf = scanner.nextLine();
                System.out.println("Senha: ");
                String senha = scanner.nextLine();
                boolean login = obj.loginPrestadorServico(new PrestadorDeServico(cpf,senha));

                while (!login){
                    System.out.println("xxxxxx DADOS INCORRETOS. xxxxxx\n----Digite os dados novamente----");
                    System.out.println("CPF: ");
                    cpf = scanner.nextLine();
                    System.out.println("Senha: ");
                    senha = scanner.nextLine();
                    login = obj.loginCliente(new Cliente(cpf,senha));

                }
                System.out.println("Digite 'Sair' para fechar ou qualquer tecla para cadastrar uma passagem");
                String opcao = scanner.nextLine();

                while(!opcao.equalsIgnoreCase("Sair")){
                    int id = new Random().nextInt();
                    System.out.println("Origem: ");
                    String origem = scanner.nextLine();
                    System.out.println("Destino: ");
                    String destino = scanner.nextLine();
                    System.out.println("Nome da passagem: ");
                    String nomePassagem = scanner.nextLine();
                    System.out.println("valor: ");
                    Float valor = Float.parseFloat(scanner.nextLine());
                    System.out.println("Data: ");
                    String data = scanner.nextLine();
                    System.out.println("Hora: ");
                    String hora = scanner.nextLine();

                    boolean cadastrar = obj.cadastrarPassagem(new Passagem(id, origem, destino, nomePassagem, valor, data, hora, cpf));

                    if(cadastrar) {
                        System.out.println("***** PASSAGEM CADASTRADA COM SUCESSO!!! *****");
                    }

                    System.out.println("Digite 'Sair' para fechar ou qualquer tecla para continuar");
                    opcao = scanner.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("HelloClient exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}