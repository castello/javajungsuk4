import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;

record Rectangle(int w, int h) {}
public class RecordEx3 {
    public static void main(String[] args) {
        Class cls = Rectangle.class; // 레코드 Rectangle의 클래스 객체(설계도)를 얻는다.
        System.out.println("cls = " + cls);
        System.out.println("cls.isRecord() = " + cls.isRecord());
        System.out.println("cls.getSuperclass() = " + cls.getSuperclass());

        System.out.println("= 레코드의 필드(인스턴스 변수) =");
        for(RecordComponent rc : cls.getRecordComponents())
            System.out.println(rc);

        System.out.println("= 레코드의 생성자 =");
        for(Constructor con : cls.getDeclaredConstructors())
            System.out.println(con);

        System.out.println("= 레코드의 메서드 =");
        for(Method m : cls.getDeclaredMethods())
            System.out.println(m);
    }
}