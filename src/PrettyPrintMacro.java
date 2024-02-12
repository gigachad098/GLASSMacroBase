import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class PrettyPrintMacro extends AbstractMacro{
    Transformer rebuild;
    public PrettyPrintMacro(String findExpression, String sourceFile) {
        super(findExpression, sourceFile);
        try {
            rebuild = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            Utils.logString(e.toString());
        }
    }
    public void replaceTag() {
        if (this.nodes == null) {
            Utils.logString("No matching nodes found");
            return;
        }
        else {
            for (int i = 0; i < nodes.getLength(); i++) {
                nodes.item(i).setTextContent("&lt;p&gt;" + nodes.item(i).getTextContent());
                nodes.item(i).getLastChild().setTextContent(nodes.item(i).getLastChild().getTextContent() + "&lt;/p&gt;");
            }
        }
    }
    public void rebuildXML() {
        if (rebuild == null) {
            return;
        }
        else {
            try {
                rebuild.transform(new DOMSource(file), new StreamResult("prettyprint.xml"));

            } catch (TransformerException e) {
                Utils.logString(e.toString());
            }
        }
    }
    public void executeMacro() {
        super.executeMacro();
        this.replaceTag();
        this.rebuildXML();
    }
}
