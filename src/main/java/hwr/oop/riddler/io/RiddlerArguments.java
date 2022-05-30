package hwr.oop.riddler.io;

public class RiddlerArguments {
    private final String[] args;

    public RiddlerArguments(String[] args) {
        if (args.length != 1)
            throw new IllegalArgumentException("USAGE: java hwr.oop.riddler.Riddler [filepath]");
        this.args = args;
    }

    public String sudokuFilePath() {
        return args[0];
    }
}
