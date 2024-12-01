import java.util.ArrayList;
import java.util.Comparator;

public class ShellSort implements Sorter {
    private static ShellSort instance = new ShellSort();

    private ShellSort(){
    }

    public static ShellSort getInstance(){
        return instance;
    }

    @Override
    public <T extends CustomObject & Comparable<T>> void sort(ArrayList<T> array, boolean isNotReverseSort) {
        this.sort(array, isNotReverseSort, null);
    }
    @Override
    public <T extends CustomObject & Comparable<T>> void sort(ArrayList<T> array, boolean isNotReverseSort, Comparator<T> comparator) {
        int compareCoefficient = isNotReverseSort ? 1 : -1;

        if (comparator == null) {
            comparator = T::compareTo;
        }

        int i, j, step;
        T temporary;
        for(step = array.size() / 2; step > 0; step /= 2) {
            for(i = step; i < array.size(); i++) {
                temporary = array.get(i);
                for(j = i; j >= step; j -= step) {
                    if(compareCoefficient * comparator.compare(array.get(j - step), temporary) > 0) {
                        array.set(j, array.get(j - step));
                    } else {
                        break;
                    }
                }
                array.set(j, temporary);
            }
        }
    }
}