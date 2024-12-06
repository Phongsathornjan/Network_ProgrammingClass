import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        if(args[0].equals("list")){
        } else if (args.length != 2) {
            System.out.println("error");
            System.exit(1);
        }

        String instruction = args[0];
        if (!(instruction.equals("upload") || instruction.equals("download") || instruction.equals("list"))) {
            System.out.println("command not found");
            System.exit(1);
        }

        try {
            Socket s = new Socket("127.0.0.1", 6789);
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            PrintWriter pw = new PrintWriter(out);

            if (instruction.equals("upload")) {
                File f = new File(args[1]);
                if (!f.exists()) {
                    System.out.println(f.getName()+" not found");
                    in.close();
                    out.close();
                    s.close();
                    System.exit(1);
                }

                FileInputStream fin = new FileInputStream(f);

                byte[] buffer = new byte[65536];
                int size;
                pw.println(instruction);
                pw.println(args[1]);
                pw.flush();

                String response = br.readLine();

                while ((size = fin.read(buffer)) > 0) {
                    out.write(buffer, 0, size);

                }

                fin.close();
                System.out.println(response);

            } else if (instruction.equals("download")) {
                pw.println(instruction);
                pw.println(args[1]);
                pw.flush();
                String response = br.readLine();
                System.out.println(response);
                if (response.equals("OK")) {
                    File f = new File(args[1]);
                    FileOutputStream fout = new FileOutputStream(f);
                    byte[] b = new byte[65536];
                    int size;
                    while ((size = in.read(b)) > 0) {
                        fout.write(b, 0, size);
                    }
                    fout.close();
                }
            } else if (instruction.equals("list")) {
                pw.println(instruction);
                pw.flush();
                String filename;
                while ((filename = br.readLine()) != null) {
                    System.out.println(filename);
                }

            } else {
                System.out.println("else");
            }

            in.close();
            out.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
