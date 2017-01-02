package mypackage.graf;

import java.util.*;

public class Dijkstra
{
    private int[] odleglosc;
    private int[] poprzednik;
    private Queue<Integer> kolejka;
    private int zrodlo;
    private boolean[] odwiedzone;
    public Dijkstra(Graf g, int source, int destination)
    {
        long start = System.currentTimeMillis();
        System.out.println("Rozpoczecie pracy algorytmu Dijkstra.");
        zrodlo = source;
        odleglosc = new int[g.liczbaWierzcholkow()];
        odwiedzone = new boolean[g.liczbaWierzcholkow()];
        poprzednik = new int[g.liczbaWierzcholkow()];
        kolejka = new PriorityQueue(g.liczbaWierzcholkow());
        int iteracje = 0;
        for(int i = 0; i < g.liczbaWierzcholkow(); i++)
        {
            odleglosc[i] = Integer.MAX_VALUE;
            poprzednik[i] = 0;
            odwiedzone[i] = false;
        }
        odleglosc[source] = 0;
        odwiedzone[source] = true;
        kolejka.offer(source);
        while (!kolejka.isEmpty())
        {            
            int v = kolejka.poll();
            for(Krawedz edge : g.ListaSasiadow(v))
            {
                int wierzcholek = edge.getSecond();
                if (odleglosc[wierzcholek] > (odleglosc[v] + edge.getEtykieta()))
                {
                    odleglosc[wierzcholek] = odleglosc[v] + edge.getEtykieta();
                    poprzednik[wierzcholek] = v;
                    if(!odwiedzone[wierzcholek])
                    {
                        odwiedzone[wierzcholek] = true;
                        kolejka.offer(wierzcholek);
                    }
                }
                iteracje++;
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println("Zakonczenie pracy algorytmu Dijkstra.\nAlgorytm wykonał " + iteracje + " iteracji w czasie " + (stop-start) + "ms.");
        getDistance(destination);
    }
    public void getDistance(int i)
    {
        if ((i > 0) && (odleglosc[i]!=Integer.MAX_VALUE))
        {
            System.out.print("Najkrótsza ścieżka z wierzchołka " + zrodlo + " do wierzchołka " + i + " wynosi " +odleglosc[i] + ". Ścieżka: ");
            String out = (i + ".");
            while(i != zrodlo)
            {   
                out = (poprzednik[i] + ", ") + out;                               
                i = poprzednik[i];
            }
            System.out.println(out);
        } 
        if ((i <= 0) || (i > odleglosc.length)) System.err.println("Niepoprawna wartość parametru.");
        if (odleglosc[i] == Integer.MAX_VALUE) System.out.println("Ścieżka do podanego wierzchołka lub wierzchołek nie istnieje.");
    }
}
