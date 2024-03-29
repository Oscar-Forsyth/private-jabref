package org.jabref.model.entry.identifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ISSNTest {

    @Test
    public void isCanBeCleaned() {
        assertTrue(new ISSN("00279633").isCanBeCleaned());
    }

    @Test
    public void isCanBeCleanedIncorrectRubbish() {
        assertFalse(new ISSN("A brown fox").isCanBeCleaned());
    }

    @Test
    public void isCanBeCleanedDashAlreadyThere() {
        assertFalse(new ISSN("0027-9633").isCanBeCleaned());
    }

    @Test
    public void getCleanedISSN() {
        assertEquals("0027-9633", new ISSN("00279633").getCleanedISSN());
    }

    @Test
    public void getCleanedISSNDashAlreadyThere() {
        assertEquals("0027-9633", new ISSN("0027-9633").getCleanedISSN());
    }

    @Test
    public void getCleanedISSNDashRubbish() {
        assertEquals("A brown fox", new ISSN("A brown fox").getCleanedISSN());
    }

    @Test
    public void isValidChecksumCorrect() {
        assertTrue(new ISSN("0027-9633").isValidChecksum());
        assertTrue(new ISSN("2434-561X").isValidChecksum());
        assertTrue(new ISSN("2434-561x").isValidChecksum());
    }

    @Test
    public void isValidChecksumIncorrect() {
        assertFalse(new ISSN("0027-9634").isValidChecksum());
    }

    @Test
    public void isValidFormatCorrect() {
        assertTrue(new ISSN("0027-963X").isValidFormat());
    }

    @Test
    public void isValidFormatIncorrect() {
        assertFalse(new ISSN("00279634").isValidFormat());
    }
}
