import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CustomClassOperations {


    private CustomClassOperations(){
    }

    public static <T extends CustomObject & Comparable<T>> void binarySearch (ArrayList<T> array, T object){
        ArrayList<T> copyArray = new ArrayList<>(array);
        ShellSort.getInstance().sort(copyArray, true, null);
        boolean flag = false;

        int lower = 0;
        int high = copyArray.size() - 1;
        while (lower <= high) {
            int middle = (lower + high) / 2;

            if (copyArray.get(middle).compareTo(object) > 0) {
                high = middle - 1;
            } else if (copyArray.get(middle).compareTo(object) < 0) {

                lower = middle + 1;
            } else {
                flag = true;
                break;
            }
        }
        System.out.println("Элемент " + (!flag ? "не " : "") + "был найден в коллекции!");
    }

    public static <T extends CustomObject & Serializable> void serializeArray (ArrayList<T> array, String path){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T extends CustomObject & Serializable> ArrayList<T> deserializeArray (String path){
        ArrayList<T> deserializedArray = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            deserializedArray = (ArrayList<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedArray;
    }
}

