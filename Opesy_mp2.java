/*
Passengers should invoke board and unboard.
Passengers should wander around the park for a random amount of time before getting in line for the roller coaster.

    The line in this case may not necessarily be first come first served.

The car should invoke load, run and unload.
Passengers cannot board until the car has invoked load
The car cannot depart until C passengers have boarded.
Passengers cannot unboard until the car has invoked unload.
Each passenger should be a thread that waits to board.
The car should be a thread that waits until C passengers want to board, then goes around the track for some fixed amount of time, then lets the passengers unboard.

    Only one car can board at a time
    Multiple cars can be on the track concurrently
    Cars cannot pass each other, they have to unload in the same order that they boarded
    All of the cars from one carload must must disembark before any of the threads from subsequent carloads.
        It is possible that the number of passengers cannot fit in one round of car load. For example if the current car m-1 is full, then the next car is 0(wrap-around) assuming that it is already available .


The output of your program should be a trace of the calls of the functions (board, unboard, load, unload and run).
Each line of output should contain a "time stamp", a short message indicating which procedure
was called. In the case of calls of board and unboard  include which passenger thread made the call. In the case of load and unload specify the car number. In the case of run, specify which car is running. In addition, also specify if everyone is aboard a car by printing "All aboard car [i
]" and likewise print "All ashore from car[i]" once everyone has disembarked a car. Lastly print "All rides completed" once all rides are done.

    Note: It is possible that a passenger will be unable to ride a car if they cannot complete the required number of passengers C

. Just let it be.
*/
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
      //System.out.println("Enter the number of passenger processes: ");
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