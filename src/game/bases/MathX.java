package game.bases;

/**
 * Created by cuonghx2709 on 7/16/2017.
 */
public class MathX {
    public static float clamp(float x, float min, float max){
        if(x > max ){
            return max;
        }
        else if (x < min) {
            return min;
        }else{
            return x;
        }
    }
}
