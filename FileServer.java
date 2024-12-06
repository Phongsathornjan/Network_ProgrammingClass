import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class FileServer implements Runnable {
    Socket s = null;

    public FileServer(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            PrintWriter pw = new PrintWriter(out);

            String instruction = br.readLine();

            if (instruction.equals("upload")) {

                String filePath = br.readLine();
                File f = new File(filePath);
                FileOutputStream fout = new FileOutputStream(f);
                byte[] b = new byte[65536];
                int size;
                pw.println("OK");
                pw.flush();
                while ((size = in.read(b)) > 0) {
                    fout.write(b, 0, size);
                }
                fout.flush();
                fout.close();

            } else if (instruction.equals("download")) {

                String filePath = br.readLine();
                File f = new File(filePath);
                if (!f.exists()) {
                    pw.println("NOK");
                    pw.flush();
                } else {
                    pw.println("OK");
                    pw.flush();
                    FileInputStream fin = new FileInputStream(f);
                    byte[] buffer = new byte[65539];
                    int size;
                    while ((size = fin.read(buffer)) > 0) {
                        out.write(buffer, 0, size);
                    }
                    out.flush();
                }

            } else if (instruction.equals("list")) {
                File f = new File("./");
                String[] filename = f.list();
                for (int i = 0; i < filename.length; i++) {
                    pw.println(filename[i].toString());
                }
                pw.flush();
            }

            in.close();
            out.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            ServerSocket serv = new ServerSocket(6789);
            while (true) {
                Socket s = serv.accept();
                FileServer fs = new FileServer(s);
                executor.execute(fs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}