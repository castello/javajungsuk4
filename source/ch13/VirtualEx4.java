public class VirtualEx4 {
    public static void main(String[] args) {
        int count = 0;

        Runnable r =() -> {
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {}
        };

        try {
            while (true) {
                Thread.ofVirtual().start(r); // 가상 쓰레드를 생성하고 시작
                if(++count%1000==0)
                    System.out.println("count = " + count);
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        System.out.println("max count = " + count);
    }
}