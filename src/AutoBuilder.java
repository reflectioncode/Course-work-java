import java.util.ArrayList;
import java.util.Random;

public class AutoBuilder implements Builder<Automobile> {

    private final ArrayList<Automobile> objects = new ArrayList<>();

    public AutoBuilder readValuesFromConsole(int number) {
        objects.clear();

        for (int i = 0; i < number; i++) {
            System.out.println("Введите наименование модели:");
            String model = UserInputValidator.stringInput();
            System.out.println("Введите значение мощности:");
            int power = UserInputValidator.intInput(0);
            System.out.println("Введите год производства:");
            int yearOfProduction = UserInputValidator.intInput(1900);
            objects.add(new Automobile(power, model, yearOfProduction));
        }
        return this;
    }

    public AutoBuilder readValuesFromRandom(int number){
        Random rand = new Random();
        objects.clear();

        for (int i = 0; i < number; i++) {
            int power = 250 + (int) (100 * Math.random());
            String[] models = {"Opel", "BMW", "Mercedes", "Mitsubishi", "Audi"};
            String model = models[rand.nextInt(models.length)];
            int yearOfProduction = 1990 + (int) (30 * Math.random());
            objects.add(new Automobile(power, model, yearOfProduction));
        }
        return this;
    }

    public AutoBuilder readValuesFromFile(String path){

        objects.clear();
        try {
            for (var auto : CustomClassOperations.deserializeArray(path)) {
                objects.add((Automobile) auto);
            }
            if (objects.size() != Controller.getNumberOfObjects()) {
                System.out.println("""
                        Количество объектов в файле отлично от установленного в программе.
                        Значение в настройках программы было обновлено!""");
                Controller.setNumberOfObjects(objects.size());
            }
        } catch(ClassCastException e) {
            System.out.println("Сохраненные в файле объекты не являются автомобилями");
            if (!(Controller.getAutomobils().isEmpty())){
                objects.addAll(Controller.getAutomobils());
                Controller.clearCustomClassLists();
            }
        }
        return this;
    }

    public ArrayList<Automobile> build(){
        return objects;
    }
}