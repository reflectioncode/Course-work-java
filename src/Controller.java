import java.util.ArrayList;

public class Controller {
    private static CustomClassType customClassType = CustomClassType.AUTOMOBILE;
    private static CustomObjectEngineer customObjectEngineer = new CustomObjectEngineer(new AutoBuilder());
    private static int numberOfObjects = 5;
    private static ArrayList<RootVegetable> rootVegetables = new ArrayList<>();
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Automobile> automobils = new ArrayList<>();
    private static String filePath = "C:\\Users\\icefo\\SaveObjects.txt";
    private static Sorter sorter;

    private Controller(){
    }

    public static void readObjectsFromConsole(){
        Controller.clearCustomClassLists();
        for(var object : customObjectEngineer.buildFromConsole(Controller.getNumberOfObjects())){
            Controller.addCustomClassObject((CustomObject) object);
        }
    }

    public static void readObjectsFromFile(){
        for(var object : customObjectEngineer.buildFromFile(Controller.filePath)){
            Controller.addCustomClassObject((CustomObject) object);
        }
    }

    public static void readObjectsFromRandom(){
        Controller.clearCustomClassLists();
        for(var object : customObjectEngineer.buildFromRandom(Controller.getNumberOfObjects())){
            Controller.addCustomClassObject((CustomObject) object);
        }
    }

    public static void search(){
        CustomObject searchObject = (CustomObject) (Controller.customObjectEngineer.buildFromConsole(1).getFirst());

        switch (customClassType){
            case CustomClassType.AUTOMOBILE -> {
                CustomClassOperations.binarySearch(automobils, (Automobile) searchObject);
            }
            case CustomClassType.BOOK -> {
                CustomClassOperations.binarySearch(books, (Book) searchObject);
            }
            case CustomClassType.ROOT_VEGETABLE -> {
                CustomClassOperations.binarySearch(rootVegetables, (RootVegetable) searchObject);
            }
        }
    }

    public static void clearCustomClassLists(){
            Controller.automobils.clear();
            Controller.books.clear();
            Controller.rootVegetables.clear();
    }

    private static void addCustomClassObject(CustomObject object){
        switch (customClassType){
            case CustomClassType.AUTOMOBILE -> {
                Controller.automobils.add((Automobile) object);
            }
            case CustomClassType.BOOK -> {
                Controller.books.add((Book) object);
            }
            case CustomClassType.ROOT_VEGETABLE -> {
                Controller.rootVegetables.add((RootVegetable) object);
            }
        }
    }

    public static void saveDataToFile(){
        switch (customClassType){
            case CustomClassType.AUTOMOBILE -> {
                CustomClassOperations.serializeArray(Controller.automobils, Controller.filePath);
            }
            case CustomClassType.BOOK -> {
                CustomClassOperations.serializeArray(Controller.books, Controller.filePath);
            }
            case CustomClassType.ROOT_VEGETABLE -> {
                CustomClassOperations.serializeArray(Controller.rootVegetables, Controller.filePath);
            }
        }
    }

    public static boolean sort(int mode){
        switch (mode){
            case 1:
                Controller.sorter = ShellSort.getInstance();
                if (Controller.customClassType == CustomClassType.BOOK) {
                    sorter.sort(books, true);
                } else if(Controller.customClassType == CustomClassType.AUTOMOBILE){
                    sorter.sort(automobils, true);
                } else{
                    sorter.sort(rootVegetables, true);
                }
                break;
            case 2:
                Controller.sorter = ShellSort.getInstance();
                if (Controller.customClassType == CustomClassType.BOOK) {
                    sorter.sort(books, false);
                } else if(Controller.customClassType == CustomClassType.AUTOMOBILE){
                    sorter.sort(automobils, false);
                } else{
                    sorter.sort(rootVegetables, false);
                }
                break;
            case 3:
                Controller.sorter = CustomSort.getInstance();
                if (Controller.customClassType == CustomClassType.BOOK) {
                    sorter.sort(books, false);
                } else if(Controller.customClassType == CustomClassType.AUTOMOBILE){
                    sorter.sort(automobils, false);
                } else{
                    sorter.sort(rootVegetables, false);
                }
                break;
            default:
                System.out.println("В метод передано некорректное значение!");
                return false;
        }
        return true;
    }

    public static void printData(){
        if (Controller.customClassType == CustomClassType.BOOK) {
            for (var book : books){
                System.out.println(book);
            }
        } else if(Controller.customClassType == CustomClassType.AUTOMOBILE){
            for (var auto : automobils){
                System.out.println(auto);
            }
        } else{
            for (var rootVegetable : rootVegetables){
                System.out.println(rootVegetable);
            }
        }
        System.out.println("\n\n\n");
    }

    public static ArrayList<Automobile> getAutomobils(){
        return automobils;
    }

    public static ArrayList<Book> getBooks(){
        return books;
    }

    public static ArrayList<RootVegetable> getRootVegetables(){
        return rootVegetables;
    }

    public static int getNumberOfObjects(){
        return Controller.numberOfObjects;
    }

    public static void setNumberOfObjects (int numberOfObjects)  {
        if (numberOfObjects <= 0){
            System.out.println("""
        Значение не может быть <= 0. 
        Было установлено минимальное возможное значение, равное 1""");
            Controller.numberOfObjects = 1;
        } else {
            Controller.numberOfObjects = numberOfObjects;
        }
    }

    public static CustomClassType getCustomClassType(){
        return Controller.customClassType;
    }

    public static void setCustomClassType(CustomClassType type){
        Controller.customClassType = type;
        switch (type){
            case CustomClassType.AUTOMOBILE -> {
                Controller.customObjectEngineer.setBuilder(new AutoBuilder());
            }
            case CustomClassType.BOOK -> {
                Controller.customObjectEngineer.setBuilder(new BookBuilder());
            }
            case CustomClassType.ROOT_VEGETABLE -> {
                Controller.customObjectEngineer.setBuilder(new RootVegetableBuilder());
            }
        }
    }
}
