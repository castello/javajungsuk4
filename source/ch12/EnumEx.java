
enum Direction { EAST, SOUTH, WEST, NORTH }

class EnumEx {
    public static void main(String[] args) {
        Direction d1 = Direction.EAST;
        Direction d2 = Direction.valueOf("WEST");
        Direction d3 = Enum.valueOf(Direction.class, "EAST");

        System.out.println("d1="+d1);
        System.out.println("d2="+d2);
        System.out.println("d3="+d3);
        System.out.println("d1==d2 ? "+ (d1==d2));
        System.out.println("d1==d3 ? "+ (d1==d3));
        System.out.println("d1.equals(d3) ? "+ d1.equals(d3));
//		System.out.println("d2 > d3 ? "+ (d1 > d3)); // 에러
        System.out.println("d1.compareTo(d3) ? "+ (d1.compareTo(d3)));
        System.out.println("d1.compareTo(d2) ? "+ (d1.compareTo(d2)));

        switch(d1) {
            case EAST  -> System.out.println("The direction is EAST.");
            case SOUTH -> System.out.println("The direction is SOUTH.");
            case WEST  -> System.out.println("The direction is WEST.");
            case NORTH -> System.out.println("The direction is NORTH.");
        }

        Direction[] dArr = Direction.values();

        for(Direction d : dArr)  // for(Direction d : Direction.values())
            System.out.printf("%s=%d%n", d.name(), d.ordinal());
    }
}