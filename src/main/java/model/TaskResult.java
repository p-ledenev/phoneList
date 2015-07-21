package model;

/**
 * Created by ledenev.p on 21.07.2015.
 */
public class TaskResult {

    private Result result;
    private String comment;

    public TaskResult(Result result, String comment) {
        this.result = result;
        this.comment = comment;
    }

    public static TaskResult yes() {
        return new TaskResult(Result.YES, null);
    }

    public static TaskResult no(String comment) {
        return new TaskResult(Result.NO, comment);
    }

    public String printResult() {
        return result.toString();
    }

    public  String print() {
        return printResult() + " " + comment;
    }

    private enum Result {
        YES, NO;
    }

    ;
}
