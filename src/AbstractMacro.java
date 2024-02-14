import com.ximpleware.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;


public class AbstractMacro {
    XPath query = XPathFactory.newInstance().newXPath();
    VTDGen vg = new VTDGen();
    AutoPilot ap = new AutoPilot();
    XMLModifier mod = new XMLModifier();
    VTDNav vn;
    String findExpression;
    String sourcePath;
    int foundNode = -1;

    public AbstractMacro(String findExpression, String sourcePath) {
        this.findExpression = findExpression;
        this.sourcePath = sourcePath;
    }

    public void loadSourceFile() {
        try {
            vg.parseFile(sourcePath, false);
            this.vn = vg.getNav();
            ap.bind(vn);
            mod.bind(vn);
        } catch (ModifyException e) {
            Utils.logString(e.toString());
        }
    }

    public void query() {
        try {
            ap.selectXPath(this.findExpression);
            ap.evalXPath();
            this.foundNode = vn.getText();
        } catch (XPathParseException e) {
            Utils.logString(e.toString());
        } catch (XPathEvalException | NavException e) {
            throw new RuntimeException(e);
        }
    }
    public void rebuildXML() throws ModifyException, IOException, TranscodeException {
        try {
            mod.output("new.xml");
        } catch (ModifyException | IOException | TranscodeException e) {
            Utils.logString(e.toString());
        }
    }
}