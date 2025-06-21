record Point(int x, int y) {}

public class RecordEx {
    public static void main(String[] args) {
        var p  = new Point(1,2);
        var p2 = new Point(1,2);
        var p3 = new Point(1,1);
        System.out.println("p = " + p);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
        System.out.println("p.x() = " + p.x());
        System.out.println("p.y() = " + p.y());
        System.out.println("p.equals(p2) = " + p.equals(p2));
        System.out.println("p.equals(p3) = " + p.equals(p3));
        System.out.println("p.hashCode() = " + p.hashCode());
        System.out.println("p2.hashCode() = " + p2.hashCode());
        System.out.println("p3.hashCode() = " + p3.hashCode());
    }
}