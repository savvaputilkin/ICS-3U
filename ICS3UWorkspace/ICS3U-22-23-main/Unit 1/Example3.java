package day2.day3;
//ttt
public class Example3 {
    public static void main(String[] args) {
    int x = 17;
 
    System.out.println("Remainder " + (x % 5));
    System.out.println("Division " + (x / 5));

    int number = 57856;
    
    int digitOne = number / 10000; //5
    int digitTwo = number / 1000 % 10; //7
    int digitThree = number / 100 % 10; //8
    int digitFour = number/ 10 % 10; //5
    int digitFive = number % 10; //6
    
    int sum = digitOne + digitTwo + digitThree + digitFour + digitFive;
    System.out.println(sum);

    //what is the sum of the induvidual digits 
    // ie. 5 + 7 + 8 + 5 + 6






    }
}
        
