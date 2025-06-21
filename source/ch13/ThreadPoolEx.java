
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class SingleThreadPool {
    private volatile boolean isStopped = false;
    private final BlockingQueue<Runnable> taskQueue; // 작업 큐
    private Worker worker; // 일꾼 쓰레드

    public SingleThreadPool(int size){ // size는 작업 큐의 크기
        taskQueue = new LinkedBlockingQueue<>(size);
        worker = new Worker();
        worker.start();
    }

    public void submit(Runnable task) {
        taskQueue.offer(task); // 작업 큐에 작업(task) 추가
    }

    public void shutdown() {
        while(!taskQueue.isEmpty()){
            try {
                Thread.sleep(100); // 0.1초마다 확인
            } catch(Exception e){ }
        }
        isStopped = true;
        worker.interrupt();
    }

    private class Worker extends Thread {
        public void run() {
            // 작업 큐에서 작업을 계속 꺼내서 실행
            while (!isStopped){
                try {
                    // 작업 큐에서 작업을 하나씩 꺼내서 실행
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    break;
                }
            }
        } // end of run()
    }
}

public class ThreadPoolEx {
    public static void main(String[] args) {
        SingleThreadPool threadPool = new SingleThreadPool(10);

        // 5개의 작업을 제출(submit)
        for (int i = 1; i <= 5; i++) {
            final int taskNo = i; // final 생략 가능
            threadPool.submit(() -> work(taskNo));
        }

        threadPool.shutdown(); // 모든 작업이 끝난 후에 종료
    }

    public static void work(int taskNo){
        // 1초간 작업한다고 가정 - 실제론 1초간 쉼.
        System.out.println("Task " + taskNo + " - started");
        try {
            Thread.sleep(1000); // 작업 수행
        } catch (InterruptedException e) { }
        System.out.println("Task " + taskNo + " - finished");
    }
}