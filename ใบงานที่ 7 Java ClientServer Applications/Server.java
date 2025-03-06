import java.util.concurrent.AbstractExecutorService.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server implements Runnable{
    private static HashMap data = new HashMap();
    Socket s = null;
    
    public Server(Socket s){
        this.s = s;
    }
    
    public void run(){
        try{
            BufferedReader br = new BufferedReader(
                                new InputStreamReader(
                                s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            
            String Instruction = br.readLine();
            switch (Instruction) {
                case "add":
                    {
                        synchronized(Server.class){
                        String studentID = br.readLine();
                        String studentName = br.readLine();
                        data.put(studentID, studentName);
                        pw.println("OK");
                        break;
                        }
                    }
                case "search":
                    {
                        String studentID = br.readLine();
                        Object result = data.get(studentID);
                        if(result != null){
                            pw.println(result.toString());
                        }else{
                            pw.println("N/A");
                        }       break;
                    }
                default:
                    pw.println("Command not found");
                    break;
            }
            pw.flush();
            pw.close(); br.close(); s.close();
            
        }catch(Exception e){}
    }
    
    public static void main(String[] args) {
        ServerSocket serv = null;
        ExecutorService es = Executors.newFixedThreadPool(15);
        
        try{
            serv = new ServerSocket(23410);
        }catch(Exception e){System.exit(1);}
        
        while(true){
            try{
                Socket s = serv.accept();
                Server st = new Server(s);
                es.execute(st);
            }catch(Exception e){}
        }
    }
    
}
