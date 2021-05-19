import java.util.Scanner;
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
/*
loadingArea= [Semaphore(0)for i in range(m)]
loadingArea[1].signal()
unloadingArea = [Semaphore(0)for i in range(m)]
unloadingArea[1].signal()
        
def next(i):
            return((i + 1) % m);
*/  
}