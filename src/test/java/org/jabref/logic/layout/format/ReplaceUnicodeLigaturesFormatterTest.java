package org.jabref.logic.layout.format;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReplaceUnicodeLigaturesFormatterTest {

    private ReplaceUnicodeLigaturesFormatter formatter;

    @BeforeEach
    public void setUp() {
        formatter = new ReplaceUnicodeLigaturesFormatter();
    }

    @Test
    public void plainFormat() {
        assertEquals("lorem ipsum", formatter.format("lorem ipsum"));
    }

    @Test
    public void singleLigatures() {
        assertEquals("AA", formatter.format("\uA732"));
        assertEquals("fi", formatter.format("ﬁ"));
        assertEquals("et", formatter.format("\uD83D\uDE70"));
    }

    @Test
    public void ligatureSequence() {
        assertEquals("aefffflstue", formatter.format("æﬀﬄﬆᵫ"));
    }

    @Test
    public void sampleInput() {
        assertEquals("AEneas", formatter.format("Æneas"));
    }
}
