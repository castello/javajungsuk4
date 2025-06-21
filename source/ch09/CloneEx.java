class Point implements Cloneable {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "x=" + x + ", y=" + y;
    }

    public Object clone() {  // 오버라이딩. 접근 제어자를 protected에서 public으로 변경
        Object obj = null;
        try {
            obj = super.clone();  // clone()은 반드시 예외처리를 해주어야 한다.
        } catch(CloneNotSupportedException e) {}
        return obj;
    }
}
class CloneEx {
    public static void main(String[] args){
        Point original = new Point(3, 5);
        Point copy = (Point)original.clone(); // 복제(clone)해서 새로운 객체를 생성
        System.out.println(original);
        System.out.println(copy);
    }
}