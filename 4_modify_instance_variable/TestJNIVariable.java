public class TestJNIVariable {
    static {
        System.loadLibrary("variable");
    }

    private int number = 66;
    private String message = "java message";

    private native void testJNIVariable();

    // Test variable
    public static void main(String args[]) {
        TestJNIVariable variable = new TestJNIVariable();
        variable.testJNIVariable();
        System.out.println("In Java, the number is " + variable.number);
        System.out.println("In Java, the message is " + variable.message);
    }
}