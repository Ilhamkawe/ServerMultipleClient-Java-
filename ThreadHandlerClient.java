import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class ThreadHandlerClient implements Runnable {
    DataInputStream din;
    DataOutputStream dot;
    BufferedReader br;
    Socket socket; 

    int no = 0;
    String baca = "";
    String kirim = "";
    ThreadHandlerClient(Socket s, int no, DataInputStream din, DataOutputStream dot, BufferedReader br ) throws IOException{

        this.no = no;
        this.din = din;
        this.dot = dot;
        this.br = br;
    }

    public void run() {

        bacaPesan();
        System.out.println("Client " + no + " : " + baca );

        if(baca.equals("Halo server")){
            
            kirimPesan("Hai Client");

        }
        while(!"selesai".equals(baca)){
            
            bacaPesan();
            System.out.println("Client " + no + " : " + baca );
            if(baca.equals("kuliah dimana?")){
                kirimPesan("Universitas Teknologi Yogyakarta");
                
            }else if(baca.equals("woi") || baca.equals("oi") ){
                kirimPesan("oit bro :}");
            }else if(baca.equals("apa kabar?")){
                
                kirimPesan("alhamdulillah baik, kamu ? ");
                System.out.println("Server : " + kirim );
                
                bacaPesan();
                System.out.println("Client " + no + " : " + baca );

                if(baca.equals("baik") || baca.equals("alhamdulillah baik") ){
                    kirimPesan("alhamdulillah");
                }else{
                    kirimPesan("Semoga lekas baik :)");
                }
            }else{
                kirimPesan("ga ngerti :(");
            }
            System.out.println("Server : " + kirim );
        }

        matiinKoneksi();

    }

    private void kirimPesan(String pesan){
        
        try {
            this.kirim = pesan;
            if("".equals(pesan)){
                System.out.print("Server : ");
                this.kirim = this.br.readLine();
            } 
            dot.writeUTF(this.kirim);
            dot.flush();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private String bacaPesan(){

        try {
            this.baca = din.readUTF();
            if(this.baca != null){};
            return this.baca;
        } catch (IOException e) {
            System.out.println(e);
            return "";
        }

    }

    private void matiinKoneksi(){

        try{
            din.close();
            dot.close();
        }catch(IOException e){
            System.out.println(e);
        }
    
    }

} 