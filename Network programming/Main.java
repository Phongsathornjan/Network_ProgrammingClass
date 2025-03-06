
public class Main {
    public static void main(String[] args) throws InterruptedException {
        if(args.length == 1){
        try{
            int n = Integer.parseInt(args[0]);
            Warehouse w = new Warehouse(n);
            Producer []p = new Producer[5];
            Consumer []c = new Consumer[5];
            for(int j = 0; j < 5; j++){
                p[j] = new Producer(w,j+1);
                c[j] = new Consumer(w,j+1);
                p[j].start(); c[j].start();
                p[j].join(); c[j].join();
            }
        }catch(NumberFormatException e){
            System.out.println("Usage : java Main <number>");
        }
        }else{
            System.out.println("Usage : java Main <number>");
        }
    }
}
