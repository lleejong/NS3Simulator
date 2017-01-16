package proj.skt.Job;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JobConverterFromFile {

	public static ArrayList<Job> readXML () {
		try {

			ArrayList<Job> list = new ArrayList<Job>();
			
			File fXmlFile = new File("./xml/input.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("job");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					
					double txLoss = Double.parseDouble(eElement.getElementsByTagName("txloss").item(0).getTextContent());
					double txDelay = Double.parseDouble(eElement.getElementsByTagName("txdelay").item(0).getTextContent());
					double txJitter = Double.parseDouble(eElement.getElementsByTagName("txjitter").item(0).getTextContent());
					double rxLoss = Double.parseDouble(eElement.getElementsByTagName("rxloss").item(0).getTextContent());
					double rxDelay = Double.parseDouble(eElement.getElementsByTagName("rxdelay").item(0).getTextContent());
					double rxJitter = Double.parseDouble(eElement.getElementsByTagName("rxjitter").item(0).getTextContent());
					
					list.add(new Job(new NS3Data(txLoss,txDelay,txJitter,rxLoss, rxDelay,rxJitter)));
				}
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
