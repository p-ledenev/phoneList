package run;

import data.*;
import model.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by ledenev.p on 20.07.2015.
 */
public class Runner {

    public static void main(String[] args) throws Throwable {

        Reader reader = new Reader();
        List<FutureTask> tasks = reader.readStandardInputStream();

        ExecutorService executor = Executors.newCachedThreadPool();

        for (FutureTask task : tasks) {
            executor.submit(task);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        for (FutureTask<TaskResult> task : tasks) {
            TaskResult future = task.get();
            System.out.println(future.printResult());
        }
    }
}
