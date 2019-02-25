import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Calendar;


public class PilotXML
{
   private String myDir = System.getProperty("user.dir");
   private File xmlFile;
   private Document doc;
   private DocumentBuilderFactory dbFactory;
   private DocumentBuilder dBuilder;
   private NodeList nList;
   private Element eElement;
   private String strDate;
   private Node nNode;
   
   private File xmlFile2;
   private Document doc2;
   private DocumentBuilderFactory dbFactory2;
   private DocumentBuilder dBuilder2;
   private NodeList nList2;
   private Element eElement2;
   private Node nNode2;
   
   private File xmlFile3;
   private Document doc3;
   private DocumentBuilderFactory dbFactory3;
   private DocumentBuilder dBuilder3;
   private NodeList nList3;
   private Element eElement3;
   private Node nNode3;
   
	     
   
   public void writeXML(String date, String[] w, String[] b, String[] a, double[] wage, int[] rate, double[] totalPay)
    {
      Date today = new Date();
      //Date format
      DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
      strDate = df.format(today);
      // Day of week
      Calendar c = Calendar.getInstance();
      c.setTime(today);
      int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
      
    writePilotWeekly(date, w, b, a, wage, rate, totalPay);
    writePilotYearly(date, w, b, a, wage, rate, totalPay);
    
    xmlFile = new File(myDir + "/Pilots.xml"); 
          
        if(xmlFile.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 

      try {
		String filepath = myDir + "/Pilots.xml";
      PrintWriter writer = new PrintWriter(filepath, "UTF-8");
      writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<pilots>");
      for (int i = 0;i<3;i++) {
         writer.println("  <pilot>");
         writer.println("    <date>" + date + "</date>");
         writer.println("    <name id=\"" + w[i] + "\">");
         writer.println("      <certificate>" + b[i] + "</certificate>");
         writer.println("      <expiration-date>" + a[i] + "</expiration-date>");
         writer.println("	    <salary>" + wage[i] + "</salary>");
         writer.println("	    <hours>" + rate[i] + "</hours>");
         writer.println("	    <pay>" + totalPay[i] + "</pay>");
         writer.println("    </name>");
         writer.println("  </pilot>");
         }
      writer.println("</pilots>");
      writer.close();
      }
      catch (Exception e) {
      System.out.println("Cant write"); }
      
	}
   public void writePilotYearly(String date, String[] w, String[] b, String[] a, double[] wage, int[] rate, double[] totalPay)
   {
      try {
         File xmlFile2 = new File(myDir + "/PilotsYear.xml");
	      dbFactory2 = DocumentBuilderFactory.newInstance();
	      dBuilder2 = dbFactory2.newDocumentBuilder();
	      doc2 = dBuilder2.parse(xmlFile2);
         nList2 = doc2.getElementsByTagName("pilot");
         //docLength = nList.getLength();
         }
         catch (Exception ex)
         {
            System.out.println("cant open");
         }
      String[] myHours = new String[3];
      String[] paid = new String[3];
      
      for (int i = 0;i<3;i++)
      {
         nNode2 = nList2.item(i);
         eElement2 = (Element) nNode2;
         myHours[i] = eElement2.getElementsByTagName("hours").item(0).getTextContent();
         paid[i] = eElement2.getElementsByTagName("pay").item(0).getTextContent();
      }
      File file = new File(myDir + "/PilotsYear.xml"); 
          
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 

      try {
		String filepath = myDir + "/PilotsYear.xml";
      PrintWriter writer = new PrintWriter(filepath, "UTF-8");
      writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<pilots>");
      for (int i = 0;i<3;i++) {
         int myHoursInt = Integer.parseInt(myHours[i]);
         double paidnum = Double.parseDouble(paid[i]);
         writer.println("  <pilot>");
         writer.println("    <date>" + date + "</date>");
         writer.println("    <name id=\"" + w[i] + "\">");
         writer.println("      <certificate>" + b[i] + "</certificate>");
         writer.println("      <expiration-date>" + a[i] + "</expiration-date>");
         writer.println("	    <salary>" + wage[i] + "</salary>");
         writer.println("	    <hours>" + (rate[i]+myHoursInt) + "</hours>");
         writer.println("	    <pay>" + (totalPay[i]+paidnum) + "</pay>");
         writer.println("    </name>");
         writer.println("  </pilot>");
         }
      writer.println("</pilots>");
      writer.close();
      }
      catch (Exception e) {
      System.out.println("Cant write"); }

   }
   public void writePilotWeekly(String date, String[] w, String[] b, String[] a, double[] wage, int[] rate, double[] totalPay)
   {
      try {
         xmlFile3 = new File(myDir + "/WeekPilot.xml");
	      dbFactory3 = DocumentBuilderFactory.newInstance();
	      dBuilder3 = dbFactory3.newDocumentBuilder();
	      doc3 = dBuilder3.parse(xmlFile3);
         nList3 = doc3.getElementsByTagName("name");
         //docLength = nList.getLength();
         }
         catch (Exception ex)
         {
            System.out.println("cant open");
         }
      String[] myHours2 = new String[3];
      String[] paid2 = new String[3];
      Date today = new Date();
      //Date format
      DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
      strDate = df.format(today);
      // Day of week
      Calendar c = Calendar.getInstance();
      c.setTime(today);
      int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
      
      //dayOfWeek = 7;
      
      for (int i = 0;i<3;i++)
      {
         nNode3 = nList3.item(i);
         eElement3 = (Element) nNode3;
         if (dayOfWeek == 7)
         {
            myHours2[i] = "0";
            paid2[i] = "0";
         }
         else
         {
            myHours2[i] = eElement3.getElementsByTagName("hours").item(0).getTextContent();
            paid2[i] = eElement3.getElementsByTagName("pay").item(0).getTextContent();
         }
      }
      File file = new File(myDir + "/WeekPilot.xml"); 
          
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 

      try {
		String filepath = myDir + "/WeekPilot.xml";
      PrintWriter writer = new PrintWriter(filepath, "UTF-8");
      writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<pilots>");
      for (int i = 0;i<3;i++) {
         int myHoursInt2 = Integer.parseInt(myHours2[i]);
         double paidnum2 = Double.parseDouble(paid2[i]);
         writer.println("  <pilot>");
         writer.println("    <date>" + date + "</date>");
         writer.println("    <name id=\"" + w[i] + "\">");
         writer.println("      <certificate>" + b[i] + "</certificate>");
         writer.println("      <expiration-date>" + a[i] + "</expiration-date>");
         writer.println("	    <salary>" + wage[i] + "</salary>");
         writer.println("	    <hours>" + (rate[i]+myHoursInt2) + "</hours>");
         writer.println("	    <pay>" + (totalPay[i]+paidnum2) + "</pay>");
         writer.println("    </name>");
         writer.println("  </pilot>");
         }
      writer.println("</pilots>");
      writer.close();
      }
      catch (Exception e) {
      System.out.println("Cant write"); }

   }

}
