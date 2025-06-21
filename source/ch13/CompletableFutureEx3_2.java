import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

// [예제3] complete() 관련 메서드 - 아주 간단하게 가자.
public class CompletableFutureEx3_2 {
    public static void main(String[] args) {
//        CompletableFuture<String>  cFuture = CompletableFuture.supplyAsync(()-> getInput());

        CompletableFuture<String> cFuture3 = CompletableFuture.completedFuture("Y");
        System.out.println("cFuture3.state() = " + cFuture3.state()); // SUCCESS
        System.out.println("cFuture3.join() = " + cFuture3.join());

        // failedFuture()에서 발생한 예외는 처리하지 않아도 되는지?
        CompletableFuture<String> cFuture4 = CompletableFuture.failedFuture(new InputMismatchException("입력이 잘못되었습니다."));
        System.out.println("cFuture4.state() = " + cFuture4.state()); // FAILED
        System.out.println("cFuture3.isCompletedExceptionally() = " + cFuture3.isCompletedExceptionally()); // false

//                .exceptionally(e->{
//                    return CompletableFuture.completedFuture("N");
//                });
// 위의 코드가 안된다~. failedFuture()를 쓰는 법을 확인해 볼 것. 2025.5.25. 9:30 PM

//        CompletableFuture<Void>     cFuture2  = CompletableFuture.runAsync(()-> countDown(cFuture));
//        boolean result = cFuture.completeExceptionally(new InputMismatchException("입력이 잘못되었습니다."));
//        System.out.println("result = " + result);
//        System.out.println("cFuture.isCompletedExceptionally() = " + cFuture.isCompletedExceptionally());
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
