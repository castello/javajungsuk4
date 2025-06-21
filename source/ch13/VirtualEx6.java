import java.util.concurrent.locks.ReentrantLock;

public class VirtualEx6 {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception{
        Runnable r = ()->{
            syncMethod();
            lockMethod();
        };
        Thread.ofVirtual().start(r).join(); // main쓰레드의 종료 대기
    }

    private static synchronized void syncMethod() {
        System.out.println("[synchronized] - start");
        try {
            Thread.sleep(1_000); // 가상 쓰레드의 고정 발생!!!
        } catch (InterruptedException e) {}
        System.out.println("[synchronized] - end");
    }

    private static void lockMethod() {
        System.out.println("[lock] - start");
        lock.lock();
        try {
            Thread.sleep(1_000); // 가상 쓰레드의 고정 발생 안함
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
        System.out.println("[lock] - end");
    }
}