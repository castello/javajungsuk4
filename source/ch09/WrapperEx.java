class WrapperEx {
    public static void main(String[] args) {
        //	Integer i  = new Integer(100); 	// Java 9부터 사용중단 권고(deprecated)
        Integer i  = Integer.valueOf(100);
        Integer i2 = Integer.valueOf("100");

        System.out.println("i==i2 ? "+(i==i2));
        System.out.println("i.equals(i2) ? "+i.equals(i2));
        System.out.println("i.compareTo(i2)="+i.compareTo(i2));
        System.out.println("i.toString()="+i.toString());

        System.out.println("MAX_VALUE="+Integer.MAX_VALUE);
        System.out.println("MIN_VALUE="+Integer.MIN_VALUE);
        System.out.println("SIZE="+Integer.SIZE+" bits");
        System.out.println("BYTES="+Integer.BYTES+" bytes");
        System.out.println("TYPE="+Integer.TYPE);
    }
}