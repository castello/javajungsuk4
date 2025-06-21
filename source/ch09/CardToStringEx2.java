class Card2 {
    String kind;
    int number;

    Card2() {
        this("SPADE", 1);  // Card2(String kind, int number)를 호출
    }

    Card2(String kind, int number) {
        this.kind = kind;
        this.number = number;
    }

    public String toString() {
        return "kind : " + kind + ", number : " + number;
    }
}
class CardToStringEx2 {
    public static void main(String[] args) {
        Card2 c1 = new Card2();
        Card2 c2 = new Card2("HEART", 10);
        System.out.println(c1.toString());
        System.out.println(c2);
    }
}