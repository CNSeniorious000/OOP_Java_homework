import java.io.*;
import java.util.*;

public class lab08 {
    public static void main(String[] args) throws IOException {
        testSetOperations();
        testWords(args[0]);
    }

    static void testWords(String filename) throws IOException {
        var file = new BufferedReader(new FileReader(filename));
        var words = new LinkedList<String>();
        var word = file.readLine();
        if (word != null) {
            words.add(word);
            word = file.readLine();
        }
        words.sort(Comparator.naturalOrder());
        for (var w : words) System.out.println(w);
    }

    static void testSetOperations() {
        var set_1 = new HashSet<String>();
        set_1.add("George");
        set_1.add("Jim");
        set_1.add("John");
        set_1.add("Blake");
        set_1.add("Kevin");
        set_1.add("Michael");
        var set_2 = new HashSet<String>();
        set_2.add("George");
        set_2.add("Katie");
        set_2.add("Kevin");
        set_2.add("Michelle");
        set_2.add("Ryan");
        {
            // 并集
            var tmp = new HashSet<String>(set_1);
            tmp.addAll(set_2);
            System.out.println(tmp);
        }
        {
            // 交集
            var tmp = new HashSet<String>(set_1);
            tmp.retainAll(set_2);
            System.out.println(tmp);
        }
        {
            // 并集
            var tmp = new HashSet<String>(set_1);
            tmp.removeAll(set_2);
            System.out.println(tmp);
        }
    }
}

