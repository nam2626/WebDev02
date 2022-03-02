import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CarXml {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		File xmlFile = new File("car.xml");

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(xmlFile);

		document.getDocumentElement().normalize();

		NodeList carTagList = document.getElementsByTagName("record");

		for (int i = 0; i < carTagList.getLength(); i++) {
			NodeList childList = carTagList.item(i).getChildNodes();
			for (int j = 0; j < childList.getLength(); j++) {
				switch(childList.item(j).getNodeName()) {
				case "mno":case "model":case "year":case "maker":case "price":					
					System.out.println(childList.item(j).getNodeName() + " - " +childList.item(j).getTextContent());
				}
			}
			System.out.println();
		}
	}

}





