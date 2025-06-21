class BlockEx {
    static {
        System.out.println("static { }");
    }

    {
        System.out.println("{ }");
    }

    public BlockTest() {
        System.out.println("생성자");
    }

    public static void main(String args[]) {
        System.out.println("BlockTest bt = new BlockTest(); ");
        BlockTest bt = new BlockTest();

        System.out.println("BlockTest bt2 = new BlockTest(); ");
        BlockTest bt2 = new BlockTest();
    }
}
