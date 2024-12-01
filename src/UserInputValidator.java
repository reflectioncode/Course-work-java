import java.util.Scanner;

public class UserInputValidator {

  public static int intInput(int min){
      Scanner in = new Scanner(System.in);

      while(true){
            try {
                int intValue =  Integer.parseInt(in.nextLine());
                if (intValue > min){
                    return intValue;
                } else{
                    System.out.printf("Введенное значение должно быть больше %d", min);
                }
            } catch (NumberFormatException e) {
                System.out.println("Введеное значение нельзя привести к целому числу");
            }
        }
    }

    public static String stringInput(){
        Scanner in = new Scanner(System.in);
        String stringValue = "";
        while (stringValue.isEmpty()){
            stringValue = in.nextLine();
            if (stringValue.isEmpty()){
                System.out.println("Значение не может быть пустой строкой");
            }
        }
        return stringValue;
    }
}
