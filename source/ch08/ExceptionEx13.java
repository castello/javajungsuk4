class ExceptionEx13 {
    public static void main(String[] args) {
        method1(); 	  // method1()에서 예외를 처리했으므로 예외가 발생했는지도 모름
    }	// main메서드의 끝

    static void method1() {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("method1메서드에서 예외가 처리되었습니다.");
            e.printStackTrace();
        }
    }	// method1의 끝
}
