class ExceptionEx12 {
    public static void main(String[] args) throws Exception {
        method1();	 // 예외를 전달받았으나 처리하지 않았으므로 JVM으로 예외를 전달
    }	// main메서드의 끝

    static void method1() throws Exception {
        method2(); // 예외를 전달받았으나 처리하지 않았으므로 method1()전달
    }	// method1의 끝

    static void method2() throws Exception {
        throw new Exception(); // 예외 발생!!! 예외를 처리하지 않았으므로 method2()전달
    }	// method2의 끝
}