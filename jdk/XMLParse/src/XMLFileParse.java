import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFileParse {

	public static void main(String[] args) 
			throws ParserConfigurationException, SAXException, IOException {
		File xmlFile = new File("person.xml");
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(xmlFile);
		
		document.getDocumentElement().normalize();
		
		//person 태그 내용을 가져옴
		NodeList personTagList = document.getElementsByTagName("person");
		
		for(int i=0;i<personTagList.getLength();i++) {
			//person이 가지고 있는 하위 태그(노드)들을 가져옴
			NodeList childNodes = personTagList.item(i).getChildNodes(); 
			for(int j = 0; j < childNodes.getLength(); j++) {
				//gender에 있는 속성값 읽기
				if(childNodes.item(j).getAttributes() != null) {
					if(childNodes.item(j).getAttributes().getNamedItem("value") != null)
						System.out.println(childNodes.item(j).getAttributes().getNamedItem("value").getNodeValue());
				}
				switch(childNodes.item(j).getNodeName()) {
				case "name":
				case "age":
				case "gender":
					System.out.println(childNodes.item(j).getNodeName() + " - " + 
						childNodes.item(j).getTextContent());
				}
			}
		}
	}

}








