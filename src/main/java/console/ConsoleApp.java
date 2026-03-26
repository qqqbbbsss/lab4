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

            if (args.length == 0) {
                System.out.println("вариант 3 (удаление повторяющихся подряд чисел)");
                System.out.print("выбор (1 или 2): ");

                Scanner sc = new Scanner(System.in);
                int ch = sc.nextInt();

                String inFile;
                String outFile;

                if (ch == 1) {
                    inFile = "test/input01.txt";
                    outFile = "output/result01.txt";
                } else if (ch == 2) {
                    inFile = "test/input02.txt";
                    outFile = "output/result02.txt";
                } else {
                    System.out.println("неверный выбор");
                    inFile = "test/input01.txt";
                    outFile = "output/result01.txt";
                }

                String[] def = {"-i", inFile, "-o", outFile};
                ia = new InputArgs(def);
                sc.close();
            } else {
                ia = new InputArgs(args);
            }

            System.out.println("входной файл: " + ia.getIn());

            List<String> lines = FileUtils.read(ia.getIn());
            System.out.println("строк: " + lines.size());

            System.out.println("\n исходные данные");
            for (String s : lines) {
                System.out.println(s);
            }

            DataProcessor dp = new DataProcessor();
            String[] inArr = FileUtils.toArr(lines);
            String[] outArr = dp.pipeline(inArr);

            System.out.println("(числа удалены)");
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
            System.err.println("ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}