import static java.lang.Thread.sleep;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


public class Router{
	
	ImageIcon connectedimg = new ImageIcon(Router.class.getResource("connected.png"));

    public boolean[] Devicearray;
    public int numberOfDevicess;
    public Semaphore device;
    public int connected;


    Router(int x) { 
    	//Start semaphore with the # of devices
    	numberOfDevicess = x;
    	device = new Semaphore(numberOfDevicess);
        Devicearray = new boolean[numberOfDevicess];
    }


    
    public synchronized int connect(Device d) throws InterruptedException, IOException {

        for (int i = 0; i < numberOfDevicess; i++) {
            if (Devicearray[i] == false) { //ready for occupied 
            	connected++;
                d.Connection = i+1;
                JOptionPane.showMessageDialog(null,d.getName() + " connected","Connection " + d.Connection,JOptionPane.INFORMATION_MESSAGE,connectedimg);
                device.writelogfile("Connection " + d.Connection+":"+d.getName() + " connected");
              
                Devicearray[i] = true;
                sleep(5000);
                break;
            }
        }
        return d.Connection;
    }

    public synchronized void leave(Device d) {

        { 
        	connected--;
            Devicearray[d.Connection-1] = false;
        }
       
        notify();
        
    }
}