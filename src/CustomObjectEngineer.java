import java.util.ArrayList;

public class CustomObjectEngineer <T extends CustomObject & Comparable<T>>{
    private Builder<T> builder;

    public CustomObjectEngineer(Builder<T> builder){
        this.builder = builder;
    }

    public void setBuilder(Builder<T> builder) {
        this.builder = builder;
    }

    public ArrayList<T> buildFromConsole(int number){
        return this.builder.readValuesFromConsole(number).build();
    }

    public ArrayList<T> buildFromRandom(int number){
        return this.builder.readValuesFromRandom(number).build();
    }

    public ArrayList<T> buildFromFile(String path){
        return this.builder.readValuesFromFile(path).build();
    }
}