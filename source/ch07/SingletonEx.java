final class Singleton {
    private static Singleton s = new Singleton();

    private Singleton() {
        //...
    }

    public static Singleton getInstance() {
        if(s==null)
            s = new Singleton();

        return s;
    }
}
class SingletonEx {
    public static void main(String args[]) {
//		Singleton s = new Singleton();
        Singleton s = Singleton.getInstance();
    }
}