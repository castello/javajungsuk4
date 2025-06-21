public class StringEx4 {
    public static void main(String[] args) {
        String str = "   \u2000Hello, world\u2000    "; // 문자 U+2000은 공백
        System.out.println("str.trim()=[" + str.trim()+"]");
        System.out.println("str.strip()=[" + str.strip()+"]");
        System.out.println("str.stripLeading()=[" + str.stripLeading()+"]");
        System.out.println("str.stripTrailing()=["+str.stripTrailing()+"]");
        char ch = '\u2000';  // 유니코드의 문자 U+2000 ~ U+200B는 공백
        System.out.printf("U+%x is blank? %b%n", (int)ch,(ch+"").isBlank());
    }
}