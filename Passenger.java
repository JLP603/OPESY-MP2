import java.util.*;
import java.util.concurrent.Semaphore;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Passenger extends Thread {

    private Random rand = new Random();
    protected int passenger_id;
    private int riding;
    
    private Semaphore mutex_sem1 = new Semaphore(1);
    private Semaphore mutex_sem2 = new Semaphore(1);
    protected Queue<Car> unfinished_Cars; 
    protected Queue<Car> finished_Cars;

    @Override
    public void run() 
    {
        try 
        {
            while(true)
            {
                if(riding==-1) 
                {
                    if(unfinished_Cars.peek()!=null||finished_Cars.peek()!=null) 
                    {
                        wander();
                        board();
                        unboard();
                    } 
                    else 
                    {
                        break;
                    }
                    Thread.sleep(3*1000);
                }
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public Passenger(int passenger_id, Queue<Car> unfinished_Cars, Queue<Car> finished_Cars, Semaphore mutex_sem1, Semaphore mutex_sem2) 
    {
        this.passenger_id=passenger_id;
        this.unfinished_Cars=unfinished_Cars;
        this.finished_Cars=finished_Cars;
        this.riding=-1;
        this.mutex_sem1=mutex_sem1;
        this.mutex_sem2=mutex_sem2;
    }
    private String getTimeStamp() 
    {
        LocalDateTime date_time=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("HH:mm:ss");
        return date_time.format(format);
    }
    private void wander() 
    {
        try 
        {
            System.out.println(getTimeStamp()+": Passenger "+(passenger_id+1)+" is wandering around the park");
            Thread.sleep((rand.nextInt(10)+2)*1000);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    private void unboard(){
        try 
        {

            mutex_sem2.acquire();
            while (true) 
            {
                Thread.sleep(1000);
                Car car=finished_Cars.peek();
                if(car!=null&&riding==car.get_car_id()) 
                {
                    while(!car.get_status().equalsIgnoreCase("unload")) 
                    {
                        Thread.sleep(1000);
                    }

                    if(car.get_car_id()==riding) 
                    {
                        car.unload_passengers();
                        System.out.println(getTimeStamp()+": Passenger "+(passenger_id+1)+" unboarded Car# "+(car.get_car_id()+1));
                        riding = -1;
                        if(finished_Cars.peek()!=null&&car.get_curr_passengers()==0) 
                        {
                            finished_Cars.remove();
                        }
                        break;
                    }
                }
                if(car==null&&unfinished_Cars.peek()==null) 
                {
                    break;
                }
            }
            mutex_sem2.release();
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
    private void board() 
    {
        try 
        {
            mutex_sem1.acquire();
            Car car = unfinished_Cars.peek();
            if(car!=null&&car.get_status().equalsIgnoreCase("load")&&car.not_full()) 
            {
                riding=car.get_car_id();
                car.load_passengers();
                System.out.println(getTimeStamp()+": Passenger "+(passenger_id+1)+" boarded Car# "+(riding+1));
                if(!car.not_full()) 
                {
                    Thread.sleep(1 * 1000);
                    finished_Cars.add(unfinished_Cars.remove());
                    if (unfinished_Cars.peek() != null){
                        unfinished_Cars.peek().set_status("load");
                    }

                }
            }
            mutex_sem1.release();
            while(riding!=-1&&(finished_Cars.peek()==null||finished_Cars.peek().get_car_id()!=riding)) 
            { 
                Thread.sleep(50);
                if(finished_Cars.peek()==null&&unfinished_Cars.peek()==null) 
                {
                    break;
                }
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
}