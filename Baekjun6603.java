import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjun6603 {
	static int[] numbers = new int[6];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if(n==0) { // 0나오면 탈출
				break;
			}
			String[] S = new String[n]; // 전체목록 받아오기
			for (int i = 0; i < n; i++) {
				S[i] = st.nextToken();
			}
			combination(0,0,n,S);
			System.out.println();
		}
	}

	private static void combination (int cnt, int start, int N, String[] S) {
		
		if(cnt==6) { // 6개 되면 출력
			StringBuilder sb = new StringBuilder();
			sb.append(numbers[0]).append(" ").append(numbers[1]).append(" ").append(numbers[2]).append(" ").append(numbers[3]).append(" ").append(numbers[4]).append(" ").append(numbers[5]);
			System.out.println(sb);
			return;
		}
		
		for (int i = start; i < N; i++) { // 조합생성중
			numbers[cnt] = Integer.parseInt(S[i]);
			combination(cnt+1,i+1, N,S);
		}
		
	}
	
}
