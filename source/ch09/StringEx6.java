import java.util.StringJoiner;

class StringEx6 {
    public static void main(String[] args) throws Exception {
        String str = "가";

        byte[] bArr  = str.getBytes("UTF-8"); // "UTF-8"은 생략 가능(기본 인코딩)
        byte[] bArr2 = str.getBytes("CP949");

        System.out.println("UTF-8:" + joinByteArr(bArr));
        System.out.println("CP949:" + joinByteArr(bArr2));

        System.out.println("UTF-8:" + new String(bArr));
        System.out.println("CP949:" + new String(bArr2, "CP949"));
    }

    static String joinByteArr(byte[] bArr) {
        StringJoiner sj = new StringJoiner(":", "[", "]");

        for(byte b : bArr)
            sj.add(String.format("%02X",b));
        return sj.toString();
    }
}