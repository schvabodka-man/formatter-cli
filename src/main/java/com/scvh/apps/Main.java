package com.scvh.apps;

import com.scvh.apps.core.StringCleaner;
import com.scvh.apps.core.StringMaker;
import com.scvh.apps.util.CLIParser;
import com.scvh.apps.util.Printer;
import com.scvh.apps.util.StdinPipeParser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Printer printer = new Printer();
        StringMaker maker = new StringMaker(new CLIParser(printer), new StdinPipeParser());
        StringCleaner cleaner = new StringCleaner();
        try {
            printer.printGenericOutput(cleaner.cleanString(maker.retrieveInput(args)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
