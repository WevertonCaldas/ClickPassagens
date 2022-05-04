package br.edu.catolica.classes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Corrida extends Remote {

    boolean cadastrarPassagem(Passagem passagem) throws RemoteException;

    boolean cadastrarCliente(Cliente cliente) throws RemoteException;

    boolean cadastrarPrestadorServico(PrestadorDeServico prestadorDeServico) throws RemoteException;

    ArrayList<Passagem> mostrarPassagem() throws RemoteException;

    boolean comprarPassagem(int idpassagem, String cpfcomprador) throws RemoteException;

    boolean loginCliente(Cliente cliente) throws RemoteException;

    boolean loginPrestadorServico(PrestadorDeServico prestadorDeServico) throws RemoteException;

}
