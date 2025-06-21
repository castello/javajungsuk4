import java.util.Scanner;

public class FutureEx {
    public static void main(String[] args) {
        System.out.print("좋아하는 숫자를 입력하세요 >>");
        // 동기 메서드 호출(synchronous call)
        Integer input = getInput();
        System.out.println("=== after sync call ===");
        System.out.println("입력하신 내용="+input);
    }

    static Integer getInput() { // 동기 메서드
        Integer input = new Scanner(System.in).nextInt();
        return input;
    }
}