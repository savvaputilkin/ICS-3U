package day2.day3;
//ttt
public class Example2 {
    public static void main(String[] args) {
        int numberOne = 16, numberTwo = 17, numberThree = 19;
        int numStudents = 3;
        //int/int = int double/int = double double/double = double int/double = double
        
        // because all the variables are ints we need to temporaraly cast something as a double before the divison
        double averageAge = (double)(numberOne + numberTwo + numberThree) /numStudents;

        System.out.println("Average age is " + averageAge);
    
        int x = 6;
        int y = 7;

        System.out.println("The sum of " + x + " and " + y + " is " + (x + y));
    
}
}