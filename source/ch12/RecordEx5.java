public class RecordEx5 {
    // 중첩 레코드는 static(생략가능)
    static record Circle(Point p, double r){
        static record Point(int x, int y){} // 레코드의 중첩 - Circle안의 Point
    }

    public static void main(String[] args) {
        var c = new Circle(new Circle.Point(1,2), 3.0);
        System.out.println("c = " + c);

        // instanceof를 이용한 구조분해
        if(!(c instanceof Circle(Circle.Point(var x, var y),var r))) return;
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("r = " + r);

        // instanceof를 이용한 구조분해없이 읽기
        System.out.println("c.p = " + c.p);
        System.out.println("c.p.x() = " + c.p.x());
        System.out.println("c.p.y() = " + c.p.y());
        System.out.println("c.r() = " + c.r());
    }
}