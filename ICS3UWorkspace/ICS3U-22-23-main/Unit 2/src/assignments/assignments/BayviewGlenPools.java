package assignments;

import java.util.Scanner;

public class BayviewGlenPools {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

       
        //scanner in = new scanner(System.in);
        // ^ gets the input from the user

        

        //input questions
        System.out.println("What is the length of your pool?(Meters): ");
        double length = in.nextDouble();

        System.out.println("What is the width of your pool?(Meters): ");
        double width = in.nextDouble();

        System.out.println("What is the depth of the shallow end in your pool?(Meters): ");
        double shallowEndDepth = in.nextDouble();

        System.out.println("What is the depth in the deep end for your pool?(Meters):  ");
        double deepEndDepth = in.nextDouble();

        System.out.println("What is the length of the transition in your pool? (Meters): ");
        double transitionSlope = in.nextDouble();

        System.out.println("What is the length of your shallow end in your pool? (Meters): ");
        double shallowEndLength = in.nextDouble();

        System.out.println("Please enter the price of liner/m^2: ");
        double price = in.nextDouble();
        
        //volume of pool
        double transitionHeight = (double) deepEndDepth-shallowEndDepth;
        double transitionLength = Math.sqrt(Math.pow(transitionSlope,2) - Math.pow(transitionHeight, 2));
        double deepEndLength = (double)length - transitionLength - shallowEndLength;

        double volume = (deepEndLength*deepEndDepth*width) + (shallowEndLength*shallowEndDepth*width) 
        + (shallowEndDepth*width*transitionLength) + (0.5*transitionHeight*transitionLength*width);
        
        //rounds the volume 
        double waterNinety = 1000*(volume*0.9);
        double waterNinetyRounded = Math.round(waterNinety*100)/100.0;
        

        System.out.println("You need " + waterNinetyRounded+ "L to keep your water at 90%");

        //Surface area  
        double SurfaceArea = ((deepEndDepth*width) + (2*(deepEndLength*deepEndDepth)) + (deepEndLength*width)) 
        + ((shallowEndDepth*width) + (2*(shallowEndLength*shallowEndDepth)) + (shallowEndLength*width)) + 
        ((transitionSlope*width) + (2*(transitionHeight*transitionLength)/2) + (2*(transitionLength*shallowEndDepth))); 

        
         //rounds the surface area 
        double roundedSA = Math.round(SurfaceArea*100)/100.0;
       

        System.out.println("You will need: " + roundedSA + "m^2 of lining.");

       
        double linerCost = roundedSA*price;
        
        
        System.out.print(" The liner will cost $" + linerCost + ".");

        in.close();

    }
}



