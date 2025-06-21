import java.util.*;

class HashSetEx4 {
    public static void main(String[] args) {
        HashSet set = new HashSet();

        set.add(new String("abc"));
        set.add(new String("abc"));
        set.add(new Person2("David",10));
        set.add(new Person2("David",10));

        System.out.println(set);
    }
}

class Person2 {
    String name;
    int age;

    Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean equals(Object obj) {
        if(obj instanceof Person2 p) {
            return name.equals(p.name) && age == p.age;
        }

        return false;
    }

    public int hashCode() {
        return (name+age).hashCode();
    }

    public String toString() {
        return name +":"+ age;
    }
}