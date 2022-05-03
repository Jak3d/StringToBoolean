public class wrapper {
    public static void main(String[] args) {
        sequenceBool test = new sequenceBool("((((4<5)|(5&3))&(2<=7))v(5=5))"); 
        System.out.println(test.scomponi("(((3>2)&(3le2))|(5ge5))"));
    }
}
