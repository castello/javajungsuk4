class Product4 {
    int price;			// 제품의 가격
    int bonusPoint;		// 제품구매 시 제공하는 보너스점수

    Product4(int price) {
        this.price = price;
        bonusPoint =(int)(price/10.0);
    }

    Product4() {} // 기본 생성자
}

class Tv4 extends Product4 {
    Tv4() {  super(100);	 }

    public String toString() { return "Tv"; }
}

class Computer4 extends Product4 {
    Computer4() { super(200); }
    public String toString() { return "Computer"; }
}

class Audio4 extends Product4 {
    Audio4() { super(50); }
    public String toString() { return "Audio"; }
}

class Buyer4 {			  // 고객, 물건을 사는 사람
    int money = 1000;	  // 소유금액
    int bonusPoint = 0;	  // 보너스 점수
    Product4[] item = new Product4[10];	// 구입한 제품을 저장하기 위한 배열
    int i =0;			  // Product배열에 사용될 카운터

    void buy(Product4 p) {
        if(money < p.price) {
            System.out.println("잔액이 부족하여 물건을 살 수 없습니다.");
            return;
        }

        money -= p.price;	// 가진 돈에서 구입한 제품의 가격을 뺀다.
        bonusPoint += p.bonusPoint;	// 제품의 보너스 점수를 추가한다.
        item[i++] = p;		// 제품을 Product[] item에 저장한다.
        System.out.println(p + "을/를 구입하셨습니다.");
    }

    void summary() {	             	// 구매한 물품에 대한 정보를 요약해서 보여 준다.
        int sum = 0;	             	// 구입한 물품의 가격합계
        String itemList =""; 	// 구입한 물품목록

        // 반복문을 이용해서 구입한 물품의 총 가격과 목록을 만든다.
        for(int i=0; i<item.length;i++) {
            if(item[i]==null) break;
            sum += item[i].price;
            itemList += item[i] + ", ";
        }
        System.out.println("구입하신 물품의 총 금액은 " + sum + "만원입니다.");
        System.out.println("구입하신 제품은 " + itemList + "입니다.");
    }
}
class PolyParamEx2 {
    public static void main(String args[]) {
        Buyer4 b = new Buyer4();

        b.buy(new Tv4());
        b.buy(new Computer4());
        b.buy(new Audio4());
        b.summary();
    }
}