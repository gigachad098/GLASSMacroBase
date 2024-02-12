import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;


public class AbstractMacro {
    XPath query = XPathFactory.newInstance().newXPath();
    Document file;
    NodeList nodes;
    String findExpression;
    String sourcePath;

    public AbstractMacro(String findExpression, String sourcePath) {
        this.findExpression = findExpression;
        this.sourcePath = sourcePath;
    }

    private void loadSourceFile() {
        try {
            InputSource temp = new InputSource(this.sourcePath);
            file = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(temp);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            Utils.logString(e.toString());
        }
    }

    private void query() {
        try {
            nodes = (NodeList) query.evaluate(this.findExpression, file, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            Utils.logString(e.toString());
        }
    }
    public void executeMacro() {
        this.loadSourceFile();
        this.query();
    }
}