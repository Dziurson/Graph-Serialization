package mypackage.serializacja;

import mypackage.graf.Graf;
import mypackage.graf.Dijkstra;
import com.thoughtworks.xstream.XStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ReadingXStream
{
    private Graf g = null;
    ReadingXStream(int port, String ip)
    {
        Socket socket = null;  
        System.out.println("Próba połączenia do serwera...");  
        try  
        {  
            socket = new Socket(ip, port);
        }    
        catch(Exception e)  
        {  
            System.err.println("Nie mozna polaczyc. " + e);               
        } 
        System.out.println("Połączono z serwerem: " + ip + ".");  
        try
        {
            XStream xstream = new XStream();
            System.out.println("Pobieranie grafu..."); 
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            String xml = (String)input.readObject();
            System.out.println("Graf pobrany."); 
            System.out.println("Tworzenie grafu...");
            g = (Graf)xstream.fromXML(xml);
            System.out.println("Graf utworzony."); 
        }
        catch(Exception e)
        {
            System.err.println("Błąd przy transmisji. " + e);
        }
        
    }
    public Graf getGraf()
    {
        return g;
    }
    public static void main(String[] args)
    {       
            ReadingXStream test2 = new ReadingXStream(13000,"127.0.0.1");
            Dijkstra d = new Dijkstra(test2.getGraf(),1,50);
    }
}
