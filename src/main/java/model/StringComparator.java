package model;

import java.util.*;

/**
 * Created by ledenev.p on 20.07.2015.
 */
public class StringComparator implements Comparator<String> {

    public int compare(String o1, String o2) {

        if (o1.length() > o2.length())
            return -1;

        if (o1.length() < o2.length())
            return 1;

        return 0;
    }
}
