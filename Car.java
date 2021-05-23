import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Car extends Thread {

    protected int car_id;
    protected int capacity;
    protected int curr_passengers;
    protected String status;

    @Override
    public void run() 
    {
        try 
        {
            while(true) 
            {
                Thread.sleep(1*1000);
                if(!not_full())
                {
                    set_status("run");
                    Thread.sleep(10 * 1000); 
                    set_status("unload");
                    while (curr_passengers>0) 
                    {
                        Thread.sleep(50);
                    }
                    System.out.println(getTimeStamp() + ": All ashore from Car# " + (car_id+1));
                    break;
                }
            }
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }

    public Car(int car_id, int capacity)
    {
        this.car_id = car_id;
        this.capacity = capacity;
        this.curr_passengers = 0;
        if(car_id==0)
        {
            set_status("load");
        }
    }
    
    public int get_car_id() 
    {
        return car_id;
    }

    private String getTimeStamp() 
    {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return dateTime.format(formatter);
    }

    public String get_status() 
    {
        return status;
    }

    public void set_status(String status) 
    {
        this.status = status;
        if (status.equalsIgnoreCase("run")) 
        {
            System.out.println(getTimeStamp() + ": All aboard Car# " + (car_id+1));
            System.out.println(getTimeStamp() + ": Car# " + (car_id+1) + " = " + status + "ning the track");
        } else 
        {
            System.out.println(getTimeStamp() + ": Car# " + (car_id+1) + " = " + status + "ing passengers");
        }
    }
    
   

    public int get_curr_passengers() 
    {
        return curr_passengers;
    }

    

    public void load_passengers() 
    {
        curr_passengers++;
    }

    public void unload_passengers() 
    {
        curr_passengers--;
    }

    public boolean not_full() 
    {
        return curr_passengers < capacity;
    }
}