
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolEx2 {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(()-> delaySayHello(5000));
        executorService.submit(()-> getUserInput());

        for (int i = 1; i <= 5; i++)
            executorService.submit(()-> sayHello());
        executorService.shutdown();
    }
    public static void sayHello() {
        System.out.println("Hello - " + Thread.currentThread().getName());
    }

    public static void delaySayHello(int ms) {
        try {
            Thread.sleep(ms);
        } catch(Exception e) {}
        sayHello();
    }

    public static void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input any number=");
        System.out.println(scanner.nextLine()+ " - "
                + Thread.currentThread().getName());
    }
}