import java.util.*;

/**
 * Created by ledenev.p on 20.07.2015.
 */

public class Node {

    private List<Node> children;
    private Integer digit;

    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int digit) {
        this();

        this.digit = digit;
    }

    public boolean hasDigit(int digit) {
        return this.digit == digit;
    }

    public void process(StringIterator phone) throws ListInconsistentFailure {

        if (!phone.hasNextDigit())
            return;

        Node node = findChildFor(phone);
        node.process(phone);
    }

    private Node findChildFor(StringIterator phone) throws ListInconsistentFailure {

        int phoneDigit = phone.nextDigit();

        for (Node node : children) {
            if (node.hasDigit(phoneDigit)) {

                if (!phone.hasNextDigit())
                    throw new ListInconsistentFailure("Phone " + phone.print() + " is full prefix");

                return node;
            }
        }

        Node node = new Node(phoneDigit);
        children.add(node);

        return node;
    }
}
