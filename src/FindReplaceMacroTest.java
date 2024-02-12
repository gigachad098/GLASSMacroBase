public class FindReplaceMacroTest {
    public static void main(final String[] args) {
        FindReplaceMacro replaceJimWithTime = new FindReplaceMacro("//*[local-name() = 'function']", "./src/old.xml", "replaced!");
        replaceJimWithTime.executeMacro();
        PrettyPrintMacro prettyPrintMacro = new PrettyPrintMacro("//*[local-name() = 'function']", "./src/old.xml");
        prettyPrintMacro.executeMacro();
    }
}
