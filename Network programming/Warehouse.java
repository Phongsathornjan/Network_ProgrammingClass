import java.util.LinkedList;

public class Warehouse {
    volatile int productID;
    volatile int storeSize;
    volatile LinkedList<Integer> store = new LinkedList<>();
    
    public Warehouse(int size){
        this.storeSize = size;
    }
    
    public synchronized void put(int productID){
        if(store.size() == storeSize){
            try{
                wait();
            }catch(Exception e){}
        }
        this.productID = productID;
        store.add(productID);
        notify();
    }
    
    public synchronized int take(){
        if(store.isEmpty()){
            try{
                wait();
            }catch(Exception e){}
        }
        int result = store.removeFirst();
        notify();
        return result;
    }
}
