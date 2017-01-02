package mypackage.serializacja;

import mypackage.graf.Graf;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class UsingObjectStream
{
    UsingObjectStream(String plik,int port)
    {
        Graf g = new Graf(plik);
        ServerSocket serversocket = null;
        try
        {
            serversocket = new ServerSocket(port);                
        }
        catch (Exception e)
        {
            System.err.println("Nie można słuchać na porcie " + port + ".");                
        }
        System.out.println("Oczekiwanie na połączenie z klientem...");         
        Socket clientsocket = null;
        try  
        {  
            clientsocket = serversocket.accept();  
        }  
        catch(Exception e)  
        {  
            System.err.println("Brak akceptacji ze strony klienta. " + e);                      
        }
        System.out.println("Połączono.");
        try
        {
            ObjectOutputStream stream = new ObjectOutputStream(clientsocket.getOutputStream()); 
            System.out.println("Rozpoczęcie wysyłania grafu...");
            stream.writeObject(g);            
            stream.flush();            
            stream.close();
        }
        catch (Exception e)
        {
            System.err.println("Błąd przy tworzeniu strumienia. " + e);
        }
        System.out.println("Wysyłanie zakończone.\nZamykanie połączenia z klientem.");        
    }
    public static void main(String[] args)
    {       
            UsingObjectStream test = new UsingObjectStream("p.txt",15000);        
    }
}