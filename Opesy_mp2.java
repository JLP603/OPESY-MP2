
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.Random;



class Passenger extends Opesy_mp2 implements Runnable {
   private int id = 0;
   public Passenger(int id) { this.id = id; }

   public void run() {
      while (true) {
         nap(1+(int)random.nextInt(1000*wanderTime));
         System.out.println(" passenger"+id+" wants to ride");
         P(carAvail);  V(carTaken);  P(carFilled);
         System.out.println(" passenger"+id+" taking a ride");
         P(passengerReleased);
         System.out.println(" passenger"+id+" finished riding");
      }
   }
}

class Car extends Opesy_mp2 implements Runnable {
   private int id = 0,cap=0;
   public Car(int id,int cap) { 
      this.id = id;
      this.cap=cap;
   }
/*********************************************************** 
//Need to make it check if the passengers are at capacity first beforre calling P(carTaken)
//Also need to make it empty out the car completely when calling or before calling V(passengerReleased)
//there are no seperate board unboard load and unload methods for the passenger and car classes respectively
//we want the passengers to not be able to board until the car has invoked load
//we want the passengers to not unboard until the car has invoked unload
//uhh is the amount of time it takes to run the coaster fixed?
//are we allowed to have passengers line up to board other coasters?
//only one car can board at a time so a car has to be filled before other cars can be filled
//multiple cars can run the track at the same time
..unload order of cars is same as boarding order
//lookinto the multi lock or higher than 1 valued lock semaphores concept?
// if the number of passengers is over capacity for the last car make the passengers board the next car that is empty starting from 0
was called. In the case of calls of board and unboard  include which passenger thread made the call. In the case of load and unload specify the car number. 
In the case of run, specify which car is running. In addition, also specify if everyone is aboard a car by printing "All aboard car [i]" 
and likewise print "All ashore from car[i]" once everyone has disembarked a car. 
Lastly print "All rides completed" once all rides are done. (When all cars from 0-last car went through a trip???)
//test case input is 5 2 3
***********************************************************/

   public void run() {
      while (true) {
         System.out.println(" car"+id+" ready to load");
         V(carAvail);  P(carTaken);  V(carFilled);
         System.out.println(" car"+id+" going on safari");
         nap(1+(int)random.nextInt(1000*rideTime));
         System.out.println(" car"+id+" has returned");
         V(passengerReleased);
      }
   }
}

public class Opesy_mp2 {

   static final int wanderTime = 5, rideTime = 4, runTime = 60;
   static final Semaphore carAvail = new Semaphore(0);
   static final Semaphore carTaken = new Semaphore(0);
   static final Semaphore carFilled = new Semaphore(0);
   static final Semaphore passengerReleased = new Semaphore(0);
   Random random=new Random();

   public static void main(String[] args){
      Scanner sc = new Scanner(System.in); 
      System.out.println("Enter the number of passenger processes: ");
      int passenger_num = sc.nextInt();
      //System.out.println("\npassenger_num is: " + prcs_num);
        
      System.out.println("\nEnter the capacity of the car: ");
      int car_cap = sc.nextInt();
      //System.out.println("\ncar_cap is: " + car_cap);

      System.out.println("\nEnter the number of cars: ");
      int car_num = sc.nextInt();
      //System.out.println("\ncar_num is: " + car_num);
      sc.close();
      for (int i = 0; i < passenger_num; i++)
         new Thread(new Passenger(i)).start();
      for (int i = 0; i < car_num; i++)
         new Thread(new Car(i,car_cap)).start();
      nap(1000*runTime);
      
   }
   
   public static void nap(int time){
       try{
       Thread.sleep(time);
       }
       catch(InterruptedException ex){}
   }
   public static void P(Semaphore x){
       try{
       x.acquire();
       }
       catch(InterruptedException ex){
           
       }
       
   }
   
    public static void V(Semaphore x){
        x.release();
   }

}