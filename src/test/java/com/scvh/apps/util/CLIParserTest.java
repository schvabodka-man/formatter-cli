package com.scvh.apps.util;

import org.junit.Assert;
import org.junit.Test;

public class CLIParserTest {

    private CLIParser parser;

    public CLIParserTest() {
        parser = new CLIParser();
    }

    @Test
    public void testNullInput() {
        String input = parser.parseInput(null).getTempInputInstance();
        String inputSecond = parser.parseInput(new String[0]).getTempInputInstance();
        Assert.assertEquals(input, null);
        Assert.assertEquals(inputSecond, null);
    }
}
