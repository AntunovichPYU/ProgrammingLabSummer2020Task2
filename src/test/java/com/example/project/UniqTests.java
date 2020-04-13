package com.example.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class UniqTests {
    private void assertFileContent(String name, String expected) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(name));

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        if (sb.lastIndexOf("\n") != -1) sb.deleteCharAt(sb.lastIndexOf("\n"));
        assertEquals(expected, sb.toString());

        reader.close();
    }

    @AfterEach
    void deleteFile() {
        new File("files/output.txt").delete();
    }

    @Test
    void withoutArgs() throws IOException {
        UniqMain.main(new String[] {"files/input.txt", "-o", "files/output.txt"});
        final String expected = "А роза упала на лапу Азора\n" +
                "а роза упала на лапу азора\n" +
                "  А роза упала на лапу Азора\n" +
                "А роза упала на лапу Азора\n" +
                "  роза упала на лапу Азора\n" +
                "ыфроза упала на лапу Азора\n" +
                "  роза УпаЛа на лапу Азора\n" +
                "fdроза урала на лапу Азора\n" +
                "Съешь еще этих мягких французских булок, да выпей чай\n" +
                "а роза упала на лапу азора\n" +
                "Съешь еще этих мягких французских булок, да выпей чай";
        assertFileContent("files/output.txt", expected);
    }

    @Test
    void ignoreUppercase() throws IOException {
        UniqMain.main(new String[] {"files/input.txt", "-o", "files/output.txt", "-i"});
        final String expected = "А роза упала на лапу Азора\n" +
                "  А роза упала на лапу Азора\n" +
                "А роза упала на лапу Азора\n" +
                "  роза упала на лапу Азора\n" +
                "ыфроза упала на лапу Азора\n" +
                "  роза УпаЛа на лапу Азора\n" +
                "fdроза урала на лапу Азора\n" +
                "Съешь еще этих мягких французских булок, да выпей чай\n" +
                "а роза упала на лапу азора\n" +
                "Съешь еще этих мягких французских булок, да выпей чай";
        assertFileContent("files/output.txt", expected);
    }

    @Test
    void ignoreFirstSymbols() throws IOException {
        UniqMain.main(new String[] {"files/input.txt", "-o", "files/output.txt", "-s", "2"});
        final String expected = "А роза упала на лапу Азора\n" +
                "а роза упала на лапу азора\n" +
                "  А роза упала на лапу Азора\n" +
                "А роза упала на лапу Азора\n" +
                "  роза УпаЛа на лапу Азора\n" +
                "fdроза урала на лапу Азора\n" +
                "Съешь еще этих мягких французских булок, да выпей чай\n" +
                "а роза упала на лапу азора\n" +
                "Съешь еще этих мягких французских булок, да выпей чай";
        assertFileContent("files/output.txt", expected);
    }

    @Test
    void ignoreAllSymbols() throws IOException {
        UniqMain.main(new String[] {"files/input.txt", "-o", "files/output.txt", "-s", "999"});
        final String expected = "А роза упала на лапу Азора";
        assertFileContent("files/output.txt", expected);
    }

    @Test
    void ignoreSymbolsAndUppercase() throws IOException {
        UniqMain.main(new String[] {"files/input.txt", "-o", "files/output.txt", "-s", "2", "-i"});
        final String expected = "А роза упала на лапу Азора\n" +
                "  А роза упала на лапу Азора\n" +
                "А роза упала на лапу Азора\n" +
                "Съешь еще этих мягких французских булок, да выпей чай\n" +
                "а роза упала на лапу азора\n" +
                "Съешь еще этих мягких французских булок, да выпей чай";
        assertFileContent("files/output.txt", expected);
    }

    @Test
    void onlyUnique() throws IOException {
        UniqMain.main(new String[] {"files/input.txt", "-o", "files/output.txt", "-u"});
        final String expected = "А роза упала на лапу Азора\n" +
                "а роза упала на лапу азора\n" +
                "  А роза упала на лапу Азора\n" +
                "А роза упала на лапу Азора\n" +
                "  роза упала на лапу Азора\n" +
                "ыфроза упала на лапу Азора\n" +
                "  роза УпаЛа на лапу Азора\n" +
                "fdроза упала на лапу Азора\n" +
                "а роза упала на лапу азора\n" +
                "Съешь еще этих мягких французских булок, да выпей чай";
        assertFileContent("files/output.txt", expected);
    }

    @Test
    void uniqueIgnoreUppercase() throws IOException {
        UniqMain.main(new String[] {"-i", "files/input.txt", "-o", "files/output.txt", "-u"});
        final String expected = "  А роза упала на лапу Азора\n" +
                "А роза упала на лапу Азора\n" +
                "  роза упала на лапу Азора\n" +
                "ыфроза упала на лапу Азора\n" +
                "  роза УпаЛа на лапу Азора\n" +
                "fdроза упала на лапу Азора\n" +
                "а роза упала на лапу азора\n" +
                "Съешь еще этих мягких французских булок, да выпей чай";
        assertFileContent("files/output.txt", expected);
    }
}
