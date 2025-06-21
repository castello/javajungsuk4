import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureEx2 {
    public static void main(String[] args) {
        CompletableFuture<String>  cFuture = CompletableFuture.supplyAsync(()-> getInput())
                .orTimeout(11, TimeUnit.SECONDS) // 11초 후 TimeoutException발생
                .exceptionally(e-> {  // 예외 처리
                    System.out.println("e=" + e);
                    if(e instanceof TimeoutException)
                        System.out.println("입력시간이 초과되었습니다.");
                    else if(e instanceof CompletionException)
                        System.out.println("입력이 잘못되었습니다.");
                    return "N"; // 기본값
                });
        // delayedExecutor를 이용해서 작업이 1초 후에 시작되게 함
        CompletableFuture<Void> cFuture2  = CompletableFuture.runAsync(()-> countDown(), CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));

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
