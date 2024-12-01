import java.util.ArrayList;
import java.util.Comparator;

public interface Sorter {
    <T extends CustomObject & Comparable<T>> void sort(ArrayList<T> array, boolean isNotReverseSort, Comparator<T> comparator);
    <T extends CustomObject & Comparable<T>> void sort(ArrayList<T> array, boolean isNotReverseSort);
}
