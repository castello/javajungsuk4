package pkgA; // 패키지 필수. 패키지가 지정된 클래스(인터페이스)만 모듈에 포함 가능

import javax.swing.JOptionPane; // 모듈 java.desktop에 속한 클래스

public class ModuleEx {
    public static void main(String[] args) {
        String question = "What is your favorite fruit?";
        String str = JOptionPane.showInputDialog(question);
        System.out.println("str = " + str);
    }
}
