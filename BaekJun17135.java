import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJun17135 {
	private static int[][] enemy;
	private static int[][] array;
	private static int N;
	private static int M;
	private static int D;
	private static int[] archer = new int[3];
	private static int turn;
	private static int killcount = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 여긴 두자리수 나올 수 있어서 charAt을 쓰면 안된다.
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		enemy = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0, index = 0; j < M; j++, index += 2) {
				enemy[i][j] = s.charAt(index)-'0';
			}
		}
		int killmax = 0;
		for (int i = 0; i < M-2; i++) {
			for (int j = i+1; j < M-1; j++) {
				for (int k = j+1; k < M; k++) {
					simulation(i,j,k);
					if (killcount>killmax) {
						killmax = killcount;
					}
				}
			}
		}
		System.out.println(killmax);
	}
	
	/**array에 enemy를 새로 받아옴*/
	private static void reset() {
		array = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				array[i][j] = enemy[i][j];
			}
		}
	}
	
	/**궁수 셋의 위치가 a,b,c일때의 시뮬레이션*/
	private static void simulation(int a, int b, int c) {
		reset(); 					// 죽은 적 되살리기
		archer =new int[]{a,b,c}; 	// 궁수위치 배정
		turn = 0; 					// 첫턴부터 시작
		killcount = 0; 				// 아무도 죽지 않은 상황
		for (int i = 0; i < N; i++) {
			turn_of_archer(); 		// 궁수의 턴 진행
			turn++; 				// 턴이 지남. 적의 턴이 없는 이유는 turn에 따라 궁수의 위치가 올라가므로
		}
	}
	
	/**궁수의 턴*/
	private static void turn_of_archer() {
		int[][] killed = new int[3][2];
		killed[0] =  kill(0);
		killed[1] =  kill(1);
		killed[2] =  kill(2);
		//위 목표포착 아래 사살
		if (array[killed[0][0]][killed[0][1]]==1) {
			array[killed[0][0]][killed[0][1]]=0;
			killcount++;
		}
		if (array[killed[1][0]][killed[1][1]]==1) {
			array[killed[1][0]][killed[1][1]]=0;
			killcount++;
		}
		if (array[killed[2][0]][killed[2][1]]==1) {
			array[killed[2][0]][killed[2][1]]=0;
			killcount++;
		}
	}
	
	/**h번 적이 이동한 뒤 arc번째 궁수가 죽이게 될 적의 좌표*/
	private static int[] kill(int arc) {
		int[] kill = new int[2];
		int min_d = N+M;
		for (int j = 0; j < M; j++) {
			for (int i = 0; i < N-turn; i++) {
				if (array[i][j]==1) { // 적 존재 확인
					int dx = j-archer[arc];
					int distance = N-turn-i + (dx>0 ? dx : -dx);
					if (distance<=D && distance<min_d) { // 사거리 내에 있고 가장 가까운가
						kill[0] = i;
						kill[1] = j;
						min_d = distance;
					}
				}
			}
		}
		if (min_d == N+M) { // 사거리 내의 적이 하나도 없을 경우
			//killcount를 늘리지 않도록 반드시 비어있을 궁수 바로 위 공간으로 지정
			kill[0] = N-turn-1;
			kill[1] = archer[arc];
		}
		return kill;
	}
}
