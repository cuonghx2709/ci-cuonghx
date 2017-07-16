package game.bases;

/**
 * Created by cuonghx2709 on 7/16/2017.
 */
public class Vector2D {
    public float x ;
    public  float y ;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public  void addUp(Vector2D b){
        x = x + b.x;
        y = y + b.y;
    }
    public float magnitude(){
        return (float) Math.sqrt(x*x + y*y );
    }
    public void multiplyBy(float n){
        x = x*n;
        y = y*n;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    public Vector2D add(float x, float y){
        return new Vector2D(this.x + x, this.y + y);
    }


    public Vector2D(){
        this(0,0);
    }

    public void addUp(float x, float y){
        this.x += x;
        this.y += y;
    }

    public Vector2D add(Vector2D V){
        Vector2D n = new Vector2D();
        n.x = x + V.x;
        n.y = y + V.y;
        return n;
    }

    public  void set(float x, float y){
        this.x = x;
        this.y = y;
    }
    public  void set(Vector2D other){
        set(other.x, other.y);
    }

    public Vector2D multiply(float f){
        Vector2D n = new Vector2D();
        n.x = this.x * f;
        n.y = this.y * f;
        return n;
    }
    public Vector2D  normdlize(){
        Vector2D n = this.multiply(1/this.magnitude());
        return  n;
    }
    public  Vector2D clone(){
        Vector2D n = new Vector2D();
        n = this;
        return  n;
    }
    public  Vector2D invevt(){
        Vector2D n = this.multiply(-1);
        return  n;
    }

}
