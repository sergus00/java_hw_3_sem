package org.dz1;

import org.apache.commons.cli.*;
import org.dz1.configs.DistrictsConfig;
import org.dz1.services.ChildService;
import org.dz1.services.ParentsService;

public class Main {
    public static void main(String[] args) {
        DistrictsConfig.addDistricts();

        Options options = new Options();
        options.addOption(Option.builder("p")
                .desc("Enter the name of one or both parents. Write -m <mother name> -f <father_name>")
                .build());
        options.addOption(Option.builder("m")
                .hasArg()
                .argName("mother name")
                .desc("Adding mother to parents")
                .build());
        options.addOption(Option.builder("f")
                .hasArg()
                .argName("father name")
                .desc("Adding father to parents")
                .build());
        options.addOption(Option.builder("c")
                .numberOfArgs(3)
                .argName("child name> <parents id> <child age")
                .desc("Adding child")
                .build());
        options.addOption(Option.builder("a")
                .numberOfArgs(2)
                .argName("parents id> <address id")
                .desc("Changing address of the parents")
                .build());
        options.addOption(Option.builder("s")
                .numberOfArgs(2)
                .argName("child id> <school id")
                .desc("Changing school of the child")
                .build());

        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        formatter.printHelp("cmd inputs", options);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption('p')) new ParentsService().AddParents(line);
            if (line.hasOption('a')) new ParentsService().ChangeAddress(line.getOptionValues('a'));
            if (line.hasOption('c')) new ChildService().AddChild(line.getOptionValues('c'));
            if (line.hasOption('s')) new ChildService().ChangeSchool(line.getOptionValues('s'));
        } catch (ParseException exp) {
            System.out.println("Unexpected exception:" + exp.getMessage());
        }
    }
}
