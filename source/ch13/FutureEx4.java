import javax.swing.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureEx4 {
    public static void main(String[] args) {
        Runnable task = ()-> {
            int downloaded = 0;
            Random r = new Random();

            while(!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(200);
                } catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
                downloaded += r.nextInt(10);
                int percent = (int) ((downloaded / (float) 100) * 100);
                percent = Math.min(percent, 100);
                final int WIDTH = 50; // 진행 바 너비

                int filled = (percent * WIDTH) / 100;
                String bar = "|".repeat(filled) + " ".repeat(WIDTH - filled);
                System.out.printf("\r[%s] %3d%%", bar, percent);

                if(percent==100)
                    break;
            }
            System.out.println();

            String status = Thread.currentThread().isInterrupted() ? "중단" : "완료";
            System.out.println("다운로드가 "+status+"되었습니다.");
        };

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<?> future = es.submit(task);

        Object[] options = {"OK", "Cancel"}; // 버튼 하나만 정의
        int result = JOptionPane.showOptionDialog( null,
                "다운로드를 시작합니다.\n취소하려면 "+ options[1] +"버튼을 누르세요.", "Donwloading",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]  // 기본 선택 버튼
        );

        boolean cancelled = false;
        if (result == 1) { // Cancel버튼을 누르면
//            cancelled = future.cancel(true);
            cancelled = future.cancel(false);
        }
        System.out.println("cancelled = " + cancelled);
        System.out.println("future.isCancelled() = " + future.isCancelled());
        System.out.println("future.isDone() = " + future.isDone());
        System.out.println("future.state() = " + future.state());
        es.shutdown();
    }
}
/*
[||||||||||||||||||||||||||||||||||||||||||||||||||] 100%
다운로드가 완료되었습니다.
cancelled = false
future.isCancelled() = false
future.isDone() = true
future.state() = SUCCESS
*/