record Point2(int x, int y) {
    //  1. 인스턴스 변수, 초기화 블럭 추가 불가
//    public final int hash; // 에러. iv 추가 불가
//    { hash = 0; } // 에러. 인스턴스 초기화 블럭 추가 불가

    // 2. 클래스 변수(cv) 추가 가능
    public static final int MAX_VALUE;
    public static final int MIN_VALUE;

    // 3. static 초기화 블럭 추가 가능
    static {
        MAX_VALUE = 100;
        MIN_VALUE = -100;
    }

    // 4. static 메서드 추가 가능 - 두 점 p, p2 사이의 거리를 반환
    public static double getDistance(Point2 p, Point2 p2){
        int a = p2.x - p.x;
        int b = p2.y - p.y;
        return Math.sqrt(a*a + b*b);
    }
    // 5. 생성자 추가 가능
    public Point2(){
        this(0,0);  // Point2(int x, int y) 호출
    }

    // 6. 컴팩트 생성자(compact constructor)
//  public Point2(int x, int y) {
    public Point2 {
        if(!((MIN_VALUE <= x && x<=MAX_VALUE)
                && (MIN_VALUE <= y && y<=MAX_VALUE)))
            throw new IllegalArgumentException("Invalid Argument");
//          this.x = x;
//          this.y = y;
    }

    // 7. 인스턴스 메서드 추가 가능 - 두 점 this, p 사이의 거리를 반환
    public double getDistance(Point2 p){
        return Point2.getDistance(this, p);
    }

    // 8. 오버라이딩 가능
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}

public class RecordEx2 {
    public static void main(String[] args) {
        var p = new Point2();
        var p2 = new Point2(1,1);
        System.out.println("p  = " + p);
        System.out.println("p2 = " + p2);
        System.out.println("p.getDistance(p2) = " + p.getDistance(p2));
        System.out.println("Point2.getDistance(p, p2) = "
                + Point2.getDistance(p, p2));
    }
}