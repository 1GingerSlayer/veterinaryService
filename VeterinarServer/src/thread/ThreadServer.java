/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Nina
 */
public class ThreadServer extends Thread {

    private ServerSocket serverSocket;

    public ThreadServer() {
        try {
            serverSocket = new ServerSocket(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                System.out.println("Cekanje klijenta...");
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao!");
                ThreadClient th = new ThreadClient(socket);
                th.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}
