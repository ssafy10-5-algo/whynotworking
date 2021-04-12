import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Baekjun15685_3rd {
	private static HashSet<int[]> dragonline;
	private static int[][] startend;
	private static boolean[][] nemos;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int dragonnemo = 0;
		nemos = new boolean[101][101]; 
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			dragoncurve(x,y,k,g);
		}
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (nemos[i][j] && nemos[i+1][j] && nemos[i][j+1] && nemos[i+1][j+1]) {
					dragonnemo++;
				}
			}
		}
		System.out.println(dragonnemo);
	}
	private static void dragoncurve(int x, int y, int k, int g) {
		dragonline = new HashSet<int[]>(); // 중복을 줄여 시간을 아낀다
		startend = new int[2][2];
		// 시작지점
		startend[0][0] = x;
		startend[0][1] = y;
		switch (k) { // 방향에 따라 끝지점 설정
		case 0:
			startend[1][0] = x+1;
			startend[1][1] = y;
			break;
		case 1:
			startend[1][0] = x;
			startend[1][1] = y-1;
			break;
		case 2:
			startend[1][0] = x-1;
			startend[1][1] = y;
			break;
		default:
			startend[1][0] = x;
			startend[1][1] = y+1;
			break;
		}
		dragonline.add(startend[0]);
		dragonline.add(startend[1]);
		for (int i = 0; i < g; i++) {
			dragonmove();
		}
		for (int[] dot : dragonline) {
			nemos[dot[0]][dot[1]]=true;
		}
	}
	
	/**드래곤커브를 그리는 매서드, 모든 점을 rotate메서드로 돌려 hashset에 넣고 end지점을 변경해준다*/
	private static void dragonmove() {
		Object[] dragonline_c = dragonline.toArray(); // foreach를 위한 array화
		for (Object dot : dragonline_c) {
			int[] rotated = rotate(startend[1],(int[])dot);
			dragonline.add(rotated); // 어레이 안쓰면 이게 갱신되면서 ConcurrentModification 런타임에러 남
		}
		startend[1] = rotate(startend[1],startend[0]);
	}
	
	/**pin을 기준으로 dot를 시계방향으로 90도 회전한 좌표를 출력하는 매서드*/
	private static int[] rotate(int[] pin, int[] dot) {
		return new int[] {pin[0] + pin[1]-dot[1], pin[1] + dot[0]-pin[0]};
	}
}
