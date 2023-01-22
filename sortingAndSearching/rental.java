import java.io.*;
import java.util.*; 

public class rental {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("rental.in"));
        PrintWriter pw = new PrintWriter("rental.out");
        
		StringTokenizer st = new StringTokenizer(r.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M= Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int [] cows = new int [N]; 
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(r.readLine());
            cows[i] = Integer.parseInt(st.nextToken());
        }
        ArrayList<int[]> gallons = new ArrayList<int[]>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(r.readLine());
            int x = Integer.parseInt(st.nextToken()); 
            int y = Integer.parseInt(st.nextToken()); 
            gallons.add(new int[]{x,y});
        }

        int [] rent = new int [R]; 
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(r.readLine());
            rent[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(rent); 
        long [] rentPrefix = new long [N+1]; 
        for(int i = 1; i < N+1; i++){
            if(i > R){
                rentPrefix[i] = rentPrefix[i-1]; 
            }
            else{
                rentPrefix[i] = rentPrefix[i-1] + rent[R-i]; 
            }
        }

    
        Arrays.sort(cows);
         
        long [] milkPrefix = new long [N+1]; 
        Collections.sort(gallons, new Comp());
        int counter = 0; 
        for(int i = 1; i < N+1; i++){
            int current = cows[N-i]; 
            long amountMade = 0; 
            while(counter < gallons.size()){
                int numGallons = gallons.get(counter)[0];
                int price = gallons.get(counter)[1];
                if(current > numGallons){
                    current -= numGallons; 
                    amountMade += (numGallons * price); 
                    counter++; 
                }
                else {
                    gallons.get(counter)[0] = numGallons - current; 
                    amountMade += (current * price); 
                    break; 
                }  
            }
            milkPrefix[i] = amountMade + milkPrefix[i-1]; 
        }

        long max = 0; 
        for(int i = 0; i < N+1; i++){
            long value = rentPrefix[i] + milkPrefix[N-i]; 
            // pw.println("rent: " + rentPrefix[i]); 
            // pw.println("milk: "+ milkPrefix[N-i]); 
            max = Math.max(value, max); 

        }

        pw.println(max); 
		pw.close();
	}

    static class Comp implements Comparator<int[]> {
		public int compare(int[] a, int[] b){
            return Integer.compare(b[1],a[1]);
		}
    
	}
}