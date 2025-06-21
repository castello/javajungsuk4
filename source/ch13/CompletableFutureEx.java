import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.*;

public class CompletableFutureEx {
    public static void main(String[] args) {
        // 2개의 작업을 비동기로 실행
        CompletableFuture<String>  cFuture =
                CompletableFuture.supplyAsync(() -> getInput());
        CompletableFuture<Void>    cFuture2 =
                CompletableFuture.runAsync(() -> countDown(cFuture));

        String input = "";
        try {
            // get()은 필수 예외처리 필요 - ExecutionException, InterruptedException
            input = cFuture.get();
        } catch(Exception e) {
            System.out.println("e=" + e);
            input = "N"; // 예외가 발생하면 기본 값 "N"으로
        }
        System.out.println("input = " + input);
        System.out.println("cFuture.isCompletedExceptionally() = "
                + cFuture.isCompletedExceptionally());

        final boolean JOIN = false; // true로 바꿔서 실행하고 결과 비교
        if(JOIN) {
            // 작업이 완료될 때까지 기다렸다가 결과 반환 - 카운트 다운이 완전히 끝날때까지 기다림
            System.out.println("cFuture2.join() = " + cFuture2.join());
        } else {
            // 작업이 완료되었으면 작업결과를 반환, 진행중이면 지정된 값을 결과로 반환
            System.out.println("cFuture2.getNow(null) = "
                    + cFuture2.getNow(null));
        }
    }

    static String getInput() { // 동기 메서드
        System.out.print("계속하시겠습니까? >>");

        String input = new Scanner(System.in).nextLine();
        if(!(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")))
            throw new InputMismatchException("입력이 잘못되었습니다.:" + input);

        return input;
    }

    static void countDown(CompletableFuture<String> cFuture) {
        for (int i = 10; i >= 1; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch(Exception e) {}
        }
        // 10초 동안 입력작업(cFuture)이 끝나지 않으면 강제로 완료 시킨다.
        final boolean THROW_EXCEPTION = false; // true로 바꿔서 실행하고 결과 비교
        if(THROW_EXCEPTION) {
            // 작업이 진행중이면, 예외를 발생시켜서 작업을 종료한다.
            cFuture.completeExceptionally(new TimeoutException("입력시간 초과."));
        } else {
            // 작업이 진행중이면, 작업을 종료하고 지정된 값을 작업 결과로 반환
            cFuture.complete("N");
        }
    }
}