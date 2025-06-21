import java.util.Scanner;
import java.util.concurrent.*;

public class FutureEx2 {
    public static void main(String[] args) throws Exception {
        Callable<Integer> task = ()-> {
            return getInput();
        };

        System.out.print("좋아하는 숫자를 입력하세요 >>");
        Future<Integer> future = getInputAsync(task); // 비동기 메서드 호출
        countDown();
        System.out.println("입력하신 내용=" + future.get()); // 입력 완료까지 기다림
    }

    static Future getInputAsync(Callable<Integer> task) { // 비동기 메서드
        return new IntegerFuture(task);
    }

    static Integer getInput() { // 동기 메서드
        Integer input = new Scanner(System.in).nextInt();
        return input;
    }

    static void countDown() {
        for (int i = 10; i >= 1; i--) {
            try{
                Thread.sleep(1000);
            }catch(Exception e) {}
            System.out.println(i);
        }
    }

    static class IntegerFuture implements Future<Integer> {
        private boolean isDone = false;
        private boolean isCancelled = false;
        private Callable<Integer> task;
        private Integer result;

        IntegerFuture(Callable<Integer> task) {
            new Thread(() -> { // 작업(task)을 별도의 쓰레드로 처리(동기 -> 비동기)
                try{
                    Integer tmp = task.call();
                    synchronized(this) {
                        result = tmp;
                        isDone = true;
                        notify();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            this.task = task;
        }

        public Integer get() throws InterruptedException,ExecutionException {
            // 비동기 작업이 완료될 때까지 기다린다.
            synchronized (this) {
                while (!isDone)
                    wait(); // 완료되지 않았으면 락을 풀고 기다린다.
            }
            return result;
        }

        public Integer get(long timeout, TimeUnit unit)
                throws InterruptedException, ExecutionException, TimeoutException {
            throw new UnsupportedOperationException("구현되지 않은 기능");
        }
        public boolean isCancelled() { return isCancelled; }
        public boolean isDone() { return isDone; }
        public boolean cancel(boolean mayInterruptIfRunning) {
            throw new UnsupportedOperationException("구현되지 않은 기능");
        }
    }
}