public class FindReplaceMacroTest {
    public static void main(final String[] args) {
        FindReplaceMacroController foo = new FindReplaceMacroController();
        foo.execute("/*[name()='unit']/*[name()='class']/*[name()='block']/*[name()='function']/*[name()='name']", "./src/old.xml", "replaced");
    }
}
