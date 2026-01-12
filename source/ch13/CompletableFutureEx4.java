import java.util.concurrent.CompletableFuture;

// 메서드를 주석처리 해가면서 비교해 보라고 할 것
// [예제4] 서로 관련된 것들끼리 묶어서 예제로 만들고 비교해서 설명핳 것
// 어떤 것과 어떤 것을 비교해야 하나?
// allOf() vs anyOf()
// thenCombine() vs thenCompose()
// thenCompose() vs thenApply() - thenApply()는 Completable<Completable<String>>이 됨.
// runAfterBoth() vs thenAcceptBoth()

public class CompletableFutureEx4 {
    public static void main(String[] args) {
        // 1. ~Async() - 작업을 비동기로 처리 - 예시 생략
        // 2. then~() - 1개의 비동기에 대한 후처리
        thenRun();      // 작업 결과 받지 않고, 후속 작업 실행. 반환값 없음.
        thenAccept();   // 작업 결과 받아서 후속 작업 실행. 반환값 없음.
        thenApply();    // 작업 결과 받아서 후속 작업 실행. 반환값 있음.
        whenComplete(); // 작업이 성공하건 실패하건 후속 작업 실행

        // 3. thenCombine(), thenCompose() 2개의 배동기 작업을 직렬 또는 병렬로 처리
        thenCombine();  // 두 작업이 모두 끝나면 결과를 받아서 처리(병렬)
        thenCompose();  // 한 작업이 끝나면, 그 결과로 다음 작업을 시작(직렬)

        // 4. ~Both() - 두 개가 모두 끝난후 처리
        runAfterBoth();    // 두 작업의 결과를 사용하지 않는 후처리
        thenAcceptBoth();  // 두 작업의 결과를 사용하는 후처리

        // 5. ~Either() - 둘 중 하나가 먼저 끝나면 하는 후처리 작업
        runAfterEither();  // 먼저 끝난 작업의 결과 사용 안함
        acceptEither();    // 먼저 끝난 작업의 결과 사용해서 결과 반환 안함
        applyToEither();   // 먼저 끝난 작업의 결과 사용해서 다른 결과 반환

        // 6. ~Of() - 여러 개의 비동기 작업의 처리
        allOf(); // 모든 비동기 작업이 종료될 때 결과 반환
        anyOf(); // 가장 먼저 종료된 작업의 결과 반환
    }

    // 2.1 작업 결과 받지 않고, 후속 작업 실행. 반환값 없음.
    static void thenRun() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {});
        cf.thenRun(()-> System.out.println("thenRun()=작업이 완료되었습니다."));
    }

    // 2.2 작업 결과 받아서 후속 작업 실행. 반환값 없음
    static void thenAccept() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "Y");
        cf.thenAccept(s -> System.out.println("thenAccept()="+s));
    }

    // 2.3 작업 결과 받아서 후속 작업 실행. 반환값 있음.
    static void thenApply() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "Y");
        CompletableFuture<String> cf2 = cf.thenApply(s -> "예");
        System.out.println("thenApply()=" + cf2.join());
    }

    // 2.4 작업이 성공하건 실패하건 후속 작업 실행 - 예외처리 필요.
    static void whenComplete() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 2/1);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> 2/0);

        // 에외가 발생해서 작업을 실패했을 경우, 예외를 확인할 수 있을 뿐 예외를 처리하지 않음.
        cf1.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("whenComplte()=" + cf1.state() + " "+result);
            } else {
                System.out.println("whenComplete()="+ cf2.state() + " "+ex);
            }
        }).exceptionally(ex-> 0).join(); // 예외가 발생할 수 있으므로 예외처리

        cf2.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("whenComplte()=" + cf1.state() + " "+result);
            } else {
                System.out.println("whenComplete()="+ cf2.state() + " "+ex);
            }
        }).exceptionally(ex-> 0).join();
    }

    // 3.1 두 작업이 모두 끝나면 결과를 받아서 처리(병렬)
    static void thenCompose() {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> composed = cf1.thenCompose(str ->
                CompletableFuture.supplyAsync(() -> str + " World")
        );
        System.out.println("thenCompose()=" + composed.join()); // 출력: Hello World
    }

    // 3.2 한 작업이 끝나면, 그 결과로 다음 작업을 시작(직렬)
    static void thenCombine() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> 20);
        CompletableFuture<Integer> combined = cf1.thenCombine(cf2, (a, b) -> a + b);

        System.out.println("thenCombine()="+combined.join());  // 출력: 30
    }

    // 4.1 두 작업의 결과를 사용하지 않는 후처리
    static void runAfterBoth() {
        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
            System.out.println("작업1 시작");
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            System.out.println("작업1 끝");
        });

        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> {
            System.out.println("작업2 시작");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            System.out.println("작업2 끝");
        });

        CompletableFuture<Void> combined = cf1.runAfterBoth(cf2, () -> {
            System.out.println("runAfterBoth()="+cf1.state()+" "+cf2.state());
        });

        combined.join(); // 작업이 완료될 때까지 기다림
    }

    // 4.2 두 작업의 결과를 사용하는 후처리
    static void thenAcceptBoth(){
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> 20);

        CompletableFuture<Void> both = cf1.thenAcceptBoth(cf2, (a, b) -> {
            System.out.println("thenAcceptBoth()=" + (a + b) + " " + cf1.state() + " "+cf2.state());
        });
        both.join();
    }

    // 5.1 둘 중에 먼저 끝난 작업의 결과 사용 안함
    static void runAfterEither() {
        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
            System.out.println("작업1 시작");
            try { Thread.sleep(3000); } catch (InterruptedException e) {}
            System.out.println("작업1 끝");
        });

        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> {
            System.out.println("작업2 시작");
            try { Thread.sleep(1500); } catch (InterruptedException e) {}
            System.out.println("작업2 끝");
        });

        CompletableFuture<Void> combined = cf1.runAfterEither(cf2, () -> {
            System.out.println("runAfterEither()="+ cf1.state() + " "+cf2.state());
        });

        combined.join(); // 작업 종료까지 기다림
    }

    // 5.2 둘 중에 먼저 끝난 작업의 결과 사용해서 결과 반환 안함
    static void acceptEither() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            return 10;
        });

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            return 20;
        });

        CompletableFuture<Void> cf3 = cf1.acceptEither(cf2, r -> {
            System.out.println("acceptEither()="+ r + " " + cf1.state()+" "+cf2.state());
        });
        cf3.join();
    }

    // 5.3 둘 중에 먼저 끝난 작업의 결과 사용해서 다른 결과 반환
    static void applyToEither() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            return 10;
        });

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            return 20;
        });

        CompletableFuture<Integer> cf3 = cf1.applyToEither(cf2, r -> r*2);
        System.out.println("applyToEither()=" + cf3.join() + " " + cf1.state()+" "+cf2.state());
    }

    // 5.1 모든 비동기 작업이 종료될 때 결과 반환
    static void allOf() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> 20);
        CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> 30);
        CompletableFuture.allOf(cf1, cf2, cf3).join(); // 모든 작업이 끝날때까지 기다림.

        // 결과를 따로 꺼내야 함
        Integer r1 = cf1.join();
        Integer r2 = cf2.join();
        Integer r3 = cf3.join();
        System.out.println("allOf()="+(r1 + r2 + r3));
    }

    // 5.2 가장 먼저 종료된 작업의 결과 반환
    static void anyOf() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<String>  cf2 = CompletableFuture.supplyAsync(() -> "이십");
        CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> 30);
        Object result = CompletableFuture.anyOf(cf1, cf2, cf3).join();  // 먼저 끝난 결과 반환
        System.out.println("anyOf()=" + result);
    }
}
