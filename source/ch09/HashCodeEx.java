class HashCodeEx {
    public static void main(String[] args) {
        String str1 = new String("abc");
        String str2 = new String("abc");

        System.out.println(str1.equals(str2));
        System.out.println(str1.hashCode()); // 내용으로 해시 코드 생성
        System.out.println(str2.hashCode());
        System.out.println(System.identityHashCode(str1)); // 주소로 해시 코드 생성
        System.out.println(System.identityHashCode(str2));
    }
}