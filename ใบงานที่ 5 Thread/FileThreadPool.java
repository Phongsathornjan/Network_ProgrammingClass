import java.io.*;
import java.util.concurrent.*;

class FileThreadPool implements Runnable {
    String line;

    public void run(){
        
        try {
            int num = Integer.parseInt(this.line.trim());
            int result = num * num;
            System.out.println(num + " -> " + result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + line);
        } catch(Exception ee){
            System.out.println(ee);
        }

    }

    public FileThreadPool (String line) {
        this.line = line;
    }

    public static void main(String[] args) {

            if(args.length < 1){
                System.out.println("Please Provide input file");
                return;
            }

            String fileName = args[0];
            ExecutorService executor = Executors.newFixedThreadPool(3);

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    FileThreadPool task = new FileThreadPool(line); 
                    executor.execute(task);
                }
                executor.shutdown();

            }catch(Exception e){
                System.out.println(e);
            }
    }
}