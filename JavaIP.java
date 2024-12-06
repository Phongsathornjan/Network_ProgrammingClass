import java.net.*;

public class JavaIP {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Usage: java javaIP <hostname/IP>");
            return;
        }
        try{
            InetAddress ad =  InetAddress.getByName(args[0]);
            System.out.println("HostName = "+ad.getHostName());
            System.out.println("IP = "+ad.getHostAddress());
        }catch(UnknownHostException e){
            System.err.println("Error: unknown host or IP address");
        }catch(Exception ee){
            System.out.println(ee);
        }
    }
}