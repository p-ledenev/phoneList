package data;

import model.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by ledenev.p on 20.07.2015.
 */
public class Reader {

    public List<FutureTask> readStandardInputStream() throws Throwable {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(reader.readLine());
        List<FutureTask> tasks = new ArrayList<FutureTask>(length);

        for (int i = 0; i < length; i++) {
            List<String> phones = readPhones(reader);
            tasks.add(new FutureTask(new ConsistentPhoneListTask(phones)));
        }

        return tasks;
    }

    private List<String> readPhones(BufferedReader reader) throws Throwable {

        int length = Integer.parseInt(reader.readLine());
        List<String> phones = new ArrayList<String>(length);

        for (int i = 0; i < length; i++)
            phones.add(reader.readLine());

        return phones;
    }
}
