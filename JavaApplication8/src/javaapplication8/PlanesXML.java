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

public class PlanesXML
{
   private String myDir = System.getProperty("user.dir");
   private Document doc;
   private DocumentBuilderFactory dbFactory;
   private DocumentBuilder dBuilder;
   private NodeList nList;
   private Element eElement;
   private int rounded;

   public void writeXML(String date, String[] w, int[] b, int[] a, int[] e)
    {
    File file = new File(myDir + "\\Planes.xml"); 
          
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 

      try {
		String filepath = myDir + "\\Planes.xml";
      PrintWriter writer = new PrintWriter(filepath, "UTF-8");
      writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<planes>");
      for (int i = 0;i<3;i++) {
         writer.println("  <plane>");
         writer.println("    <date>" + date + "</date>");
         writer.println("    <name id=\"" + w[i] + "\">");
         writer.println("      <flight-hours>" + b[i] + "</flight-hours>");
         writer.println("      <fhm>" + a[i] + "</fhm>");
         rounded = ((b[i] + 99) / 100 ) * 100;
         writer.println("      <next-maint>" + rounded + "</next-maint>");
         writer.println("      <rebuild>" + e[i] + "</rebuild>");
         writer.println("    </name>");
         writer.println("  </plane>");
         }
      writer.println("</planes>");
      writer.close();
      }
      catch (Exception ex) {
      System.out.println("Cant write"); }
	}
   
}
