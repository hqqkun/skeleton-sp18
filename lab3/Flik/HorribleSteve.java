public class HorribleSteve {
    public static void main(String [] args) {
        int i = 0;
        int j;
        for ( i = 0; i < 500; ++i) {
            j = i;
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }
}
