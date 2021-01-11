public class TestJNIPrimitive {
    static {
        System.loadLibrary("primitive");
    }

    // Declare a native method average() that receives two ints and return a double containing the average
    private native double average(int n1, int n2);

    private native String saySomething(String str);

    // Test Driver
    public static void main(String args[]) {
        System.out.println("In Java, the average is " + new TestJNIPrimitive().average(3, 2));
        System.out.println("In Java, abc returns: " + new TestJNIPrimitive().saySomething("abc"));
        System.out.println("In Java, null returns: " + new TestJNIPrimitive().saySomething(null));
    }
}