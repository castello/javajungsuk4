class OperatorEx28 {
    public static void main(String[] args) {
        int x = 0xAB, y = 0xF;

        System.out.printf("x = %#X \t\t%s%n", x, toBinaryString(x));
        System.out.printf("y = %#X \t\t%s%n", y, toBinaryString(y));
        System.out.printf("%#X | %#X = %#X \t%s%n" , x, y, x | y, toBinaryString(x | y));
        System.out.printf("%#X & %#X = %#X \t%s%n" , x, y, x & y, toBinaryString(x & y));
        System.out.printf("%#X ^ %#X = %#X \t%s%n" , x, y, x ^ y, toBinaryString(x ^ y));
        System.out.printf("%#X ^ %#X ^ %#X = %#X %s%n" , x, y, y, x ^ y ^ y, toBinaryString(x ^ y ^ y));
    } // main의 끝

    static String toBinaryString(int x) {  // 10진 정수를 2진수로 변환하는 메서드
        String zero = "00000000000000000000000000000000";
        String tmp = zero + Integer.toBinaryString(x);
        return tmp.substring(tmp.length()-32);
    }
}
