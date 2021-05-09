import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    private int N = 100;

    @Test
    public void testArray() {
        int number;
        Integer exp;
        Integer act;
        String message = "";

        StudentArrayDeque<Integer> studentArrayDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> arrayDequeSolution = new ArrayDequeSolution<>();

        for (int i = 0; i != N; ++i) {
            number = StdRandom.uniform(4);
            switch (number) {
                default:
                    break;
                case 0:
                    arrayDequeSolution.addFirst(i);
                    studentArrayDeque.addFirst(i);
                    message += "addFirst(" + i + ")\n";
                    break;
                case 1:
                    arrayDequeSolution.addLast(i);
                    studentArrayDeque.addLast(i);
                    message += "addLast(" + i + ")\n";
                    break;
                case 2:
                    if (!arrayDequeSolution.isEmpty() && !studentArrayDeque.isEmpty()) {
                        message += "removeFirst()\n";
                        exp = arrayDequeSolution.removeFirst();
                        act = studentArrayDeque.removeFirst();
                        assertEquals(message, exp, act);
                    }
                    break;
                case 3:
                    if (!arrayDequeSolution.isEmpty() && !studentArrayDeque.isEmpty()) {
                        message += "removeLast()\n";
                        exp = arrayDequeSolution.removeLast();
                        act = studentArrayDeque.removeLast();
                        assertEquals(message, exp, act);
                    }
                    break;
            }
        }
    }
}

