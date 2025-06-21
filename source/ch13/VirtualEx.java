public class VirtualEx {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = ()-> {
            try { Thread.sleep(1000);} catch(Exception e) {}
            System.out.println("Hello");
        };

        Thread vt = Thread.ofVirtual().unstarted(r); // 가상 쓰레드를 생성만 한다.
        vt.setPriority(10); // vt의 우선 순위를 10으로 변경
//    vt.setDaemon(false); // 실행시 에러. 'false' not legal for virtual threads
        vt.start(); // 가상 쓰레드를 시작한다.

        System.out.println("vt.isVirtual() = " + vt.isVirtual());
        System.out.println("vt.isDaemon() = " + vt.isDaemon());
        System.out.println("vt.getPriority() = " + vt.getPriority());
        System.out.println("vt.getName() = " + vt.getName());
        System.out.println("vt.threadId() = " + vt.threadId());
        System.out.println("vt.getThreadGroup() = " + vt.getThreadGroup());
        vt.join(); // vt가 종료될 때까지 main쓰레드를 기다리게 한다.
    }
}