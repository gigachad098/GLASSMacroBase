import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FindReplaceMacro extends  AbstractMacro{
    Transformer rebuild;
    String replaceExpression;
    public FindReplaceMacro(String findExpression, String sourceFile, String replaceExpression) {
        super(findExpression, sourceFile);
        this.replaceExpression = replaceExpression;
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
                nodes.item(i).setTextContent(replaceExpression);
            }
        }
    }
    public void rebuildXML() {
        if (rebuild == null) {
            return;
        }
        else {
            try {
                rebuild.transform(new DOMSource(file), new StreamResult("output.xml"));

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
