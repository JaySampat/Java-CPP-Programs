import java.util.*;

public class Dijkstras {
	static int n, m, startNode;
	static ArrayDeque<edge>[] edges;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		startNode = sc.nextInt();
		edges = new ArrayDeque[n];
		for(int i = 0; i < n; ++i) {
			edges[i] = new ArrayDeque<>();
		}
		
		// scan in edges
		for(int i = 0; i < m; ++i) {
			// assume u and v are 0 indexed
			int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
			edges[u].add(new edge(v, w));
		}
		
		// do Dijkstra's
		// stores distance from start node to every other node
		long[] dist = new long[n];
		// fill with some massive value
		Arrays.fill(dist, Long.MAX_VALUE / 2);
		// set up Queue
		PriorityQueue<state> pq = new PriorityQueue<state>();
		
		// kick off Dijkstra's
		dist[startNode] = 0;
		pq.add(new state(startNode, 0));
		while(!pq.isEmpty()) {
			state curr = pq.poll();
			
			// SIGNIFICANT speed-up
			if(curr.dist > dist[curr.idx]) {
				continue;
			}
			
			// see what options this state provides to us;
			// loop over his edges
			for(edge e : edges[curr.idx]) {
				// check to make sure this edge is actually useful
				if(dist[curr.idx] + e.w < dist[e.v]) {
					// set this guy's new and improved distance
					dist[e.v] = dist[curr.idx] + e.w;
					// add this new state to our Queue
					pq.add(new state(e.v, dist[e.v]));
				}
			}
		}
		
		System.out.println(Arrays.toString(dist));
	}
	static class state implements Comparable<state>{
		int idx;
		long dist; // make sure this is a long; should be able to store the biggest
		// distance we'll ever need to consider and then some
		state(int ii, long dd){
			idx = ii;
			dist = dd;
		}
		// sort the states with smaller distance first
		public int compareTo(state in) {
			return Long.compare(dist, in.dist);
		}
	}
	static class edge{
		int v, w;
		edge(int vv, int ww){
			v = vv;
			w = ww;
		}
	}
}
/*
4 5 0
0 1 7
0 2 11
1 2 2
2 3 1
1 3 15

4 6 0
0 1 7
0 2 11
1 2 2
2 3 1
1 3 15
2 0 2

*/