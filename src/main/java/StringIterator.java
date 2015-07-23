/**
 * Created by ledenev.p on 20.07.2015.
 */
public class StringIterator {

    private String str;
    private int index;

    public StringIterator(String str) {
        this.str = str;
        index = 0;
    }

    public int nextDigit() {
        int digit = Character.getNumericValue(str.charAt(index));
        index++;

        return digit;
    }

    public boolean hasNextDigit() {

        return str.length() > index;
    }

    public String print() {
        return str;
    }
}
