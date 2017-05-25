import java.util.*;
import java.io.*;

class Kontenery {
	
	private int ilosc = 7;
	private int[] wartosc;
	private int[] waga;
	private int max_waga = 20;
	private int max_wartosc = 20;
	private int ladownosc = 100;
	
	public Kontenery() {
		wartosc = new int[ilosc];
		waga = new int[ilosc];
	}
	
	public Kontenery(int ilosc) {
		this.ilosc = ilosc;
		wartosc = new int[ilosc];
		waga = new int[ilosc];
	}

	public Kontenery(int ilosc, int max_waga, int max_wartosc) {
		this.ilosc = ilosc;
		this.max_waga = max_waga;
		this.max_wartosc = max_wartosc;
		
		Random losowanie = new Random();
		this.ladownosc = losowanie.nextInt(max_waga) + ((int)(ilosc*0.5)-1)*max_waga + 1;
	
		wartosc = new int[ilosc];
		waga = new int[ilosc];
	}
	
	public Kontenery(int ilosc, int max_waga, int max_wartosc, int ladownosc) {
		this.ilosc = ilosc;
		this.max_waga = max_waga;
		this.max_wartosc = max_wartosc;
		this.ladownosc = ladownosc;	
		wartosc = new int[ilosc];
		waga = new int[ilosc];
	}

	public int getLadownosc() {
		return ladownosc;
	}
	
	public void setLadownosc(int ladownosc) {
		this.ladownosc = ladownosc;
	}
	
	public int getIlosc() {
		return ilosc;
	}
	
	public int getMaxWaga() {
		return max_waga;
	}
	
	public int getMaxWartosc() {
		return max_waga;
	}
	
	public void setMaxWaga(int max) {
		max_waga = max;
	}
	
	public void setMaxWartosc(int max) {
		max_wartosc = max;
	}
	
	public int[] getWaga() {
		int[] copy = new int[ilosc];
		for(int i = 0; i < ilosc; i++) 
			copy[i] = waga[i];
		return copy;
	}
	
	public int getWaga(int element) {
		return waga[element];
	}
	
	public int[] getWartosc() {
		int[] copy = new int[ilosc];
		for(int i = 0; i < ilosc; i++) 
			copy[i] = wartosc[i];
		return copy;
	}
	
	public int getWartosc(int element) {
		return wartosc[element];
	}
	
	private void losujWartosci() {
		Random losowanie = new Random();		
		for(int i = 0; i < ilosc; i++) 
			wartosc[i] = losowanie.nextInt(max_wartosc) + 1;
	}
	
	private void losujWagi() {
		Random losowanie = new Random();		
		for(int i = 0; i < ilosc; i++) 
			waga[i] = losowanie.nextInt(max_waga) + 1;
	}
	
	public void losowanie() {
		losujWartosci();
		losujWagi();
	}
	
	public void zPalca() {
		int[] wagan = {3,6,2,1,4};
		int[] wartoscn = {2,4,1,3,5};
		waga = wagan;
		wartosc = wartoscn;
	}
	
	public void printWagi() {
		System.out.println();
		for(int i = 0; i < ilosc; i++) 
			System.out.print(waga[i] + " > ");
		System.out.println();
	}
	
	public void printWartosci() {
		System.out.println();
		for(int i = 0; i < ilosc; i++) 
			System.out.print(wartosc[i] + " > ");
		System.out.println();
	}
}

class Dynamicznie {
	
	private int ilosc;
	private int[] wartosc;
	private int[] waga;
	private int ladownosc;
	private int[][] macierz;
	private int jakosc;
	
	List<Integer> weszly = new ArrayList<Integer>();
	
	public Dynamicznie(int ilosc, int[] wartosc, int[] waga, int ladownosc) {
		this.ilosc = ilosc;
		this.ladownosc = ladownosc;
		this.waga = waga;
		this.wartosc = wartosc;
		macierz = new int[ilosc+1][ladownosc+1];
	}
	
	private int max(int a, int b, int c) {
		if( (a >= b && b >= c ) || ( a >= c && c >= b ) ) return a;
		if( (b >= a && a >= c ) || ( b >= c && c >= a ) ) return b;
		return c;
	}
	
	private int max(int a, int b) {
		if( a >= b ) return a;
		return b;
	}
	
	
	private void wypelnij() {
		for(int i = 1; i <= ilosc; i++) {
			for(int j = 1; j <= ladownosc; j++) {
				if(waga[i-1] > j)
					macierz[i][j] = macierz[i-1][j];
				else
					macierz[i][j] = max(macierz[i-1][j], macierz[i-1][j-waga[i-1]]+wartosc[i-1]);
			}			
		}		
	}
	
	private void czytaj() {
		int i = ilosc, j = ladownosc;
		while( !( i == 0 || j == 0 ) ) {
			//System.out.println(i+ " " + j );
			if(macierz[i][j] != macierz[i-1][j]) {
				weszly.add(i);
				j -= waga[i-1];
				i--;
			}
			else {
				i--;
			}
			
		}
	}
	
	public void dynamiczne() {
		wypelnij();
		czytaj();
		jakosc();
	}
	
	public void printMacierz() {
		for(int i = 0; i <= ilosc; i++) {
			for(int j = 0; j <= ladownosc; j++) 
				System.out.print(macierz[i][j]+ "\t");
			System.out.println();
			
		}
	}
	
	public void printWeszly() {
		System.out.println();
		for(int x : weszly) System.out.print( x + ", ");
		System.out.println();
	}
	
	public List<Integer> getWeszly() {
		return weszly;
	}
	
	private void jakosc() {
		int suma = 0;
		for(int x : weszly) suma += wartosc[x-1];
		jakosc = suma;
	}
	
	public int getJakosc() {
		return jakosc;
	}
}


class Zachlannie {
	
	private int ilosc;
	private int[] wartosc;
	private int[] waga;
	private int ladownosc;
	private double[] stosunek;
	private int jakosc;
	
	List<Integer> weszly = new ArrayList<Integer>();
	private int wagaAfter = 0;
	private int wartoscAfter = 0;
	
	public Zachlannie(int ilosc, int[] wartosc, int[] waga, int ladownosc) {
		this.ilosc = ilosc;
		this.ladownosc = ladownosc;
		this.waga = waga;
		this.wartosc = wartosc;
	}
	
	private void stosunek() {
		stosunek = new double[ilosc];
		for(int i = 0; i < ilosc; i++) 
			stosunek[i] = wartosc[i]/waga[i];
	}
	
	private void lapczywy() {
		do {
			double max_stosunek = -1;
			int max_stosunek_id = -1;
			for(int i = 0; i < ilosc; i++) {
				if(max_stosunek < stosunek[i] && wagaAfter + waga[i] <= ladownosc) {
					max_stosunek = stosunek[i];
					max_stosunek_id = i;
				}
			}
			if(max_stosunek == -1) break;
			stosunek[max_stosunek_id] = -2;	
			//System.out.println(max_stosunek_id+1);
			weszly.add(max_stosunek_id);
			wagaAfter += waga[max_stosunek_id];
			wartoscAfter += wartosc[max_stosunek_id];
			
		}while(true);
		
	}
	
	public void zachlannie() {
		stosunek();
		lapczywy();
		jakosc();
	}
	
	public List<Integer> getWeszly() {
		return weszly;
	}
	
	public int wagaAfter() {
		return wagaAfter;
	}
	
	public int wartoscAfter() {
		return wartoscAfter;
	}
	
	public void printWeszly() {
		System.out.println();
		for(int x : weszly) System.out.print((x+1) + ", ");
		System.out.println();
	}
	
	private void jakosc() {
		int suma = 0;
		for(int x : weszly) suma += wartosc[x];
		jakosc = suma;
	}
	
	public int getJakosc() {
		return jakosc;
	}
	
}


class Pomiary {
	
	long start, zachTime, dynTime;
	double jakosc;
	
	public void pomiary(int ilosc, int maxwaga, int maxwartosc, int ladownosc) {
		Kontenery statek = new Kontenery(ilosc, maxwaga, maxwartosc, ladownosc);//ilość, maxwaga, max wartość, ładowność
		statek.losowanie();
		
		Zachlannie zachlannie = new Zachlannie(statek.getIlosc(), statek.getWartosc(), statek.getWaga(), statek.getLadownosc());
		start = System.nanoTime();
		zachlannie.zachlannie();
		zachTime = System.nanoTime()-start;
		
		Dynamicznie dynamiczne = new Dynamicznie(statek.getIlosc(), statek.getWartosc(), statek.getWaga(), statek.getLadownosc());
		start = System.nanoTime();
		dynamiczne.dynamiczne();
		dynTime = System.nanoTime()-start;
					
		jakosc = (double)(dynamiczne.getJakosc()-zachlannie.getJakosc())/dynamiczne.getJakosc();
	}	

	public long getZachTime() {
		return zachTime;
	}
	
	public long getDynTime() {
		return dynTime;
	}
	
	public double getJakosc() {
		return jakosc;
	}
}



class Test {
	
	static public void test(int ilosc, int maxwaga, int maxwartosc, int ladownosc) {
		Kontenery statek = new Kontenery(ilosc, maxwaga, maxwartosc, ladownosc);
		statek.zPalca();
		//statek.losowanie();
		statek.printWagi();
		statek.printWartosci();
		
		Zachlannie zachlannie = new Zachlannie(statek.getIlosc(), statek.getWartosc(), statek.getWaga(), statek.getLadownosc());
		zachlannie.zachlannie();
		zachlannie.printWeszly();
		
		Dynamicznie dynamiczne = new Dynamicznie(statek.getIlosc(), statek.getWartosc(), statek.getWaga(), statek.getLadownosc());
		dynamiczne.dynamiczne();
		dynamiczne.printMacierz();
		dynamiczne.printWeszly();
		
		System.out.println((double)(dynamiczne.getJakosc()-zachlannie.getJakosc())/dynamiczne.getJakosc());
	}

	static public void a(int ilosc, int maxwaga, int maxwartosc, int ladownosc) {
		for(int i = 0; i < 15; i++) {
			try {
				PrintStream printStream = new PrintStream("test/a/"+i+".txt");
				for(int j = ilosc; j <= ilosc*15; j += ilosc) {
					System.out.println("ilosc " + j + " " + i );
					Pomiary pomiary = new Pomiary();
					pomiary.pomiary(j, maxwaga, maxwartosc, ladownosc);

					printStream.println(pomiary.getZachTime());		
					printStream.println(pomiary.getDynTime());					
					printStream.format("%.15f%n",pomiary.getJakosc());
					printStream.println();
				}
			}				
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}						
		}
	}
	
	
	static public void b(int ilosc, int maxwaga, int maxwartosc, int ladownosc) {
		for(int i = 0; i < 15; i++) {
			try {
				PrintStream printStream = new PrintStream("test/b/"+i+".txt");
				for(int j = ladownosc; j <= ladownosc*15; j += ladownosc) {
					System.out.println("ladownosc " + j + " " + i );
					Pomiary pomiary = new Pomiary();
					pomiary.pomiary(ilosc, maxwaga, maxwartosc, j);

					printStream.println(pomiary.getZachTime());		
					printStream.println(pomiary.getDynTime());					
					printStream.format("%.15f%n",pomiary.getJakosc());
					printStream.println();
				}
			}				
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}						
		}
	}
	
	
	
}


class Plecak {
	public static void main(String[] args) {
	//Test.test(100, 50, 50, 100); //ilość, maxwaga, max wartość, ładowność
	Test.a(1000, 500, 500, 1000);
	Test.b(1000, 500, 500, 1000);
	}
	
	
}