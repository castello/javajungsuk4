
import java.util.*;
import java.util.concurrent.*;

public class ExecutorServiceEx3 {
    public static void main(String[] args) {
        int[] arr = new int[40_000_000];
        int answer = (int)(Math.random() * arr.length);
        int bomb = (arr.length-1) - answer; // anwer와 겹치지 않는 위치
        arr[answer] = 1; // 임의의 위치에 정답(1)을 저장
        arr[bomb] = -1;  // 임의의 위치에 폭탄(-1)을 저장
        System.out.println("정답 = " + answer);
        System.out.println("폭탄 = " + bomb);

        Callable<String> task = ()->{
            String name = Thread.currentThread().getName();

            while(true) {
                int idx = (int)(Math.random() * arr.length);
                if(arr[idx]==1) { // 정답을 찾으면, 쓰레드의 이름을 반환
                    System.out.println("정답 발견 by " + name +" idx="+idx);
                    return name;
                }
                if(arr[idx]==-1) { // 폭탄(-1)을 발견하면 예외 발생시키기
                    System.out.println("폭탄 발견 by " + name +" idx="+idx);
                    throw new Exception("failed - " + name);
                }
            }
        };

        // 5개의 작업(task)을 List에 저장
        List<Callable<String>> taskList
                = Arrays.asList(task, task, task, task, task);

        // try-with-resources를 사용하면 executor.shutdown()을 명시적으로 후출하지 않아도 됨.
        try(ExecutorService executor =
                    Executors.newThreadPerTaskExecutor(Thread.ofPlatform().factory()))
        {
            // 작업 결과를 저장할 List
            List<Future<String>> resultList = executor.invokeAll(taskList);
            System.out.println("=== After invokeAll() ===");
            for(Future<String> future : resultList) {
                String result = "";
                try {
                    result = future.get();
                } catch(Exception e) {
                    result = "예외발생";
                }
                System.out.printf("future.state()=%-7s, future.get()=%-8s%n",
                        future.state(), result);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
