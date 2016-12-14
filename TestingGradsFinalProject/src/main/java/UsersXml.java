import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by mhahue on 12/14/2016.
 */
public class UsersXml {
        //public String retrieveUser(String nameUser, String mailUser) throws Exception {

    public static void main(String[] args) throws Exception  {

            //The two lines below are just for getting an
            //instance of DocumentBuilder which we use
            //for parsing XML data
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Parsing of Book.xml is done here
            Document doc = builder.parse(new File("C:\\Users\\mhahue\\IdeaProjects\\New folder (2)\\TemaFinala\\TestingGradsFinalProject\\users.xml"));

            //Here we get the root element of XML and print out
            Element rootElement = doc.getDocumentElement();

            //Here we get a list of all elements named 'child'
            NodeList list = rootElement.getElementsByTagName("username");
            NodeList list1 = rootElement.getElementsByTagName("email");

            //Traversing all the elements from the list and printing
            //out its data
            for (int i = 0; i < list.getLength(); i++) {

                //Getting one node from the list.
                Node childNode1 = list.item(i);
                Node childNode2 = list1.item(i);

                System.out.println("Username: " + childNode1.getTextContent());

                System.out.println("Email: " + childNode2.getTextContent());
            }
        }
}
