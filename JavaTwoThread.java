public class JavaTwoThread implements Runnable {
    int start;
    int end;
    int sleepTime;
    int result = 0;

    public JavaTwoThread (int start, int end, int sleepTime){
        this.start = start;
        this.end = end;
        this.sleepTime = sleepTime;
    }

    public void run() {
        for(int i = this.start; i<=this.end;i++){
            this.result += i;
        }
        try{
            Thread.sleep(sleepTime*1000);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public int getResult(){
        return result;
    }

    public static void main(String[] args) {
        JavaTwoThread s1 = new JavaTwoThread(1,5000,5);
        JavaTwoThread s2 = new JavaTwoThread(5001,10000,10);
        Thread t1 = new Thread(s1);
        Thread t2 = new Thread(s2);
        try{
            t1.start(); t2.start();
            t1.join(); t2.join();
            System.out.println(s1.getResult()+s2.getResult());
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
