package br.edu.catolica.classes;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {

    public static void main(String args[]) {
        try {
            LocateRegistry.createRegistry(2021);
            ObjetoPassagem objeto = new ObjetoPassagem();
            Naming.rebind("//localhost:2021/Corrida", objeto);
            System.out.println("Servidor no AR!");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
