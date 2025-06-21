import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceEx {
    public static void main(String[] args) {
        // 하나의 쓰레드로 작업을 처리하는 ExecutorService를 생성
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 5개의 작업을 executor에게 제출(submit)
        for (int i = 1; i <= 5; i++) {
            final int no = i; // final 생략 가능
            executor.submit(()->System.out.println("Hello"+no));
        }

        executor.shutdown(); // ExecutorService를 종료.
    }
}