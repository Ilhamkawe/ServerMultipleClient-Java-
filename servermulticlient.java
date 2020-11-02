import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class servermulticlient{

    private ServerSocket serverSocket;
    private Socket socket;

    int noClient = 1;
    
    servermulticlient(int port) throws IOException{

        serverSocket = new ServerSocket(port);

    }

    public static void main(String[]args){
        try {
            servermulticlient ss = new servermulticlient(1456);

            ss.handleClient();

        } catch (IOException e) {
            System.out.println(e);
        }catch(InterruptedException ie){

        }
    }

    private void handleClient()throws InterruptedException{
        try{
            while(true){
                this.socket = serverSocket.accept();

                DataInputStream din = new DataInputStream(socket.getInputStream());
                DataOutputStream dot = new DataOutputStream(socket.getOutputStream());
                InputStreamReader r=new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(r);

                System.out.println("Menyiapkan Handler Untuk Client " + noClient );

                ThreadHandlerClient thc = new ThreadHandlerClient(socket, noClient, din, dot, br);

                new Thread(thc).start();

                noClient++;

            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
   

}

 