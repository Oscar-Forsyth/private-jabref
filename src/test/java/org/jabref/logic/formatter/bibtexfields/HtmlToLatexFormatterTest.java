package org.jabref.logic.formatter.bibtexfields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests in addition to the general tests from {@link org.jabref.logic.formatter.FormatterTest}
 */
public class HtmlToLatexFormatterTest {

    private HtmlToLatexFormatter formatter;

    @BeforeEach
    public void setUp() {
        formatter = new HtmlToLatexFormatter();
    }

    @Test
    public void formatWithoutHtmlCharactersReturnsSameString() {
        assertEquals("abc", formatter.format("abc"));
    }

    @Test
    public void formatMultipleHtmlCharacters() {
        assertEquals("{{\\aa}}{\\\"{a}}{\\\"{o}}", formatter.format("&aring;&auml;&ouml;"));
    }

    @Test
    public void formatCombinedAccent() {
        assertEquals("{\\'{\\i}}", formatter.format("i&#x301;"));
    }

    @Test
    public void basic() {
        assertEquals("aaa", formatter.format("aaa"));
    }

    @Test
    public void hTML() {
        assertEquals("{\\\"{a}}", formatter.format("&auml;"));
        assertEquals("{\\\"{a}}", formatter.format("&#228;"));
        assertEquals("{\\\"{a}}", formatter.format("&#xe4;"));
        assertEquals("{{$\\Epsilon$}}", formatter.format("&Epsilon;"));
    }

    @Test
    public void hTMLRemoveTags() {
        assertEquals("aaa", formatter.format("<b>aaa</b>"));
    }

    @Test
    public void hTMLCombiningAccents() {
        assertEquals("{\\\"{a}}", formatter.format("a&#776;"));
        assertEquals("{\\\"{a}}", formatter.format("a&#x308;"));
        assertEquals("{\\\"{a}}b", formatter.format("a&#776;b"));
        assertEquals("{\\\"{a}}b", formatter.format("a&#x308;b"));
    }

    @Test
    public void keepsSingleLessThan() {
        String text = "(p < 0.01)";
        assertEquals(text, formatter.format(text));
    }

    @Test
    public void formatExample() {
        assertEquals("JabRef", formatter.format(formatter.getExampleInput()));
    }
}
