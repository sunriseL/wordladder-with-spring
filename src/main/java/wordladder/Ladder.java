package wordladder;

public class Ladder {
    private final int status;
    private final String errMessage;
    private String[] ladder;

    public Ladder(int s, String m, String[] l){
        this.status = s;
        this.errMessage = m;
        this.ladder = l;
    }
    public int getStatus() {
        return status;
    }

    public String[] getLadder() {
        return ladder;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
