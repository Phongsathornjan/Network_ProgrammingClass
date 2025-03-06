import java.util.*;

public class Producer extends Thread{
    Warehouse w;
    volatile int producerID;
    
    public Producer(Warehouse w,int pid){
    this.w = w;
    this.producerID = pid;
    }
    
    public void run(){
        Random r = new Random();
        for(int i = 0; i < 10; i++){
            int id = r.nextInt(100);
            System.out.println("Producer "+this.producerID+" : try to put product with id = "+id);
            w.put(id);
            System.out.println("Producer "+this.producerID+" : put product with id = "+ id);
            try{
            Thread.sleep(r.nextInt(1000));
            }catch(Exception e){}
        }  
    }
}
