import java.util.*;

public class Consumer extends Thread{
    Warehouse w;
    volatile int consumerID;
    
    public Consumer(Warehouse w,int cid){
        this.w = w;
        this.consumerID = cid;
    }
    
    public void run(){
        int id;
        Random r = new Random();
        for(int i = 0; i < 10; i++){
            System.out.println("Consumer "+this.consumerID+" : try to take product");
            id = w.take();
            System.out.println("Consumer "+this.consumerID+" : take product with id = "+ id);
            try{
            Thread.sleep(r.nextInt(1000));
            }catch(Exception e){}
        }  
    }
    
}
