import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjun1182 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] su = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			su[i] = Integer.parseInt(st.nextToken());
		}
		// 공집합 아닌 부분수열 비트연산자로 구현
		int cases = 0;
		for (int i = 1; i < 1<<N; i++) { 
			int sum = 0;
			for (int j = 0; j < N; j++) {
				if ((i&1<<j) !=0) {
					sum +=su[j];
				} 
			}
			if (sum==S) {
				cases++;
			}
		}
		System.out.println(cases);
	}
}
