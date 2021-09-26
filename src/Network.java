import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class Network{

    public static void main(String [] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);
        
        ImageIcon routerimg = new ImageIcon(Network.class.getResource("dlink.png"));

        String  MaxNumberOfConnections = (String) JOptionPane.showInputDialog(null,"What is number of WI-FI Connections?","Home Router",JOptionPane.QUESTION_MESSAGE,routerimg, null, null);
        int N = Integer.parseInt(MaxNumberOfConnections);
        Router router = new Router(N); 

        String  TotalNumberOfDevices = (String) JOptionPane.showInputDialog(null,"What is number of devices  want to connect?","Home Router",JOptionPane.QUESTION_MESSAGE,routerimg, null, null);
        int TC = Integer.parseInt(TotalNumberOfDevices);
        Device[] device = new Device[TC];

     
        input=new Scanner(System.in);
        for (int i = 0; i < TC; i++) {
            String TClineS = (String) JOptionPane.showInputDialog(null,"what is device "+(i+1)+ " Name&type?","Home Router",JOptionPane.QUESTION_MESSAGE,routerimg, null, null);
            device[i] = new Device(TClineS, router);
        }
       
        for (int i = 0; i < TC; i++) {  /* Hybd2 kol l threads */
            Thread.sleep(5000);
            device[i].start();}
    }

}