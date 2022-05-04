package br.edu.catolica.classes;

import db.DB;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ObjetoPassagem extends UnicastRemoteObject implements Corrida{

    Connection conn = DB.abrirConexao();
    PreparedStatement st = null;
    //conn = DB.abrirConexao();
    Statement stt = null;
    ResultSet rs = null;



    public ObjetoPassagem() throws RemoteException {
        super();
    }



    @Override
    public ArrayList<Passagem> mostrarPassagem() throws RemoteException {

        ArrayList<Passagem> ListaPassagens = new ArrayList<>();

        try {
            stt = conn.createStatement();
            rs = stt.executeQuery("SELECT * FROM passagens");


            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String origem = rs.getString("origem");
                String destino = rs.getString("destino");
                Float valor = rs.getFloat("valor");
                String datas = rs.getString("datas");
                String hora = rs.getString("hora");

                ListaPassagens.add(new Passagem(id,origem,destino,nome,valor,datas,hora));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.fecharResultSet(rs);
            DB.fecharStatement(stt);
        }
        return ListaPassagens;
    }

    @Override
    public boolean comprarPassagem(int idpassagem, String cpfcomprador) throws RemoteException {

        boolean compra = false;

        try {
            st = conn.prepareStatement("INSERT INTO passagenscomprada (idpassagem, cpfcomprador) " +
                            "VALUES (?, ?);"
                    , Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, idpassagem);

            st.setString(2, cpfcomprador);

            int linhas = st.executeUpdate();

            if(linhas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while(rs.next()) {
                    System.out.println("Passagem comprada com sucesso!!! " + rs.getString(linhas));
                    compra = true;
                }
            } else
                System.out.println("Nenhuma passagem foi comprada");


        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            DB.fecharStatement(st);
            //DB.fecharConexao();
        }
        return compra;
    }

    @Override
    public boolean loginCliente(Cliente cliente) throws RemoteException {

        String procurar = "Não encontrado";
        boolean login = false;

        try {
            stt = conn.createStatement();
            rs = stt.executeQuery("SELECT * FROM cliente");

            while(rs.next() && !procurar.equals("Encontrado")) {
                System.out.println("deu ruim");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                if(cpf.equals(cliente.getCPF())){
                    System.out.println("correto");
                    procurar = "Encontrado";
                    login = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.fecharResultSet(rs);
            DB.fecharStatement(stt);
        }

        return login;
    }

    @Override
    public boolean loginPrestadorServico(PrestadorDeServico prestadorDeServico) throws RemoteException {
        String procurar = "Não encontrado";
        boolean login = false;

        String cpf = " ";
        String senha = " ";

        try {
            stt = conn.createStatement();
            rs = stt.executeQuery("SELECT * FROM prestadordeservicos");

            while(rs.next() && !procurar.equals("Encontrado")) {
                cpf = rs.getString("cpf");
                senha = rs.getString("senha");

                if(cpf.equals(prestadorDeServico.getCPF())){
                    procurar = "Encontrado";
                    login = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.fecharResultSet(rs);
            DB.fecharStatement(stt);
        }

        return login;
    }


    @Override
    public boolean cadastrarPassagem(Passagem passagem) throws RemoteException {

        try {

            st = conn.prepareStatement("INSERT INTO passagens (id, origem, destino, nome, valor, datas, hora, idsecundario) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);"
                    , Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, passagem.getId());

            st.setString(2, passagem.getOrigem());

            st.setString(3, passagem.getDestino());

            st.setString(4, passagem.getNome());

            st.setFloat(5, passagem.getValor());

            st.setString(6, passagem.getData());

            st.setString(7, passagem.getHora());

            st.setString(8, passagem.getIdsecundario());

            int linhas = st.executeUpdate();

            if(linhas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while(rs.next()) {
                    System.out.println("Id da passagem cadastrada: " + rs.getString(linhas));

                }
            } else
                System.out.println("Nenhuma passagem foi inserida");


        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            DB.fecharStatement(st);
            //DB.fecharConexao();
        }

        return false;

    }

    @Override
    public boolean cadastrarCliente(Cliente cliente) throws RemoteException {

        boolean cadastro = false;

        try {

            st = conn.prepareStatement("INSERT INTO cliente (nome, cpf, senha) VALUES (?,?,?);"
                    ,Statement.RETURN_GENERATED_KEYS);

                st.setString(1, cliente.getNome());

                st.setString(2, cliente.getCPF());

                st.setString(3 , cliente.getSenha());

            int linhas = st.executeUpdate();

            if(linhas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while(rs.next()) {
                    System.out.println("Cliente cadastrado: " + rs.getString(linhas));
                    cadastro = true;

                }
            } else
                System.out.println("Nenhum cliente foi inserida");

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            DB.fecharStatement(st);
            //DB.fecharConexao();
        }

        return cadastro;
    }

    @Override
    public boolean cadastrarPrestadorServico(PrestadorDeServico prestadorDeServico) throws RemoteException {

        boolean cadastro = false;

        try {
            st = conn.prepareStatement("INSERT INTO prestadordeservicos (nome, registrocnh, cpf, senha) VALUES (?, ?, ?, ?);"
                    , Statement.RETURN_GENERATED_KEYS);

                st.setString(1, prestadorDeServico.getNome());

                st.setString(2, prestadorDeServico.getRegistroCNH());

                st.setString(3, prestadorDeServico.getCPF());

                st.setString(4 , prestadorDeServico.getSenha());


            int linhas = st.executeUpdate();

            if(linhas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while(rs.next()) {
                    System.out.println("Prestador cadastrado: " + rs.getString(linhas));
                    cadastro = true;
                }
            } else
                System.out.println("Nenhuma linha foi inserida");


        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            DB.fecharStatement(st);
            //DB.fecharConexao();
        }

        return cadastro;
    }
}
