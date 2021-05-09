public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> queue = new ArrayDeque<>();
        int length = word.length();
        for (int i = 0; i != length; ++i) {
            queue.addLast(word.charAt(i));
        }
        return queue;
    }

    public boolean isPalindrome(String word) {
        int length = word.length();
        if (length == 0 || length == 1) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        return helper(deque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int length = word.length();
        if (length == 0 || length == 1) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        return helper(deque, cc);
    }

    private boolean helper(Deque<Character> deque) {
        if (deque.size() == 0  || deque.size() == 1) {
            return true;
        }
        char first = deque.removeFirst();
        char last = deque.removeLast();
        if (first != last) {
            return false;
        }
        return helper(deque);
    }

    private boolean helper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() == 0  || deque.size() == 1) {
            return true;
        }
        char first = deque.removeFirst();
        char last = deque.removeLast();
        if (!cc.equalChars(first, last)) {
            return false;
        }
        return helper(deque, cc);
    }
}
