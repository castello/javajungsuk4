
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExecutorServiceEx2 {
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
                    System.out.println("정답 발견 by "+name+" idx="+idx);
                    return name;
                }
                if(arr[idx]==-1) { // 폭탄(-1)을 발견하면 예외 발생시키기
                    System.out.println("폭탄 발견 by "+name+" idx="+idx);
                    throw new Exception("failed - " + name);
                }
            }
        };

        // 5개의 작업(task)을 List에 저장
        List<Callable<String>> taskList =
                Arrays.asList(task, task, task, task, task);

        // 플랫폼 쓰레드 생성하는 ThreadFactory
        ThreadFactory factory = Thread.ofPlatform().factory();
//      ThreadFactory factory = Thread.ofVirtual().factory(); // 가상 쓰레드

        try (ExecutorService executor = Executors.
                newThreadPerTaskExecutor(factory)) {
            // 가장 먼저 성공적으로 완료된 task의 결과를 반환. 모두 실패하면 예외 발생. 
            String result = executor.invokeAny(taskList);
            System.out.println("=== After invokeAny() ===");
            System.out.println("result = " + result);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}