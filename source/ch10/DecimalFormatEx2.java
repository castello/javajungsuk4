import java.text.*;

class DecimalFormatEx2 {
    public static void main(String[] args) {
        DecimalFormat df  = new DecimalFormat("#,###.##");
        DecimalFormat df2 = new DecimalFormat("#.###E0");

        System.out.println(Integer.parseInt("123,456"));
        try {
            Number num = df.parse("1,234,567.89");
            System.out.print("1,234,567.89" + " -> ");

            double d = num.doubleValue();
            System.out.print(d + " -> ");

            System.out.println(df2.format(num));
        } catch (Exception e) {}
    } // main
}
