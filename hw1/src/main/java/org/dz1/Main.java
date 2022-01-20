package org.dz1;

import org.apache.commons.cli.*;
import org.dz1.models.*;
import org.dz1.services.AddressService;
import org.dz1.services.ChildService;
import org.dz1.services.SchoolService;
import org.dz1.services.ParentsService;
import org.dz1.configs.DistrictsConfig;

import java.util.List;
import java.util.stream.Collectors;

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
            if (line.hasOption('p')) AddParents(line);
            if (line.hasOption('c')) AddChild(line.getOptionValues('c'));
            if (line.hasOption('a')) ChangeAddress(line.getOptionValues('a'));
            if (line.hasOption('s')) ChangeSchool(line.getOptionValues('s'));
        }
        catch (ParseException exp) {
            System.out.println("Unexpected exception:" + exp.getMessage());
        }
    }
    // TODO Доделать сервисы

    private static void AddParents(CommandLine lineArgs) {
        Parents parents = new Parents();
        if (lineArgs.hasOption('m')) parents.setMother(lineArgs.getOptionValue('m'));
        if (lineArgs.hasOption('f')) parents.setFather(lineArgs.getOptionValue('f'));
        parents.save();
        System.out.println("Добавлены родители " + parents.getMother() + " " + parents.getFather() +
                ", Id " + parents.getId());
    }

    private static void AddChild(String[] lineArgs) {
        Parents parents = new ParentsService().getById(Integer.parseInt(lineArgs[1]));
        if (parents.getAddress() != null) {
            List<School> suitableSchools = new SchoolService().getSchools().stream().filter(x ->
                    parents.getAddress().getDistrict().getAddresses().stream().anyMatch(y ->
                            y.getId() == x.getAddress().getId())).collect(Collectors.toList());
            //TODO переписать на цикл или просто иначе
            System.out.println("Подходящие школы:");
            suitableSchools.forEach(x -> System.out.println(x.getSchoolNumber() + ", Id " + x.getId()));
        }

        Child child = new Child(lineArgs[0], parents, Integer.parseInt(lineArgs[2]));
        child.save();
        System.out.println("Добавлен ребёнок " + child.getName() + ", Id " + child.getId());
    }

    // TODO Объединить две следующие
    private static void ChangeAddress(String[] lineArgs) {
        Parents parents = new ParentsService().getById(Integer.parseInt(lineArgs[0]));
        parents.setAddress(new AddressService().getById(Integer.parseInt(lineArgs[1])));
        parents.update();
        System.out.println("Адрес родителей " + parents.getFather() + " и " + parents.getMother() +
                " с Id " + parents.getId() + " изменён на " + parents.getAddress().getAddress());
    }

    private static void ChangeSchool(String[] lineArgs) {
        Child child = new ChildService().getById(Integer.parseInt(lineArgs[0]));
        child.setSchool(new SchoolService().getById(Integer.parseInt(lineArgs[1])));
        child.update();
        System.out.println("Школа ребёнка " + child.getName() +
                " с Id " + child.getId() + " изменена на " + child.getSchool().getSchoolNumber());
    }
}
