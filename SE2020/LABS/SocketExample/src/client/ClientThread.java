package client;

import java.io.*;
import java.net.*;
import java.util.*;
import server.ServerThread;

public class ClientThread implements Runnable { // client thread

    private Socket sock; // Socket per le richieste
    private ServerThread st = null; // server thread
    private boolean running = false;

    public ClientThread(Socket s) {
        sock = s;
    }

    @Override
    public void run() {

        boolean running = true;
        Scanner in = null;
        PrintWriter pw = null;
        try {
            in = new Scanner(sock.getInputStream());
            pw = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
            System.out.println("Errore nel client thread" + Thread.currentThread());
        }
        try {
            while (running) { // running 
                String cmd = in.nextLine(); // parse command received by main
                System.out.println("Ricevuto: " + cmd);  // command received
                if (cmd.equals("start")) { // start the request to server
                    st = new ServerThread();
                    Thread t = new Thread(st);
                    t.start();
                } else if (cmd.equals("getStatus")) { // get status of the thread
                    String out;
                    if (st.isRunning()) {
                        out = "running";
                    } else {
                        out = "finished";
                    }
                    pw.println(out);
                    pw.flush();
                    System.out.println("Sto mandando: " + out);
                } else if (cmd.equals("getResult")) { // get result of the thread
                    for (int n : st.getResult()) {
                        pw.println(String.valueOf(n));
                        pw.flush();
                    }
                    running = false;
                    pw.println("###");
                    pw.flush();
                }
            }
            sock.close();
            pw.close();
            in.close();

        } catch (Exception ex) {
            System.out.println("Errore nel client thread" + Thread.currentThread());
            ex.printStackTrace();
        }
    }
}
