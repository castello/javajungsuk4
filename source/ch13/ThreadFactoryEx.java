import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryEx {
    public static void main(String[] args) {
        // 1. 쓰레드가 수행할 작업
        Runnable r = ()-> {
            System.out.println("Hello");
        };
        // 2. 쓰레드를 생성하는 방법
        ThreadFactory factory = new PlatformThreadFactory();

        // 3. 쓰레드를 실행하는 방법
        Executor executor = new DaemonExecutor(factory);
        executor.execute(r);
    }
}

// 플랫폼 쓰레드를 생성해서 반환하는 ThreadFactory
class PlatformThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        Thread th = new Thread(r);
        th.setName("platform-"+th.threadId());
        return th;
    }
}

// 가상 쓰레드를 생성해서 반환하는 ThreadFactory
class VirtualThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        return Thread.ofVirtual().unstarted(r);
    }
}

class DaemonExecutor implements Executor {
    private ThreadFactory factory;

    DaemonExecutor(ThreadFactory factory) {
        this.factory = factory;
    }

    public void execute(Runnable r) {
        Thread th = factory.newThread(r);
        th.setDaemon(true);
        th.start();
    }
}