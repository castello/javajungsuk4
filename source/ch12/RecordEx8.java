import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;

@Target({ElementType.RECORD_COMPONENT, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface Range {
    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
}

public class RecordEx8 {
    static record Point(
            @Range(min=0,   max=10) int x,
            @Range(min=-10, max=10) int y
    ) {
        Point(int x, int y) {
            this.x = x;
            this.y = y;

            // 레코드의 모든 컴포넌트(인스턴스 변수)에 대해 반복
            for (RecordComponent rc : getClass().getRecordComponents()) {
                Range anno = rc.getDeclaredAnnotation(Range.class);

                if(anno==null) continue; // 컴포넌트(인스턴스 변수)에 @Range가 없으면
                final int MIN_VALUE = anno.min();
                final int MAX_VALUE = anno.max();

                try {
                    // 컴포넌트(인스턴스 변수)의 getter인 x(), y()를 찾아서 호출
                    Method m =  getClass().getDeclaredMethod(rc.getName());

                    // 아래의 문장으로, x()와 y()에도 @Range가 붙어있는 것을 확인 가능 
//                  System.out.println(m.getDeclaredAnnotation(Range.class)
//										+" "+m.getName()+"()");
                    int rcValue = (int)m.invoke(this); // x(), y()를 호출

                    if(!((MIN_VALUE <= rcValue && rcValue <= MAX_VALUE)))
                        throw new IllegalArgumentException("Invalid Argument:"
                                + rc.getName()+"="+rcValue);
                } catch(ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            } // for
        }
    }
    public static void main(String[] args) {
        Point p = new Point(3, 5);
        System.out.println("p = " + p);
        p = new Point(-1000, 1000); // 에러. 지정된 범위(min~max)를 벗어나는 값
    }
}