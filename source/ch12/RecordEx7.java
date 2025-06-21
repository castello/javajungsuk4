import java.math.*;
import java.util.*;

public class RecordEx7 {
    // 중첩 레코드 - static이 생략됨
    record Point<T extends Number>(T x, T y) implements Comparable<Point<T>> {
        public int compareTo(Point<T> p) {
            // 먼저 x와 x2를 비교한다. - 정수뿐만 아니라 실수도 비교해야하므로 double로 비교
            // T는 Number의 자손이므로 모든 T는 Number의 doubleValue()를 가지고 있음.
            int result =
                    Double.compare(x.doubleValue(), p.x().doubleValue());

            // x와 x2가 같으면 y와 p.y를 비교한다.
            return result!=0 ? result :
                    Double.compare(y.doubleValue(), p.y().doubleValue());
        }
        public String toString() { return "("+x+", "+y+")"; }
    };

    public static void main(String[] args) {
        var list = new ArrayList<Point>(List.of(
                new Point<Integer>(3,1),
                new Point<BigDecimal>(BigDecimal.valueOf(2.3),BigDecimal.valueOf(4.5)),
                new Point<Double>(2.1,1.0),
                new Point<BigInteger>(BigInteger.valueOf(1),BigInteger.valueOf(2)),
                new Point<Integer>(1,1), new Point<Integer>(2,1)
        ));

        System.out.println("list = " + list);
        Collections.sort(list);
        System.out.println("list = " + list);
    }
}