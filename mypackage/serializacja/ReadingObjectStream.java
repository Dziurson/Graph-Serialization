package mypackage.serializacja;

import mypackage.graf.Graf;
import mypackage.graf.Dijkstra;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ReadingObjectStream
{
    private Graf g = null;
    ReadingObjectStream(int port)
    {
        Socket socket = null;  
        System.out.println("Próba połączenia do serwera...");  
        try  
        {  
            socket = new Socket("127.0.0.1", port);
        }    
        catch(Exception e)  
        {  
            System.err.println("Nie mozna polaczyc. " + e);               
        } 
        System.out.println("Połączono z serwerem.");  
        try
        {
            System.out.println("Pobieranie grafu..."); 
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            g = (Graf)ois.readObject();
        }
        catch(Exception e)
        {
            System.err.println("Błąd przy transmisji. " + e);
        }
        System.out.println("Graf pobrany."); 
    }
    public Graf getGraf()
    {
        return g;
    }
    public static void main(String[] args)
    {       
            ReadingObjectStream test = new ReadingObjectStream(15000);
            Dijkstra d = new Dijkstra(test.getGraf(),1,50);
    }
}
