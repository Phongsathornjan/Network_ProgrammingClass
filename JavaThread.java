public class JavaThread extends Thread {
    int number;
    public void run(){
        System.out.println(this.number + " HelloWorld");
    }

    public JavaThread(int number){
        this.number = number;
    }
    public static void main(String[] args) {
        try{
            int number = Integer.parseInt(args[0]);
            for(int i = 0; i < number; i++){
                JavaThread T = new JavaThread(i);
                T.start();
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Use: Java JavaThread <int>");
        }catch(Exception ee){
            System.out.println(ee);
        }
    }
}
