package org.jabref.logic.importer.fileformat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jabref.logic.util.StandardFileType;
import org.jabref.model.entry.BibEntry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MsBibImporterTest {

    @Test
    public void sGetExtensions() {
        MsBibImporter importer = new MsBibImporter();
        assertEquals(StandardFileType.XML, importer.getFileType());
    }

    @Test
    public void getDescription() {
        MsBibImporter importer = new MsBibImporter();
        assertEquals("Importer for the MS Office 2007 XML bibliography format.", importer.getDescription());
    }

    @Test
    public final void isNotRecognizedFormat() throws Exception {
        MsBibImporter testImporter = new MsBibImporter();
        List<String> notAccepted = Arrays.asList("CopacImporterTest1.txt", "IsiImporterTest1.isi",
                "IsiImporterTestInspec.isi", "emptyFile.xml", "IsiImporterTestWOS.isi");
        for (String s : notAccepted) {
            Path file = Path.of(MsBibImporter.class.getResource(s).toURI());
            assertFalse(testImporter.isRecognizedFormat(file));
        }
    }

    @Test
    public final void importEntriesEmpty() throws IOException, URISyntaxException {
        MsBibImporter testImporter = new MsBibImporter();
        Path file = Path.of(MsBibImporter.class.getResource("EmptyMsBib_Test.xml").toURI());
        List<BibEntry> entries = testImporter.importDatabase(file).getDatabase().getEntries();
        assertEquals(Collections.emptyList(), entries);
    }

    @Test
    public final void importEntriesNotRecognizedFormat() throws IOException, URISyntaxException {
        MsBibImporter testImporter = new MsBibImporter();
        Path file = Path.of(MsBibImporter.class.getResource("CopacImporterTest1.txt").toURI());
        List<BibEntry> entries = testImporter.importDatabase(file).getDatabase().getEntries();
        assertEquals(0, entries.size());
    }

    @Test
    public final void getFormatName() {
        MsBibImporter testImporter = new MsBibImporter();
        assertEquals("MSBib", testImporter.getName());
    }

    @Test
    public final void getCommandLineId() {
        MsBibImporter testImporter = new MsBibImporter();
        assertEquals("msbib", testImporter.getId());
    }
}
