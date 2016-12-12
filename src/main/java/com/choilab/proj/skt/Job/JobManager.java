package com.choilab.proj.skt.Job;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JobManager {
	public JobManager () {
		readXML();
	} 

	public void readXML () {
		try {

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

					System.out.println("ID : " + eElement.getAttribute("seq"));
					System.out.println("TxLoss : " + eElement.getElementsByTagName("txloss").item(0).getTextContent());
					System.out.println("TxDelay : " + eElement.getElementsByTagName("txdelay").item(0).getTextContent());
					System.out.println("TxJitter : " + eElement.getElementsByTagName("txjitter").item(0).getTextContent());
					System.out.println("RxLoss : " + eElement.getElementsByTagName("rxloss").item(0).getTextContent());
					System.out.println("RxDelay : " + eElement.getElementsByTagName("rxdelay").item(0).getTextContent());
					System.out.println("RxJitter : " + eElement.getElementsByTagName("rxjitter").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
