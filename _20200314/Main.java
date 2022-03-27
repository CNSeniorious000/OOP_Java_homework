import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Main {
    public static void main(String[] args) {

    }
}

class Unnamed {
    public static <E extends Number> void copy(@NotNull List<E> listIn, List<E> listOut) {
        int i = 0;
        for (E number : listIn) {
            listOut.set(i, number);
            i++;
        }
    }

    public static <E extends Comparable<E>> void selectionSort(E @NotNull [] list) {
        var lth = list.length;
        for (int i = 0; i < lth; i++) {
            var minIndex = i;
            for (int j = i + 1; j < lth; j++) if (list[j].compareTo(list[minIndex]) < 0) minIndex = j;
            var tmp = list[i];
            list[i] = list[minIndex];
            list[minIndex] = tmp;
        }
    }
}