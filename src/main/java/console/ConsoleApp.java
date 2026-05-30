package console;

import core.DataProcessor;
import core.FileUtils;
import models.InputArgs;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    public static void main(String[] args) {
        try {
            InputArgs ia;
            int processorChoice = 1;

            if (args.length == 0) {
                System.out.println("Выберите вариант обработки:");
                System.out.println("1 вариант 3 (удаление дубликатов)");
                System.out.println("2 вариант 8 (сортировка положительных)");
                System.out.println("3 вариант 9 (max и min)");


                Scanner sc = new Scanner(System.in);
                processorChoice = sc.nextInt();
                sc.close();

                if (processorChoice < 1 || processorChoice > 3) {
                    System.err.println("ошибка");
                    processorChoice = 1;
                }

                String inFile = "test/input01.txt";
                String outFile = "output/result01.txt";

                String[] def = {"-i", inFile, "-o", outFile};
                ia = new InputArgs(def);
            } else {
                ia = new InputArgs(args);

                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("-p") && i + 1 < args.length) {
                        try {
                            processorChoice = Integer.parseInt(args[i + 1]);
                        } catch (NumberFormatException e) {
                            System.err.println("ошибка");
                            processorChoice = 1;
                        }
                        break;
                    }
                }
            }

            System.out.println("входной файл: " + ia.getIn());

            List<String> lines = FileUtils.read(ia.getIn());
            System.out.println("строк: " + lines.size());

            System.out.println("\nисходные данные:");
            for (String s : lines) {
                System.out.println(s);
            }

            DataProcessor dp = new DataProcessor();
            String[] inArr = FileUtils.toArr(lines);
            String[] outArr = dp.pipeline(inArr, processorChoice);

            System.out.println("\nобработанные данные:");
            for (String s : outArr) {
                System.out.println(s);
            }

            if (ia.hasOut()) {
                List<String> outList = FileUtils.toList(outArr);
                FileUtils.write(ia.getOut(), outList);
                System.out.println("\nсохранено: " + ia.getOut());
            }

        } catch (IllegalArgumentException e) {
            System.err.println("ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("ошибка" + e.getMessage());
        } catch (Exception e) {
            System.err.println("ошибка" + e.getMessage());
            e.printStackTrace();
        }
    }
}