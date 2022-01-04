package playground;

public class JavaPlayground {
    public static void main(String args[]){
        System.out.println("Hello, Java");
        //This is called class level functionality as we don't need to create instance of the class
        System.out.println(Person.N_Eyes);
    }

    class Person{
        public static final int N_Eyes = 2;
    }
}
