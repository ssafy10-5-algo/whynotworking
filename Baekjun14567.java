import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baekjun14567 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayDeque<Integer>[] next = new ArrayDeque[N+1];
		for (int i = 2; i <= N; i++) {
			next[i] = new ArrayDeque<Integer>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			next[Integer.parseInt(st.nextToken())].add(a);
			// DP를 진행하면서 이전꺼를 참고해야하니까 큰 쪽에 작은 쪽을 넣어줘야 한다.
		}
		int[] semester = new int[N+1];
		semester[1]=1;
		for (int i = 2; i <= N; i++) {
			int max = 1;
			while(!next[i].isEmpty()) {
				int past = next[i].poll();
				if (max < semester[past]+1) {
					max = semester[past]+1;
				}
			}
			semester[i]=max;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(semester[i]).append(" ");
		}
		System.out.println(sb);
	}

	
}
