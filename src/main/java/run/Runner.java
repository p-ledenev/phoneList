package run;

import data.*;
import model.*;
import org.slf4j.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by ledenev.p on 20.07.2015.
 */
public class Runner {

    public static Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) throws Throwable {

        if (args.length < 1)
            throw new Exception("Enter file name");

       Date dateStart = new Date();

        Reader reader = new Reader();

        List<FutureTask> tasks = reader.read(args[0]);

        ExecutorService executor = Executors.newCachedThreadPool();

        for (FutureTask task : tasks) {
            executor.submit(task);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        for (FutureTask<TaskResult> task : tasks) {
            TaskResult future = task.get();
            logger.info(future.printResult());
            logger.debug(future.print());

            System.out.println(future.print());
        }

        Date dateEnd = new Date();
        logger.info("All threads finished. Execution time (seconds): " + (dateEnd.getTime() - dateStart.getTime()) / 1000.);
    }
}
