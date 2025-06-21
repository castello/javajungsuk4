import java.io.*;

class StringReaderWriterEx {
    public static void main(String[] args) throws IOException {
        String inputData = "ABCD";
        StringReader input = new StringReader(inputData);
        StringWriter output = new StringWriter();

//		int data = 0;
//		while((data = input.read())!=-1) {
//			output.write(data);	// void write(int b)
//		}
        input.transferTo(output);

        System.out.println("Input Data  :" + inputData);
        System.out.println("Output Data :" + output.toString());
//		System.out.println("Output Data :" + output.getBuffer().toString());
    }
}