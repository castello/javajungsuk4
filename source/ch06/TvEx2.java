class Tv2 {
    // Tv2의 속성(멤버 변수)
    String color;          // 색상
    boolean power;         // 전원상태(on/off)
    int channel;           // 채널

    // Tv2의 기능(메서드)
    void power()   { power = !power; }  // TV를 켜거나 끄는 기능을 하는 메서드
    void channelUp()   {  ++channel; }  // TV의 채널을 높이는 기능을 하는 메서드
    void channelDown() {  --channel; }  // TV의 채널을 낮추는 기능을 하는 메서드
}

class TvEx2 {
    public static void main(String args[]) {
        Tv2 t1 = new Tv2();  // Tv2 t1; t1 = new Tv2();를 한 문장으로 가능
        Tv2 t2 = new Tv2();
        System.out.println("t1의 channel값은 " + t1.channel + "입니다.");
        System.out.println("t2의 channel값은 " + t2.channel + "입니다.");

        t1.channel = 7;	// channel 값을 7으로 한다.
        System.out.println("t1의 channel값을 7로 변경하였습니다.");

        System.out.println("t1의 channel값은 " + t1.channel + "입니다.");
        System.out.println("t2의 channel값은 " + t2.channel + "입니다.");
    }
}
