public class VirtualEx3 {
    public static void main(String[] args) {
        int count = 0;

        Runnable r =() -> {
            try {
                Thread.sleep(Integer.MAX_VALUE); // 종료되지 않고 오래 잠자게 한다.
            } catch (InterruptedException e) {}
        };

        try {
            while (true) {
                Thread.ofPlatform().start(r); // 플랫폼 쓰레드를 생성하고 시작
                if(++count%1000==0)
                    System.out.println("count = " + count);
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        System.out.println("max count = " + count);
    }
}