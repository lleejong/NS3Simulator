package proj.skt.Configure;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigureManager {

	

	public static void readXML () {
		try {

			File fXmlFile = new File("./xml/configure.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("setup");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					Configure.setCache(Boolean.valueOf(eElement.getElementsByTagName("cache").item(0).getTextContent()).booleanValue());
					Configure.setType(Integer.parseInt(eElement.getElementsByTagName("type").item(0).getTextContent()));
					Configure.setContainers(Integer.parseInt(eElement.getElementsByTagName("containers").item(0).getTextContent()));
					Configure.setPort(Integer.parseInt(eElement.getElementsByTagName("port").item(0).getTextContent()));

					//System.out.println("ID : " + eElement.getAttribute("id"));
					//System.out.println("Cache : " + eElement.getElementsByTagName("cache").item(0).getTextContent());
					//System.out.println("Type : " + eElement.getElementsByTagName("type").item(0).getTextContent());
					//System.out.println("Number of containers : " + eElement.getElementsByTagName("containers").item(0).getTextContent());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	public static void writeXML (boolean cache, int type, int containers) {
		try {

			File fXmlFile = new File("./xml/configure.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("setup");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					eElement.getElementsByTagName("cache").item(0).setTextContent(String.valueOf(cache));
					eElement.getElementsByTagName("type").item(0).setTextContent(String.valueOf(type));
					eElement.getElementsByTagName("containers").item(0).setTextContent(String.valueOf(containers));
				}
			}
			
			DOMSource xmlDOM = new DOMSource(doc);
			StreamResult xmlFile = new StreamResult(new File("./xml/configure.xml"));
			TransformerFactory.newInstance().newTransformer().transform(xmlDOM, xmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
