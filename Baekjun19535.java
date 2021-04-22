import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baekjun19535 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] 연결점수 = new int[N+1];
		ArrayDeque<int[]> 간선 = new ArrayDeque<>();
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			간선.add(new int[] {a,b});
			연결점수[a]++;
			연결점수[b]++;
		}
		long ㄷ = 0;
		long ㅈ = 0;
		for (int i = 1; i <= N; i++) {
			ㅈ += ㅈ세기(연결점수[i]);
		}
		while (!간선.isEmpty()) {
			int[] line = 간선.poll();
			ㄷ += (연결점수[line[0]]-1)*(연결점수[line[1]]-1);
		}
		System.out.println(ㄷ);
		System.out.println(ㅈ);
		if (ㄷ>ㅈ*3) {
			System.out.println("D");
		} else if (ㄷ<ㅈ*3) {
			System.out.println("G");
		} else {
			System.out.println("DUDUDUNGA");
		} 
}

	private static long ㅈ세기(long n) {
		return n*(n-1)*(n-2)/6;
	}
}
