package model;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by ledenev.p on 20.07.2015.
 */
public class ConsistentPhoneListTask implements Callable<TaskResult> {

    List<String> phones;
    List<Node> trees;

    public ConsistentPhoneListTask(List<String> phones) {
        this.phones = phones;

        trees = new ArrayList<Node>();
    }

    public TaskResult call() throws Exception {
        phones.sort(new StringComparator());

        for (String phone : phones) {
            StringIterator iterator = new StringIterator(phone);

            if (!iterator.hasNextDigit())
                continue;

            Node node = findTreeFor(iterator.nextDigit());

            try {
                node.process(iterator);

            } catch (ListInconsistentFailure e) {
                return TaskResult.no(e.getMessage());
            }
        }

        return TaskResult.yes();
    }

    private Node findTreeFor(int digit) {
        for (Node node : trees) {
            if (node.hasDigit(digit)) {
                return node;
            }
        }

        Node node = new Node(digit);
        trees.add(node);

        return node;
    }
}
