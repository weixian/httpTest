package Istuary.com.Util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigHelper {
	private static  Log log = new Log(ConfigHelper.class);
	public static String GetPathByTag(String configfile, String tag) {
		try {

			File fXmlFile = new File(configfile);
			if(!fXmlFile.exists())
				log.error("Can not find "+configfile+"!");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(tag);

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String p= eElement.getElementsByTagName("p").item(0)
							.getTextContent();
					if(p!=null)
					{
						log.info("Get "+tag+" path is :"+p);
						return p;
					}else
						log.error("Can not find "+tag+"in config file!");
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String GetApiURL(String configfile) {

		return GetPathByTag(configfile, "ApiURL");
	}
}
