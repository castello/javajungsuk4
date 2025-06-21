import java.math.*;
import java.util.*;

public class RecordEx6 {
    public static void main(String[] args) {
        // 지역 레코드 - 메서드 내에 선언된 레코드
        record Point<T extends Number>(T x, T y) {
            public String typeOf() {
                return switch(this.x()) {
                    case Integer i -> "Point<Integer>";
                    case Double d -> "Point<Double>";
                    case BigInteger d -> "Point<BigInteger>";
                    default -> "Point";
                };
            }
        };

        Point<Integer> p = new Point<>(1,2);
        var p2 = new Point<Long>(3L,5L);
        var p3 = new Point<Double>(2.0,4.0);
        System.out.println("p = " + p);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
        System.out.println("p.typeOf()  = " + p.typeOf());
        System.out.println("p2.typeOf() = " + p2.typeOf());
        System.out.println("p3.typeOf() = " + p3.typeOf());
    }
}
