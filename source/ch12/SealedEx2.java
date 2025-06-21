import java.lang.reflect.Modifier;

// 같은 파일 내 자식이 있으면 permits 생략 가능
sealed interface Val {} // permits IntVal, LongVal {}
record IntVal(int value)  implements Val {}
record LongVal(long value) implements Val {}

public class SealedEx2 {
    public static void main(String[] args) {
        Class cls = Val.class;
        System.out.println(cls.getName() + " isSealed()=" + cls.isSealed());

        // 실드 인터페이스의 모든 자식의 정보를 출력
        for(Class c : cls.getPermittedSubclasses()) {
            String clsInfo = Modifier.isFinal(c.getModifiers()) ? "final ":"";
            clsInfo += c.isSealed() ? "sealed " : "";
            clsInfo += c.isRecord() ? "record " : "";
            clsInfo += c.getName();
            System.out.println(clsInfo);
        }
    }
}