import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjun17404 {
	private static int[] RRlow, RGlow, RBlow, GRlow, GGlow, GBlow, BRlow, BGlow, BBlow;
	private static int[][] cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		//각 최소값을 받을 array 생성
		RRlow = new int[N]; // R로 시작 R로 끝
		RGlow = new int[N]; // R로 시작 G로 끝
		RBlow = new int[N]; // R로 시작 B로 끝
		GRlow = new int[N]; // G로 시작 R로 끝
		GGlow = new int[N]; // G로 시작 G로 끝
		GBlow = new int[N]; // G로 시작 B로 끝
		BRlow = new int[N]; // B로 시작 R로 끝
		BGlow = new int[N]; // B로 시작 G로 끝
		BBlow = new int[N]; // B로 시작 B로 끝
		cost = new int[N][3];
		
		// 초기값 설정
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken()); // R
			cost[i][1] = Integer.parseInt(st.nextToken()); // G
			cost[i][2] = Integer.parseInt(st.nextToken()); // B
		}
		RRlow[0] = cost[0][0];
		GGlow[0] = cost[0][1];
		BBlow[0] = cost[0][2];
		int a = cost[0][0]+cost[0][1]+cost[0][2];
		RGlow[0] = a;
		RBlow[0] = a;
		GRlow[0] = a;
		GBlow[0] = a;
		BRlow[0] = a;
		BGlow[0] = a;
		
		//N까지 계산
		for (int i = 1; i < N; i++) {
			calculate(i);
		}
		
		// 시작과 끝이 같지 않은 것 중 가장 작은 것
		int min = Math.min(RGlow[N-1],RBlow[N-1]); 
		min = Math.min(min, GRlow[N-1]);
		min = Math.min(min, GBlow[N-1]);
		min = Math.min(min, BRlow[N-1]);
		min = Math.min(min, BGlow[N-1]);
		System.out.println(min);
	}
	
	/**i번째 최소값들을 계산하는 함수*/
	private static void calculate(int i) {
		RRlow[i] = Math.min(RGlow[i-1],RBlow[i-1]) + cost[i][0];
		RGlow[i] = Math.min(RRlow[i-1],RBlow[i-1]) + cost[i][1];
		RBlow[i] = Math.min(RRlow[i-1],RGlow[i-1]) + cost[i][2];
		GRlow[i] = Math.min(GGlow[i-1],GBlow[i-1]) + cost[i][0];
		GGlow[i] = Math.min(GRlow[i-1],GBlow[i-1]) + cost[i][1];
		GBlow[i] = Math.min(GRlow[i-1],GGlow[i-1]) + cost[i][2];
		BRlow[i] = Math.min(BGlow[i-1],BBlow[i-1]) + cost[i][0];
		BGlow[i] = Math.min(BRlow[i-1],BBlow[i-1]) + cost[i][1];
		BBlow[i] = Math.min(BRlow[i-1],BGlow[i-1]) + cost[i][2];
	}
}
