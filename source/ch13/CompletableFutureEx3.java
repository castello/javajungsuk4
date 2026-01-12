import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// [예제3] complete() 관련 메서드 - completeOnTimeout
public class CompletableFutureEx3 {
    public static void main(String[] args) {
        CompletableFuture<String>  cFuture = CompletableFuture.supplyAsync(()-> getInput())
                .completeOnTimeout("N", 10, TimeUnit.SECONDS) // TimeoutException이 발생하지 않는다.
                .exceptionally(e-> {  // 예외 처리
                    System.out.println("e = " + e);
                    System.out.println("입력이 잘못되었습니다.");
                    return "N"; // 기본값
                });

        CompletableFuture<Void>     cFuture2  = CompletableFuture.runAsync(()-> countDown());
        System.out.println("cFuture.join() = " + cFuture.join()); // cFuture.join() = N
    }
    
    static String getInput() { // 동기 메서드
        System.out.print("계속하시겠습니까? >>");

        String input = new Scanner(System.in).nextLine();
        if(!(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")))
            throw new InputMismatchException("입력이 잘못되었습니다.:" + input);

        return input;
    }

    static void countDown() {
        for (int i = 10; i >= 1; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch(Exception e) {}
        }
    }
}
