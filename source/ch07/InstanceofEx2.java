public class InstanceofEx2 {
    public static void main(String[] args) {
        Point2D p  = new Point2D(1,1);
        Point2D p2 = new Point2D(1,1);
        Point2D p3 = new Point2D(0,0);
        System.out.println("p  = " + p);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
        System.out.println("p.equals(p2)==" + p.equals(p2));
        System.out.println("p.equals(p3)==" + p.equals(p3));
    }
}

class Point2D {
    private int x;
    private int y;

    Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object obj) {
//        if(!(obj instanceof Point2D))
//            return false;
//
//        Point2D p = (Point2D)obj;
//        return x == p.x && y == p.y;
        return (obj instanceof Point2D p) && x == p.x && y == p.x;
    }
    public String toString() {
        return "("+x+","+y+")";
    }
}
