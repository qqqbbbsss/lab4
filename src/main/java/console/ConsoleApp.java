package console;

import core.DataProcessor;
import core.FileUtils;
import models.InputArgs;

import java.io.IOException;
import java.util.List;

public class ConsoleApp {

    public static void main(String[] args) {
        try {
            InputArgs ia = new InputArgs(args);

            if (ia.getIn() == null) {
                System.err.println("ошибка:");
                System.exit(1);
            }

            System.out.println("входной файл: " + ia.getIn());

            List<String> lines = FileUtils.read(ia.getIn());
            System.out.println("прочитано строк: " + lines.size());

            System.out.println("\nисходные данные:");
            for (String s : lines) {
                System.out.println(s);
            }

            DataProcessor dp = new DataProcessor();
            String[] inArr = lines.toArray(new String[0]);
            String[] outArr = dp.processPipeline(inArr);

            System.out.println("\nрезультат:");
            for (String s : outArr) {
                System.out.println(s);
            }

            if (ia.hasOut()) {
                List<String> outList = List.of(outArr);
                FileUtils.write(ia.getOut(), outList);
                System.out.println("\nсохранено: " + ia.getOut());
            }

        } catch (IllegalArgumentException e) {
            System.err.println("ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("ошибка ввода/вывода: " + e.getMessage());
        }
    }
}