import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjun1976 {
	private static boolean[] isconnect;
	private static boolean[] checked;
	private static int[] plan;
	private static int N;
	private static boolean[][] connect;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		connect = new boolean[N+1][N+1]; // 연결여부 boolean배열
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if (st.nextToken().equals("1")) {
					connect[i][j]=true;
				}
			}
		}
		plan = new int[M]; // 여행경로
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		
		isconnect = new boolean[N+1]; // 이 도시가 연결되어있는지 여부 확인
		checked = new boolean[N+1]; // 그 도시와 연결된 도시를 고려했는지 여부 확인
		
		// 여행경로 시작점과 연결된 도시들을 확인하자
		connecting(plan[0]); 
		
		// 여행경로가 전부 연결되어있는지 확인
		boolean connectAll=true;
		for (int i = 0; i < M; i++) {
			if (!isconnect[plan[i]]) {
				connectAll=false;
				break;
			}
		}
		if (connectAll) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	/**연결된 도시들 그룹을 만드는 함수*/
	private static void connecting(int city) {
		isconnect[city]=true; // 이 도시는 연결되어있다
		if (checked[city]) { // 이 도시와 연결된 도시들에대해 고려했다면 넘어간다
			return;
		}
		checked[city]=true; // 이 도시와 연결된 도시들에 대해 고려한다
		for (int i = 1; i <= N; i++) {
			if (connect[city][i] && !isconnect[i]) { // 갱신
				connecting(i);
			}
		}
	}
}
