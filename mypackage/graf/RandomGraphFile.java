package mypackage.graf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.*;
/**
 * <b>Klasa RandomGraphFile</b>
 * Klasa tworzy plik tekstowy o schemacie (Id1,etykieta,Id2),
 * Do późniejszego wykorzystania w klasie Graf.
 * @see Graf
 * @see Krawedz
 */

class Krawedz implements Serializable                                 
{
    private int from;                                                           /**Id pierwszego wierzchołka*/
    private int to;                                                             /**Id drugiego wierzchołka*/
    private int etykieta;                                                       /**Etykieta Krawedzi*/
    /**
     * <i>Konstruktor parametrowy</i>
     * @param w1 - Pierwsza współrzędna
     * @param w2 - Druga współrzędna
     * @param name - Etykieta
     */
    Krawedz(int w1, int w2, int name)                                           
    {
        from = w1;                              
        to = w2;                                
        etykieta = name;                           
    }
    public int getFirst(){return from;}                                         /**Zwraca wsółrzędną pierwszego wierzchołka*/
    public int getSecond(){return to;}                                          /**Zwraca współrzędną drugiego wierzchołka*/
    public int getEtykieta(){return etykieta;}                                  /**Zwraca etykietę krawędzi*/
    public void show()                                                          /**Wypisuje krawędz w konsoli*/
    {
        System.out.println("W1: " + from + " W2: " + to + " Etykieta: " + etykieta);
    }    
}

public class RandomGraphFile                                                                
{
    /**
     * <i>Konstruktor parametrowy</i>
     * @param minId - Dolna granica losowania wierzchołków      
     * @param maxId - Górna granica loswania wierzchołków      
     * @param numEdge - MAxymalna ilość wylosowancyh krawędzi
     * @param plik - Nazwa pliku wyjściowego ("Ścieżka pliku")
     */
    public RandomGraphFile(int minId,int maxId, int numEdge, String plik)              
    {
        try
        {
            
            List<Krawedz> listazapisu = new ArrayList<Krawedz>();               /**Tworzy liste przechowującą aktualne krawędzi (zapobiega duplikatom) */          
            BufferedWriter w = (new BufferedWriter(new FileWriter(plik)));      /**Otwarcie pliku do zapisu */     
            Random losInt = new Random();                                       /** Rozpoczęcie pracy generatora liczb losowych */                                       
            System.out.println("Losowanie krawedzi...");
            long start = System.currentTimeMillis();
            for (int i = 0; i < numEdge; i++)                                   
            {
                int from = losInt.nextInt(maxId-minId)+minId;                   /**Losowanie pierwszego wierzchołka*/
                int to = losInt.nextInt(maxId-minId)+minId;                     /**Losowanie drugiego wierzchołka*/
                int etykieta = i+1;//losInt.nextInt(899)+100;                         /**Losowanie etykiety*/
                Krawedz k = new Krawedz(from,to,0);                      /**Stworzenie obiektu typu Krawedz*/
                //boolean check = true;
                //for(int j = 0; j < listazapisu.size(); j++)                     /**Petla sprawdzajaca czy dana krawedz juz istnieje*/
                //{
                //    if ((k.getFirst() == listazapisu.get(j).getFirst()) && (k.getSecond() == listazapisu.get(j).getSecond()))
                //    {
                //        check = false;
                //        break;
                //    }
                //}
                //if (check == true)
                //{
                //   listazapisu.add(k);                                         /**Dodanie elementu do listy */
                //    w.write(from + "," + etykieta + "," + to);                  /**Zapis linii do pliku */
                //    w.newLine();                                                /**Przejscie do nowej linni w pliku */
                //} 
                if (listazapisu.contains(k) == true) i--;
                else //(listazapisu.contains(k) == false)
                    {
                    listazapisu.add(k);
                    //w.write(from + "," + 1 + "," + to); 
                    //w.newLine(); 
                }
            }
            int etykieta;
            for (int i = 0; i < listazapisu.size(); i++)
            {
                etykieta = i+1;
                w.write(listazapisu.get(i).getFirst() + "," + etykieta + "," + listazapisu.get(i).getSecond()); 
                w.newLine();
            }
            long stop = System.currentTimeMillis();
            w.close();                                                          /**Zamkniecie strumienia wyjsciowego*/
            listazapisu.clear();                                                /**Czyszczenie listy*/
            System.out.println("Losowanie zakończone.\nZapis do pliku zakończony sukcesem w czasie " + (stop - start) + "ms.");
        }
        catch(Exception e)
        {
            System.err.println("Wystapil blad przy zapisywaniu danych");        /**Wyjatek kiedy wystapi błąd zapisu*/
        }
    }
    /*public void GenerateGraph(int minId,int maxId, int numEdge, String plik)
    {
        RandomGraphFile a = new RandomGraphFile(minId, maxId, numEdge, plik); 
    }*/
}



