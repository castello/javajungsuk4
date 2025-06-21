public class VirtualEx2 {
    public static void main(String[] args) {
        final int THREAD_COUNT = 10_000;  // 스레드 개수
        Thread[] arr = new Thread[THREAD_COUNT];

        System.out.println("1.쓰레드 생성시간");
        long ptTime = newThread(arr, false, false);
        System.out.println("플랫폼 쓰레드 : " + ptTime + " ms");
        long vtTime = newThread(arr, true, false);
        System.out.println("가상 스레드  : " + vtTime + " ms");
        System.out.println();
        System.out.println("2.쓰레드 생성 & 시작시간");
        ptTime = newThread(arr, false, true);
        System.out.println("플랫폼 스레드 : " + ptTime + " ms");
        vtTime = newThread(arr, true, true);
        System.out.println("가상 스레드  : " + vtTime + " ms");
    }

    static long newThread(Thread[] arr, boolean isVirtual, boolean isStarted){
        long start = System.currentTimeMillis();
        Runnable r = () -> {};

        for(int i=0;i<arr.length;i++)
            if(isVirtual) {
                if(isStarted)
                    arr[i] = Thread.ofVirtual().start(r);
                else
                    arr[i] = Thread.ofVirtual().unstarted(r);
            } else {
                if(isStarted)
                    arr[i] = Thread.ofPlatform().start(r);
                else
                    arr[i] = Thread.ofPlatform().unstarted(r);
            }
        long end = System.currentTimeMillis();
        return end - start;
    }
}