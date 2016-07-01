

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author KOLU0897
 */
public class ThreadsApp extends JFrame {
    JSpinner spinner;
    
    public ThreadsApp () {
        super("Многопоточное приложение на Java");
        setBounds(0, 0, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        SpinnerModel spinnerModel =
         new SpinnerNumberModel(10, //initial value
            0, //min
            100, //max
            1);//step
      JSpinner spinner = new JSpinner(spinnerModel);
      add(spinner);
    }
    
    
    class NewThread implements Runnable {
	Thread thread;

        NewThread() {
            thread = new Thread(this, "Поток для примера");
            System.out.println("Создан второй поток " + thread);
            thread.start(); // Запускаем поток
        }

	public void run() {
            try {
                for (int i = 5; i > 0; i--) {
                    System.out.println("Второй поток: " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Второй поток прерван");
            }
            System.out.println("Второй поток завершён");
	}
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        ThreadsApp app = new ThreadsApp();
        app.setVisible(true);
    }
    
}
