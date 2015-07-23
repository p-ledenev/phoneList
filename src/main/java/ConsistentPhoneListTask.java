import java.util.*;
import java.util.concurrent.*;

/**
 * Created by ledenev.p on 20.07.2015.
 */
public class ConsistentPhoneListTask implements Callable<TaskResult> {

    List<String> phones;
    Node heap;

    public ConsistentPhoneListTask(List<String> phones) {
        this.phones = phones;
        heap = new Node();
    }

    public TaskResult call() throws Exception {
        phones.sort(new StringComparator());

        for (String phone : phones) {
            StringIterator iterator = new StringIterator(phone);

            if (!iterator.hasNextDigit())
                continue;

            try {
                heap.process(iterator);

            } catch (ListInconsistentFailure e) {
                return TaskResult.no(e.getMessage());
            }
        }

        return TaskResult.yes();
    }
}
