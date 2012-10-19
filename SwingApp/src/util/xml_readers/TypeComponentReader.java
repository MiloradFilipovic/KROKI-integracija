package util.xml_readers;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.h2.constant.SysProperties;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.panelcomposer.core.AppCache;

import util.staticnames.ReadersPathConst;
import util.staticnames.Tags;

public class TypeComponentReader {
	
	protected static String xmlFile = ReadersPathConst.TYPE_COMPONENT_FILE_NAME;
	
	public static void loadMappings() {
		try {
			File f = new File(".");
			String appPath = f.getAbsolutePath().substring(0,f.getAbsolutePath().length()-1);
			System.out.println("TYPE COMPONENT READER: " + xmlFile);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(appPath + xmlFile);
			System.out.println("TC READER: " + doc.getDocumentURI());
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName(Tags.PROPERTY);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element elementType = (Element) nodeList.item(i);
				String langType = elementType.getAttribute(Tags.LANGUAGE_TYPE);
				String compType = elementType.getAttribute(Tags.COMPONENT_TYPE);
				AppCache.getInstance().addToCacheTypes(langType, compType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
