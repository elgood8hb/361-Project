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
import java.io.PrintWriter ;

public class CustomerXML
{
   private String myDir = System.getProperty("user.dir");
   private Document doc;
   private DocumentBuilderFactory dbFactory;
   private DocumentBuilder dBuilder;
   private NodeList nList;
   private Element eElement;

   public void writeXML(String date, String[] w, int[] b, double[] a)
    {
    File file = new File(myDir + "\\customer.xml"); 
          
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 

      try {
		String filepath = myDir + "\\customer.xml";
      PrintWriter writer = new PrintWriter(filepath, "UTF-8");
      writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<customers>");
      for (int i = 0;i<3;i++) {
         writer.println("  <customer>");
         writer.println("    <date>" + date + "</date>");
         writer.println("    <name id=\"" + w[i] + "\">");
         writer.println("      <flight-hours>" + b[i] + "</flight-hours>");
         writer.println("      <bill>" + a[i] + "</bill>");
         writer.println("    </name>");
         writer.println("  </customer>");
         }
      writer.println("</customers>");
      writer.close();
      }
      catch (Exception e) {
      System.out.println("Cant write"); }
      writeCustomerYearly(date, w, b, a);
	}
   public void writeCustomerYearly(String date, String[] w, int[] b, double[] a)
   {
      try {
         File xmlFile = new File(myDir + "/CustomersYear.xml");
	      dbFactory = DocumentBuilderFactory.newInstance();
	      dBuilder = dbFactory.newDocumentBuilder();
	      doc = dBuilder.parse(xmlFile);
         nList = doc.getElementsByTagName("customer");
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
         Node nNode = nList.item(i);
         eElement = (Element) nNode;
         myHours[i] = eElement.getElementsByTagName("flight-hours").item(0).getTextContent();
         paid[i] = eElement.getElementsByTagName("bill").item(0).getTextContent();
      }
      File file = new File(myDir + "\\CustomersYear.xml"); 
          
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 

      try {
		String filepath = myDir + "\\CustomersYear.xml";
      PrintWriter writer = new PrintWriter(filepath, "UTF-8");
      writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<customers>");
      for (int i = 0;i<3;i++) {
         int myHoursInt = Integer.parseInt(myHours[i]);
         double paidnum = Double.parseDouble(paid[i]);
         writer.println("  <customer>");
         writer.println("    <date>" + date + "</date>");
         writer.println("    <name id=\"" + w[i] + "\">");
         writer.println("      <flight-hours>" + b[i] + "</flight-hours>");
         writer.println("      <bill>" + a[i] + "</bill>");
         writer.println("    </name>");
         writer.println("  </customer>");
         }
      writer.println("</customers>");
      writer.close();
      }
      catch (Exception e) {
      System.out.println("Cant write"); }

   }
}
