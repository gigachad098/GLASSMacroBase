import com.ximpleware.ModifyException;

import java.io.UnsupportedEncodingException;

public class FindReplaceMacro extends AbstractMacro {
    String replaceExpression;
    public FindReplaceMacro(String findExpression, String sourceFile, String replaceExpression) {
        super(findExpression, sourceFile);
        this.replaceExpression = replaceExpression;
    }
    public void replaceTag() {
        if (this.foundNode != -1) {
            try {
                this.mod.updateToken(this.foundNode, this.replaceExpression);
            } catch (ModifyException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
