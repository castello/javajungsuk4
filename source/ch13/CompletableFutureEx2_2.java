import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// [예제2] exceptionally(), orTimeout() - 여기에 더 추가할 건 없나?
// defaultExecutor(), delayedExecutor() - handle()사용한 버전.
public class CompletableFutureEx2_2 {
    public static void main(String[] args) {
        CompletableFuture<String>  cFuture = CompletableFuture.supplyAsync(() -> getInput())
//                .orTimeout(11, TimeUnit.SECONDS) // TimeoutException발생
                .whenComplete((result, ex)->{

                })
                .completeOnTimeout("N", 11, TimeUnit.SECONDS)
                .handle((value, e) -> {
                    System.out.println("e = " + e);
                    System.out.println("value = " + value);
                    if(e!=null){ // 예외가 발생했으면
                        if(e instanceof TimeoutException)
                            System.out.println("입력시간이 초과되었습니다.");
                        else if(e instanceof CompletionException)
                            System.out.println("입력이 잘못되었습니다.");
                        return "N"; // 기본값
                    }
                    // 예외가 발생하지 않았으면
                    return value; // 작업 결과값
                });

        CompletableFuture<Void>     cFuture2  = CompletableFuture.runAsync(()-> countDown(), CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));

        System.out.println("cFuture.join() = " + cFuture.join()); // cFuture.join() = N
        System.out.println("cFuture.defaultExecutor() = " + cFuture.defaultExecutor());
        System.out.println("cFuture2.defaultExecutor() = " + cFuture2.defaultExecutor());
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
