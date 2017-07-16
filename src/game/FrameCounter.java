package game;

/**
 * Created by cuonghx2709 on 7/16/2017.
 */
public class FrameCounter {
    int count;
    int countmax;
    public  FrameCounter(int countmax){
        this.countmax = countmax;
    }
    public void reset(){
        count = 0;
    }
    public boolean run(){
        if(count < countmax) {
            count ++;
            return  false;
        }
        return true;
    }
}
