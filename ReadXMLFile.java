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
            DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuild = dbFact.newDocumentBuilder();
            Document doc = dbBuild.parse(getXMLFile);


            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nList  = doc.getElementsByTagName("person");

            for(int i=0; i <nList.getLength();i++) {
                Node nNode = nList.item(i);
                System.out.println("Node name " + nNode.getNodeName() + " " + (i+1));
                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
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
