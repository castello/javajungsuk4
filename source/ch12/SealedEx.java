sealed interface Type permits ValType, RefType {}
sealed interface ValType extends Type permits Num {}
sealed interface RefType extends Type permits Obj {}

// 참조형(RefType)의 자손들 
non-sealed class Obj implements RefType {}
class Tv extends Obj {}

// 기본형(ValType)의 자손들 
sealed interface Num extends ValType permits Int, Lng, Flt, Dbl {}
record Int(Integer val) implements Num {} // 레코드와 열거형은 묵시적 final 
record Lng(Long val)    implements Num {}
record Flt(Float val)   implements Num {}
record Dbl(Double val)  implements Num {}

public class SealedEx {
    static String typeOf(Type type) {
        return switch (type) {
            case Type t -> "Type";
//          default -> "Type";
        };
    }

    static String typeOf2(Type type) {
        return switch (type) {
            case RefType r -> "RefType";
            case ValType v -> "ValType";
        };
    }

    static String typeOf3(RefType type) { // 매개변수의 타입이 RefType
        return switch (type) {
            case Tv t  -> "Tv";
            case Obj o -> "Obj";
//          case RefType v -> "RefType";
        };
    }

    static String typeOf4(Type type) {
        return switch (type) {
            case Int i -> "Int";
            case Lng l -> "Lng";
            case Flt f -> "Flt";
            case Dbl d -> "Dbl";
//          case ValType v -> "ValType";
            case RefType r -> "RefType";
        };
    }

    public static void main(String[] args) {
        System.out.println("typeOf4(new Tv()) = " + typeOf4(new Tv()));
        System.out.println("typeOf4(new Int(10)) = " + typeOf4(new Int(10)));
        System.out.println("typeOf4(new Lng(100L)) = "+typeOf4(new Lng(100L)));
        System.out.println("typeOf4(new Flt(2f)) = " + typeOf4(new Flt(2f)));
        System.out.println("typeOf4(new Dbl(4.0)) = " + typeOf4(new Dbl(4.0)));
        System.out.println("typeOf4(new Int(100)) = " + typeOf4(new Int(100)));
    }
}