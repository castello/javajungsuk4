class ThreadEx {
    public static void main(String args[]) {
        ThreadEx_1 t1 = new ThreadEx_1();

        Runnable r = new ThreadEx_2();
        Thread t2 = new Thread(r);	  // 생성자 Thread(Runnable target)

        t1.start();
        t2.start();
    }
}
class ThreadEx_1 extends Thread {
    public void run() {
        for(int i=0; i < 5; i++) {
            System.out.println(getName()); // 조상인 Thread의 getName()을 호출
        }
    }
}

class ThreadEx_2 implements Runnable {
    public void run() {
        for(int i=0; i < 5; i++) {
            // Thread.currentThread() - 현재 실행중인 Thread를 반환한다.
            System.out.println(Thread.currentThread().getName());
        }
    }
}