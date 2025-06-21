import java.io.*;

class StandardIOEx {
    public static void main(String[] args) throws IOException {
        int input = 0;

        while((input=System.in.read())!=-1) {
            System.out.println("input :" + input + ", (char)input :"
                    + (char)input);
        }
    } // main
}