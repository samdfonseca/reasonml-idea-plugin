import com.reason.lang.core.psi.PsiLet;
import com.reason.lang.reason.RmlParserDefinition;

public class LetParsingReTest extends BaseParsingTestCase {
    public LetParsingReTest() {
        super("", "re", new RmlParserDefinition());
    }

    public void testLetConstant() {
        PsiLet let = first(parseCode("let x = 1;").getLetExpressions());

        assertEquals("x", let.getName());
    }

}