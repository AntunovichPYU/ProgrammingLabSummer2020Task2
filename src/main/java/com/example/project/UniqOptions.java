package com.example.project;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class UniqOptions {
    @Argument(usage = "input file name", metaVar = "INPUT")
    public String inputName;

    @Option(name = "--help")
    public boolean help;

    @Option(name = "-o", usage = "output file name", metaVar = "OUTPUT")
    public String outputName;

    @Option(name = "-i", usage = "ignore symbols' uppercase")
    public boolean ignoreCase;

    @Option(name = "-s", usage = "ignore first N symbols of string", metaVar = "N")
    public int num;

    @Option(name = "-u", usage = "outputs only unique strings")
    public boolean unique;

    @Option(name = "-c", usage = "outputs number of duplicates before string")
    public boolean count;

    public UniqOptions(String[] args) {
        final CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.out.println("Unable to parse command-line options: " + e.getMessage());
            System.out.println("Try --help");
        }

        if (help) {
            parser.printUsage(System.out);
            System.exit(0);
        }
    }
}