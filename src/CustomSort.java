import java.util.ArrayList;
import java.util.Comparator;

public class CustomSort implements Sorter{
    private static final CustomSort instance = new CustomSort();
    private static final ShellSort shellSort = ShellSort.getInstance();

    private CustomSort(){
    }

    public static CustomSort getInstance(){
        return instance;
    }


    @Override
    public <T extends CustomObject & Comparable<T>> void sort(ArrayList<T> array, boolean isNotReverseSort) {
        this.sort(array, isNotReverseSort, null);
        }

    @Override
    public <T extends CustomObject & Comparable<T>> void sort(ArrayList<T> array, boolean isNotReverseSort, Comparator<T> comparator) {
        ArrayList<T> arrayWithEvenValues = new ArrayList<>(array.stream().filter(x -> x.getIntValueForCustomSort() % 2 == 0).toList());

        if (comparator == null) {
            comparator = Comparator.comparing(T::getIntValueForCustomSort);
        }
        shellSort.sort(arrayWithEvenValues, true, comparator);

        int j = 0;
        for(int i = 0; i < array.size(); i++){
            if (array.get(i).getIntValueForCustomSort() % 2 == 0){
                array.set(i, arrayWithEvenValues.get(j));
                j++;
            }
        }
    }
}
