public class VirtualEx5 {
    public static void main(String[] args) {
        final int THREAD_COUNT = 10_000;  // 스레드 개수
        Thread[] thArr = new Thread[THREAD_COUNT];

        System.out.println("=== 쓰레드 컨텍스트 스위칭 시간 ===");
        long ptTime = switchingTime(thArr, false);
        System.out.println("플랫폼 쓰레드 : " + ptTime + " ms");
        long vtTime = switchingTime(thArr, true);
        System.out.println("가상 스레드  : " + vtTime + " ms");
    }

    static long switchingTime(Thread[] htArr, boolean isVirtual){
        Runnable r = () -> {
            for(int i=0;i<1000;i++) {
                // Thread.sleep(1)에 의해 컨텍스트 스위칭 발생
                try { Thread.sleep(1); } catch (Exception e) { }
            }
        };

        long start = System.currentTimeMillis();

        for(int i=0;i<htArr.length;i++) {
            if (isVirtual)
                htArr[i] = Thread.ofVirtual().start(r);
            else
                htArr[i] = Thread.ofPlatform().start(r);
        }
        // 쓰레드 종료 대기 - 가상 쓰레드는 데몬 쓰레드라 이 코드가 없으면 바로 종료됨
        for (Thread th : htArr) {
            try {
                th.join();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}