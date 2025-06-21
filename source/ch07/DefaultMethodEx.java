class DefaultMethodEx {
    public static void main(String[] args) {
        Child_1 c = new Child_1();
        c.method1();
        c.method2();
        MyInterface.staticMethod();
        MyInterface2.staticMethod();
    }
}

class Child_1 extends Parent_1 implements MyInterface, MyInterface2 {
    public void method1() {
        System.out.println("method1() in Child"); // 오버라이딩
    }
}

class Parent_1 {
//    public void method2() {
//        System.out.println("method2() in Parent");
//    }
}

interface MyInterface {
    default void method1() {
        System.out.println("method1() in MyInterface");
    }

    default void method2() {
        System.out.println("method2() in MyInterface");
        privateMetod();
    }
    static void staticMethod() {
        System.out.println("staticMethod() in MyInterface");
        privateStaticMetod();
    }

    private void privateMetod(){
        System.out.println("privateMethod() in MyInterface");
    }

    private static void privateStaticMetod(){
        System.out.println("privateStaticMethod() in MyInterface");
    }
}

interface MyInterface2 {
    default void method1() {
        System.out.println("method1() in MyInterface2");
    }

    static void staticMethod() {
        System.out.println("staticMethod() in MyInterface2");
    }
}