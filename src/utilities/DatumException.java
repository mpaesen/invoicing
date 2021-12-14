package utilities;

public class DatumException extends Exception {
    private final String text;

    public DatumException(String str) {
        text = str;
    }

    public DatumException(Date arg) {
        this(arg.toString());
    }

    @Override
    public String toString() {
        return " is geen correcte datum! [" + text + "]";
    }
}
