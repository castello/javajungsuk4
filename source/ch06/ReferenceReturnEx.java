class Data3 { int x; }

class ReferenceReturnEx {
    public static void main(String[] args) {
        Data3 d = new Data3();
        d.x = 10;

        Data3 d2 = copy(d);
        System.out.println("d.x ="+d.x);
        System.out.println("d2.x="+d2.x);
    }

    static Data3 copy(Data3 d) {  // 참조형 반환타입. 객체의 주소(정수)를 반환
        Data3 tmp = new Data3();
        tmp.x = d.x;

        return tmp;
    }
}