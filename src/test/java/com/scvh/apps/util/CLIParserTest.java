package com.scvh.apps.util;

import org.junit.Assert;
import org.junit.Test;

public class CLIParserTest {

    private CLIParser parser;

    public CLIParserTest() {
        parser = new CLIParser(new Printer());
    }

    @Test
    public void testNullInput() {
        String input = parser.parseInput(null);
        String inputSecond = parser.parseInput(new String[0]);
        Assert.assertEquals(input, null);
        Assert.assertEquals(inputSecond, null);
    }
}
