interface Shape {}
record RectAngle(int w, int h) implements Shape {}
record Triangle(int w, int h) implements Shape {}
record Circle(int r) implements Shape {}

public class RecordEx4 {
    public static void main(String[] args) {
        System.out.println("RectAngle(3,5) = " + getArea(new RectAngle(3, 5)));
        System.out.println("Triangle(3,5)  = " + getArea(new Triangle(3, 5)));
        System.out.println("Circle(1) = " + getArea(new Circle(1)));
    }
    static double getArea(Shape s){
        if(s instanceof RectAngle(int w, int h)) {
            return w * h;
        } else if(s instanceof Triangle(int w, int h)){
            return w * h * 0.5;
        } else if(s instanceof Circle(int r)){
            return r * r * Math.PI;
        } else {
            return -1.0;
        }
    }
    static double getArea2(Shape s){
        return switch(s) {
            case RectAngle r -> r.w() * r.h();
            case Triangle t  -> t.w() * t.h() * 0.5;
            case Circle c    -> c.r() * c.r() * Math.PI;
            default -> -1.0;
        };
    }

    static double getArea3(Shape s){
        return switch(s) { // 레코드 컴포넌트의 타입 추론이 가능 - var
            case RectAngle(var w2, var h2) -> w2 * h2; // 변수 이름이 달라도 된다.
            case Triangle(var w, var h)  -> w * h * 0.5;
            case Circle(var r) -> r * r * Math.PI;
            default -> -1.0;
        };
    }
}
