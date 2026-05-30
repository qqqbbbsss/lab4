package models;

public class InputArgs {
    private String in;
    private String out;

    public InputArgs(String[] args) {
        parse(args);
    }

    private void parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i") && i + 1 < args.length) {
                in = args[i + 1];
                i++;
            } else if (args[i].equals("-o") && i + 1 < args.length) {
                out = args[i + 1];
                i++;
            }
        }

    }

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    public boolean hasOut() {
        return out != null;
    }
}