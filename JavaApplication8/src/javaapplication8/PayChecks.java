import javax.swing.*;
import java.util.Random;

public class PayChecks extends Thread {

//private String[] pilotName;
//private String[] pilotHours;
//private String[] pilotPay;
private String pilotName;
private String pilotHours;
private String pilotPay;
private int myHours;
private double myPay;
private double netPay;
private int rand;

public PayChecks(String names, String hours, String pay)
{
   pilotName = names;
   pilotHours = hours;
   pilotPay = pay;
}
public void run() {
   myPay = Double.parseDouble(pilotPay);

   JFrame frame = new JFrame();
   frame.setVisible(true);
   frame.setSize(200, 200);
   
   JPanel mainPanel = new JPanel();
   mainPanel.setLayout( new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
   JPanel panel1 = new JPanel();
   JLabel checkNum = new JLabel();
   Random random = new Random();
   rand = (random.nextInt(9999) + 1000);
   checkNum.setText(String.valueOf(rand));
   
   JLabel label1 = new JLabel("Name: " + pilotName);
   JLabel label2 = new JLabel("Hours: "  + pilotHours);
   JLabel label3 = new JLabel("Net Pay: $" + String.format("%.2f", myPay));
   mainPanel.add(checkNum);
   panel1.add(label1);
   panel1.add(label2);
   panel1.add(label3);
   mainPanel.add(panel1);
   frame.add(mainPanel);
   }
}

