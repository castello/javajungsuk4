import java.util.Scanner;
import java.util.concurrent.*;

public class FutureEx3 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Callable<Integer> task = ()-> { return getInput(); };
        Future<Integer> future = es.submit(task); // 작업을 제출(submit)

        countDown(future); // 카운트 다운을 시작

        Integer result = 0;
        try {
            // 1. 작업(입력)이 끝날 때까지 계속 기다림
            result = future.get();

            // 2. 지정된 시간 동안만 기다렸다가 작업 결과 반환
            //  시간 내에 작업이 끝나면, 결과 반환.
            //  시간 내에 작업이 안끝나면 TimeoutException발생.
//            result = future.get(5, TimeUnit.SECONDS);

            // 3. 기다리지 않고 바로 작업 결과 반환
            //   작업이 끝났으면, 결과 반환.
            //   작업이 안끝났으면 IllegalStateException발생
//            result = future.resultNow();
            System.out.println("입력 내용=" + result);
        } catch(Exception e){
            e.printStackTrace();
            // 작업중 예외가 발생하면 Future의 상태는 FAILED
            if(future.state()==Future.State.FAILED) {
                Throwable ex = future.exceptionNow();
                System.out.println("입력이 잘못되었습니다. : "+ex);
            } else {
                System.out.println("입력시간이 초과되었습니다.");
            }
        }

        es.shutdown();
        try { System.in.close(); } catch (Exception e) {}
    }

    static Integer getInput() { // 동기 메서드
        System.out.print("좋아하는 숫자를 입력하세요 >>");

        // 숫자를 입력하지 않으면 InputMismatchException 발생
        Integer input = new Scanner(System.in).nextInt();
        System.out.println("input = " + input);
        return input;
    }

    static void countDown(Future future) {
        for (int i = 10; i >= 1; i--) {
            try{
                Thread.sleep(1000);
            }catch(Exception e) {}
            System.out.println(i);

            // 작업(입력)이 종료되면 카운트 다운을 중단
            if(future.state()!=Future.State.RUNNING)
                break;
        }
    }
}