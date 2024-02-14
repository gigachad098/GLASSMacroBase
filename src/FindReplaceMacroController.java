import com.ximpleware.ModifyException;
import com.ximpleware.TranscodeException;

import java.io.IOException;

public class FindReplaceMacroController {
    public void execute(String findExpression, String sourceFile, String replaceExpression) {
        FindReplaceMacro macro = new FindReplaceMacro(findExpression, sourceFile, replaceExpression);
        macro.loadSourceFile();
        macro.query();
        macro.replaceTag();
        try {
            macro.rebuildXML();
        } catch (ModifyException | TranscodeException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
