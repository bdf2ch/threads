package threads;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
 
public class Server implements Runnable {
 
    protected int          serverPort   = 9000;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
 
    public Server (int port){
        this.serverPort = port;
    }
 
    @Override
    public void run () {
        openServerSocket();
        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException("Не удалось приянть соединения от клиента", e);
            }
            new Thread(new Worker(clientSocket)).start();
        }
        System.out.println("Server Stopped.") ;
    }
 
 
    private synchronized boolean isStopped() {
        return this.isStopped;
    }
 
    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось остановить сервер", e);
        }
    }
 
    private void openServerSocket() {
        //System.out.println("Opening server socket...");
     try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось открыть порт " + this.serverPort, e);
        }
    }
 
}