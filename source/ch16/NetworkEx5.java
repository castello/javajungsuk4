import java.net.*;
import java.io.*;

public class NetworkEx5 {
    public static void  main(String args[]) {
        URL url = null;
        InputStream in = null;
        FileOutputStream out = null;
        String address = "https://github.com/castello/javajungsuk4/blob/main/java_jungsuk4_src_20250601.zip";

        int ch = 0;

        try {
            url = new URL(address);
            in = url.openStream();
            out = new FileOutputStream("javajungsuk3_src.zip");

            while((ch=in.read()) !=-1) {
                out.write(ch);
            }
            in.close();
            out.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    } // main
}