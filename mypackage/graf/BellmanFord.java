package mypackage.graf;

public class BellmanFord
{
    private double[] odleglosc;
    private int[] poprzednik;
    private int zrodlo;
    public BellmanFord(Graf g, int source, int destination)
    {
        long start = System.currentTimeMillis();
        System.out.println("Rozpoczecie pracy algorytmu Bellmana-Forda.");
        zrodlo = source;
        odleglosc = new double[g.liczbaWierzcholkow()];
        poprzednik = new int[g.liczbaWierzcholkow()];
        int tmp = 0;
        for (int i = 0; i < g.liczbaWierzcholkow(); i++)
        {
            odleglosc[i] = Double.POSITIVE_INFINITY;
            poprzednik[i] = 0;
        }
        odleglosc[zrodlo] = 0;
        for (int i = 1; i < g.liczbaWierzcholkow(); i++)
        {            
            for(int j = 0; j < g.IloscKrawedzi();j++)
            {
                if(odleglosc[g.GetKrawedz(j).getSecond()] > (odleglosc[g.GetKrawedz(j).getFirst()] + g.GetKrawedz(j).getEtykieta()))
                {
                    odleglosc[g.GetKrawedz(j).getSecond()] = odleglosc[g.GetKrawedz(j).getFirst()] + g.GetKrawedz(j).getEtykieta();
                    poprzednik[g.GetKrawedz(j).getSecond()] = g.GetKrawedz(j).getFirst();
                }
                tmp++;
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println("Zakończenie pracy algorytmu Bellmana-Forda.\nAlgorytm wykonał " + tmp + " iteracji w czasie " + (stop-start) + "ms.");
        getDistance(destination);
    }
    public void getDistance(int i)
    {
        if ((i > 0) && (odleglosc[i] != Double.POSITIVE_INFINITY))
        {            
            System.out.print("Najkrótsza ścieżka z wierzchołka " + zrodlo + " do wierzchołka " + i + " wynosi " + (int)odleglosc[i] + ". Ścieżka: ");            
            String out = (i + ".");
            while(i!=zrodlo)
            {   
                out = (poprzednik[i] + ", ") + out;                               
                i = poprzednik[i];
            }
            System.out.println(out);
        } 
        if ((i <= 0) || (i > odleglosc.length)) System.err.println("Niepoprawna wartość parametru.");
        if (odleglosc[i] == Double.POSITIVE_INFINITY) System.out.println("Ścieżka do podanego wierzchołka lub wierzchołek nie istnieje.");
    }
}
