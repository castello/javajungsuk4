import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class FileEx5 {
    public static void main(String[] args) {
        if(args.length != 1 || args[0].length() != 1
                || "tTlLnN".indexOf(args[0]) == -1) {
            System.out.println("USAGE : java FileEx5 SORT_OPTION   ");
            System.out.println("   SORT_OPTION :                   ");
            System.out.println("   t     Time ascending sort.      ");
            System.out.println("   T     Time descending sort.     ");
            System.out.println("   l     Length ascending sort.    ");
            System.out.println("   L     Length descending sort.   ");
            System.out.println("   n     Name ascending sort.      ");
            System.out.println("   N     Name descending sort.     ");
            System.exit(0);
        }

        final char option = args[0].charAt(0);

        String currDir = System.getProperty("user.dir");
        File   dir  = new File(currDir);
        File[] files = dir.listFiles();

        Comparator<File> comp = null;
        switch(option){
            case 't':
                comp = (f, f2) -> Long.compare(f.lastModified(),f2.lastModified());
                break;
            case 'T':
                comp = (f, f2) -> Long.compare(f2.lastModified(),f.lastModified());
                break;
            case 'l':
                comp = (f, f2) -> Long.compare(f.length(), f2.length());
                break;
            case 'L':
                comp = (f, f2) -> Long.compare(f2.length(), f.length());
                break;
            case 'n':
                comp = (f, f2) -> f.getName().compareTo(f2.getName());
                break;
            case 'N':
                comp = (f, f2) -> f2.getName().compareTo(f.getName());
                break;
        }

        Arrays.sort(files, comp);

        for(int i=0; i < files.length; i++) {
            File f = files[i];
            String name = f.getName();
            SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String attribute = "";
            String size = "";
            if(files[i].isDirectory()) {
                attribute = "DIR";
            } else {
                size = f.length() + "";
                attribute  = f.canRead()  ? "R" : " ";
                attribute += f.canWrite() ? "W" : " ";
                attribute += f.isHidden() ? "H" : " ";
            }

            System.out.printf("%s %3s %6s %s%n"
                    ,df.format(new Date(f.lastModified())),attribute,size,name );
        } // for
    } // main
} // end of class