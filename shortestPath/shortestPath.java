import java.util.*; 

public class shortestPath{
    public static void main(String [] agrs){
        Scanner sc = new Scanner(System.in); 
        while(true){
            int n  = sc.nextInt(); 
            int m  = sc.nextInt(); 
            int q  = sc.nextInt(); 
            int s  = sc.nextInt(); 
            if(n == 0 && m == 0 && q == 0 && s == 0){
                break; 
            }
            ArrayDeque <edge> [] edges = new ArrayDeque[n]; 
            for(int i = 0; i < n; ++i) {
			    edges[i] = new ArrayDeque<>();
		    }
        
            for(int i = 0; i < m; i++){
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt(); 
                edges[u].add(new edge(v, w)); 
            }

            long [] distance = new long [n]; 
            PriorityQueue <state> pq = new PriorityQueue <state>(); 

            Arrays.fill(distance, Long.MAX_VALUE); 
            distance[s] = 0; 
            pq.add(new state(s, distance[s])); 
            while(pq.size() != 0){ 
                state small = pq.poll(); 
                for()
            }
        }
    }
    static class edge{
        int v; 
        int w; 
        edge(int vv, int ww){
            v = vv; 
            w = ww; 
        }
    }
    static class state implements Comparable <state> {
        int node; 
        long distance; 
        state(int n, long d){
            node = n; 
            distance = d;
        }
        public int compareTo(state myState){
            if(distance < myState.distance){
                return -1; 
            }
            if(distance == myState.distance){
                return 0; 
            }
            return 1; 

        }
    }
}