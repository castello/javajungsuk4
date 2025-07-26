import java.util.*;

class FlowEx12 {
    public static void main(String[] args) {
        int score  = 0;

        System.out.print("당신의 점수를 입력하세요.(1~100)>");

        Scanner scanner = new Scanner(System.in);
        String tmp = scanner.nextLine(); // 화면을 통해 입력받은 내용을 tmp에 저장
        score = Integer.parseInt(tmp);   // 입력받은 문자열(tmp)를 숫자로 변환

        char grade = switch(score/10) {
            case 9, 10 -> 'A';
            case 8  -> 'B';
            case 7  -> 'C';
            default -> 'F';
        };

        System.out.println("당신의 학점은 "+ grade +"입니다.");
    } // main의 끝
}
