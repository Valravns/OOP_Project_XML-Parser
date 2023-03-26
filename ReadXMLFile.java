package CourseProject.XMLParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadXMLFile {
    public static void main(String[] args) {
        try{
            File getXMLFile = new File("Info.xml");
            DocumentBuilderFactory xmlDBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDB = xmlDBF.newDocumentBuilder();
            Document xmlDocument = xmlDB.parse(getXMLFile);


            System.out.println("Root element: " + xmlDocument.getDocumentElement().getNodeName());
            NodeList nodeList1  = xmlDocument.getElementsByTagName("person");

            for(int i=0; i <nodeList1.getLength();i++) {
                Node node = nodeList1.item(i);
                System.out.println("Node name " + node.getNodeName() + " " + (i+1));
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("Person ID#: " + eElement.getAttribute("id"));
                    System.out.println("Person Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Person Address: " + eElement.getElementsByTagName("address").item(0).getTextContent());
                    System.out.println("Person Position: " + eElement.getElementsByTagName("position").item(0).getTextContent());
                    System.out.println("----------------------------------------");
                }
            }
        } catch(Exception e){
            System.out.println("Error");
        }
    }
}
