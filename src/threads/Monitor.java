package threads;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Monitor extends Thread {
 
    private ServerSocket serverSocket;
    private MainFrame frame;
 
    public Monitor(int port) {
        setDaemon(true);
        setName("StopMonitor");
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    @Override
    public void run() {
        System.out.println("stop monitor thread listening on: "+ serverSocket.getInetAddress()+":"+serverSocket.getLocalPort());      
        Socket socket;
        try {
            socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            reader.readLine();
            System.out.println("stop signal received, stopping server");
            socket.close();
            serverSocket.close();
            //Thread t = Thread.currentThread();
            frame.addMessage("127.0.0.1", getName(), "Сервер запущен");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }    
}