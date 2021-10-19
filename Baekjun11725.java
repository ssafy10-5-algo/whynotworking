import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baekjun11725 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Integer>[] connected = new ArrayDeque[N+1];
		for (int i = 1; i <= N; i++) {
			connected[i]=new ArrayDeque<Integer>();
		}
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connected[a].add(b);
			connected[b].add(a);
		}
		ArrayDeque<Integer> check = new ArrayDeque<>();
		check.add(1);
		int[] parent = new int[N+1];
		while (!check.isEmpty()) {
			int now = check.poll();
			for (int point : connected[now]) {
				if (point==parent[now]) {
					continue;
				}
				parent[point]=now;
				check.add(point);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; i++) {
			sb.append(parent[i]).append("\n");
		}
		System.out.println(sb);
	}
}
