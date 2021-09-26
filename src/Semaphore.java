import javax.swing.JOptionPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;

public class Semaphore {
	ImageIcon arrivedimg = new ImageIcon(Semaphore.class.getResource("arrived.jpg"));
	ImageIcon waitimg = new ImageIcon(Semaphore.class.getResource("wait.jpg"));
    public static int queue;
    
    Semaphore(int num){queue=num;}
    
    public void writelogfile(String str) 
    		  throws IOException {
    		    
    		    BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
    		    writer.append(str);
    		    writer.newLine();
    		    writer.close();
    		}
    
    public synchronized void P(Device d) throws IOException {
        queue--;
        if(queue<0) {
            try {
            	JOptionPane.showMessageDialog(null,d.getName() + " arrived and waiting",null, 0, waitimg);
            	writelogfile(d.getName() + " arrived and waiting");
              
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
        	JOptionPane.showMessageDialog(null,d.getName() + " has arrived",null, 0, arrivedimg);
        	writelogfile(d.getName() + " has arrived");
        }
    }
    public synchronized void V() {
        queue++;
        if (queue <= 0)
            notify();
    }
}