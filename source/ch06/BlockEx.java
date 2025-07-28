class BlockEx {
    static {
        System.out.println("static { }");
    }

    {
        System.out.println("{ }");
    }

    public BlockEx() {
        System.out.println("생성자");
    }

    public static void main(String args[]) {
        System.out.println("BlockEx be = new BlockEx(); ");
        BlockEx be = new BlockEx();

        System.out.println("BlockEx be2 = new BlockEx(); ");
        BlockEx be2 = new BlockEx();
    }
}
