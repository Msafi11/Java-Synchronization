import javax.swing.JOptionPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Device extends Thread {
	
	public void writelogfile(String str) 
  		  throws IOException {
  		    
  		    BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
  		    writer.append(str);
  		    writer.newLine();
  		    writer.close();
  		}
	
    public static Router rr;
   
    public int Connection ;
    Device(String DeviceName, Router r) {
        super(DeviceName); //name the thread with same device name
        rr = r;
    }
    
    
    ImageIcon logoutimg = new ImageIcon(Device.class.getResource("notconnected.png"));
    ImageIcon performonlineimg = new ImageIcon(Device.class.getResource("performonline.jpg"));

    public void run() {
       

    	try {
			rr.device.P(this); // till  semaphore -->  new device  arrived 
		} catch (IOException e1) {} 
        try {
        	Connection=rr.connect(this); 
        	sleep(6000);
        	
        }
        catch (InterruptedException e) {} catch (IOException e) {
		}
     
       
        try {
        	JOptionPane.showMessageDialog(null,this.getName() + " Performs online activity","Connection " + Connection,JOptionPane.INFORMATION_MESSAGE,performonlineimg);
        	writelogfile("Connection " + Connection+":"+this.getName() + " Performs online activity");
			sleep(8000);
		} catch (InterruptedException | IOException e) {}
        
        
        rr.leave(this);
        JOptionPane.showMessageDialog(null,this.getName() + " has logged out!","Connection " + Connection,JOptionPane.INFORMATION_MESSAGE,logoutimg);
        try {
			writelogfile("Connection " + Connection+":"+this.getName() + "has logged out!");
		} catch (IOException e) {}
        	
        
       
            rr.device.V();
    }
}