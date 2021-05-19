import java.util.Scanner;

class Main
{
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in); 
        //System.out.println("Enter the number of passenger processes: ");
        int prcs_num = sc.nextInt();
        //System.out.println("\nprcs_num is: " + prcs_num);
        
        System.out.println("\nEnter the capacity of the car: ");
        int car_cap = sc.nextInt();
        //System.out.println("\ncar_cap is: " + car_cap);

        System.out.println("\nEnter the number of cars: ");
        int car_num = sc.nextInt();
        //System.out.println("\ncar_num is: " + car_num);
        sc.close();
        //create car_num number of car objects with identifiers from 0-(car_num-1)
    }
}