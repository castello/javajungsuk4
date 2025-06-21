import java.io.File;
import java.util.Properties;

public class Test   {
    public static void main(String[] args) throws Exception {
        Properties props = System.getProperties();
//        p.list(System.out);

        System.out.println(System.getProperty("java.io.tmpdir"));
        File tempFile = File.createTempFile("~mergetemp",".tmp");
        System.out.println("tempFile = " + tempFile);

        // 전체 출력 (방법 1) - keySet으로 반복
//        for (String key : props.stringPropertyNames()) {
//            String value = props.getProperty(key);
//            System.out.println(key + " = " + value);
//        }

    }
}
