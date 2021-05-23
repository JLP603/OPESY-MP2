/*
***************************************************************
Names: Mappa, Jamie
       Pascual, Jeremy
       Pua, Shaun
       Soler, Javier
       
Group: Mini MP2 Synchronization 21
Section: CSOPESY-S15
***************************************************************
*/

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Semaphore;

class OPESY_MP2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int passenger_num, car_capacity, car_num;
        Semaphore mutex_sem1 = new Semaphore(1);
        Semaphore mutex_sem2 = new Semaphore(1);

        System.out.print("Enter the number of total passengers ex.(6): ");
        passenger_num = scanner.nextInt();
        System.out.print("Enter the capacity of a car ex.(2): ");
        car_capacity = scanner.nextInt();
        System.out.print("Enter the number of cars ex.(3): ");
        car_num = scanner.nextInt();
        System.out.print("\n");
        Queue<Car> unfinished_Cars = new LinkedList<Car>();
        Queue<Car> finished_Cars = new LinkedList<Car>();
        Passenger[] passengers = new Passenger[passenger_num];
        if (car_capacity < passenger_num) 
        {
            for (int i=0; i<car_num; i++) 
            {
                Car car= new Car(i,car_capacity);
                car.start();
                unfinished_Cars.add(car);
            }
            for (int i=0; i<passenger_num; i++) 
            {
                passengers[i]= new Passenger(i, unfinished_Cars,finished_Cars,mutex_sem1,mutex_sem2);
                passengers[i].start();
            }
            try 
            {
                passengers[(passenger_num - 1)].join();
                LocalDateTime dateTime=LocalDateTime.now();
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
                System.out.println(dateTime.format(formatter)+": All rides completed!");
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }

        }
        else 
        {
            System.out.println("Invalid input was entered. A car's capacity cannot be greater or equal to the total amount of passengers.");
        }
        scanner.close();

    }
}