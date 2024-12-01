import java.util.ArrayList;
import java.util.Random;

public class BookBuilder implements Builder<Book> {

    private final ArrayList<Book> objects = new ArrayList<>();

    public BookBuilder readValuesFromConsole(int number) {
        objects.clear();

        for (int i = 0; i < number; i++) {
            System.out.println("Введите имя автора:");
            String author = UserInputValidator.stringInput();
            System.out.println("Введите наименование книги:");
            String name = UserInputValidator.stringInput();
            System.out.println("Введите количество страниц:");
            int numberOfPages = UserInputValidator.intInput(0);
            objects.add(new Book(author, name, numberOfPages));
        }
        return this;
    }

    public BookBuilder readValuesFromRandom(int number){
        objects.clear();
        Random rand = new Random();

        Object[][] books = {{"Достоевский Ф.М.", "Преступление и наказание",  465},
                {"Пушкин А.С.", "Капитанская дочка", 348},
                {"Лермонтов Р.М.", "Герой нашего времени", 183},
                {"Карамзин Н.М.", "Бедная Лиза", 279},
                {"Салтыков-Щедрин М.Е.", "Дикий помещик", 286}};

        for (int i = 0; i < number; i++) {
            Object[] book = books[rand.nextInt(books.length)];
            String author = (String) book[0];
            String name = (String) book[1];
            int numberOfPages = (int) book[2];
            objects.add(new Book(author, name, numberOfPages));
        }
        return this;
    }

    public BookBuilder readValuesFromFile(String path){
        objects.clear();

        try {
            for (var book : CustomClassOperations.deserializeArray(path)) {
                objects.add((Book) book);
            }

            if (objects.size() != Controller.getNumberOfObjects()) {
                System.out.println("""
                        Количество объектов в файле отлично от установленного в программе.
                        Значение в настройках программы было обновлено!""");
                Controller.setNumberOfObjects(objects.size());
            }
        } catch(ClassCastException e) {
            System.out.println("Сохраненные в файле объекты не являются книгами");
            if (!(Controller.getBooks().isEmpty())){
                objects.addAll(Controller.getBooks());
                Controller.clearCustomClassLists();
            }
        }
        return this;
    }

    public ArrayList<Book> build(){
        return objects;
    }
}