package screen;

public class FailMessage extends RuntimeException{
    public FailMessage(String msg){
        super(msg);
    }
}
