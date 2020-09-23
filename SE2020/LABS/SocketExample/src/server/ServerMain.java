package server;

import client.ClientThread;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServerMain {

    public static void main(String[] args) {

        List<ClientThread> ctList = new ArrayList<ClientThread>(); // lista di thread dei client che richiedono risorse
        ServerSocket lis = null; // listner sul delle ricjeste dal network

        try {
            lis = new ServerSocket(Integer.parseInt(args[0])); // parsing della porta dove ascoltare
        } catch (IOException e1) {
            System.out.println("Errore nella creazione del ServerSocket, applicazione dismessa");
            System.exit(1);
        }
        System.out.println("Server avviato");
        Socket sock = null; // socket 

        while (true) {
            try {
                sock = lis.accept(); // assegno la richiesta
            } catch (IOException e) {
                break; // in caso di errore esco
            }
            System.out.println("Socket creata, connessione accettata");
            ClientThread cl = new ClientThread(sock); // creo un trhead con il sock
            Thread tr = new Thread(cl);
            tr.start(); // lo avvio e lo aggiungo alla lista
            ctList.add(cl);
        }
    }

}
