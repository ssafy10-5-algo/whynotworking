import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjun16398 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[][] flows = new long[N][N];
		boolean[] connected = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < N; j++) {
				flows[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		PriorityQueue<Flow> connecting = new PriorityQueue<Flow>();
		long connect = 0;
		connected[0]=true;
		for (int j = 1; j < N; j++) {
			connecting.add(new Flow(flows[0][j], 0, j));
		}
		while(!connecting.isEmpty()) {
			Flow now = connecting.poll();
			if (connected[now.i] && connected[now.j]) {
				continue;
			}
			int low = connected[now.i] ? now.j : now.i;
			connected[low]=true;
			connect +=now.cost;
			for (int i = 0; i < N; i++) {
				connecting.add(new Flow(flows[low][i], low, i));
			}
		}
		System.out.println(connect);
	}
}

class Flow implements Comparable<Flow> {
	long cost;
	int i;
	int j;
	
	public Flow(long cost, int i, int j) {
		super();
		this.cost = cost;
		this.i = i;
		this.j = j;
	}

	@Override
	public int compareTo(Flow o) {
		return Long.compare(this.cost, o.cost);
	}
}
