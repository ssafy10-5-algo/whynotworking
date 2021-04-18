import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjun1753_4th {

	private static boolean[] out;
	private static int[] length;

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		ArrayDeque<int[]>[] sun = new ArrayDeque[V+1]; // 이거 더 좋은방법 없나요
		for (int i = 1; i <= V; i++) {
			sun[i] = new ArrayDeque<int[]>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			sun[start].add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())}); // 간선 업데이트
		}
		out = new boolean[V+1]; // 방문처리. 방문처리가 안되면 연결이 안되는데
		out[K]=true;
		length = new int[V+1]; // 최단경로 길이
		PriorityQueue<City> Cities = new PriorityQueue<>();
		//초기값 넣기
		while(!sun[K].isEmpty()) {
			int[] kansun = sun[K].poll();
			Cities.add(new City(kansun[0], kansun[1]));
		}
		
		while(!Cities.isEmpty()) {
			City city = Cities.poll();
			int len = city.shortlen;
			if (out[city.num]) { // 방문처리된 경로는 고려할 필요 없다. 이유는 후술
				continue;
			}
			out[city.num]=true;
			length[city.num]=len;
			while(!sun[city.num].isEmpty()) { // 추가적으로 고려해야할 도시들로부터의 경로를 추가해준다.
				int[] kansun = sun[city.num].poll();
				Cities.add(new City(kansun[0], len + kansun[1]));
				//len이 지금까지 갱신된 length중 최대이므로 여기서 추가되는 모든 경로는 이전 경로보다 효율이 좋지 않다. 그러므로 방문처리로 걸러도 된다.
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (out[i]) {
				sb.append(length[i]).append("\n");
			} else {
				sb.append("INF").append("\n");
			}
		}
		System.out.print(sb.toString());
	}
	
	/**PriorityQueue를 위한 클래스 생성*/
	static class City implements Comparable<City>{
		int num;
		int shortlen;
		
		public City(int num, int shortlen) {
			super();
			this.num = num;
			this.shortlen = shortlen;
		}

		@Override
		public int compareTo(City o) {
			if (this.shortlen > o.shortlen) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
