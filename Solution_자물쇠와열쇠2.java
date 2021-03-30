import java.util.ArrayDeque;
public class Solution_자물쇠와열쇠2 {
    private ArrayDeque<int[]> keycell;
	private boolean answer;
	private int M;
	private int N;
	private boolean[][] keycheck;
	private int count;
	public boolean solution(int[][] key, int[][] lock) {
    	N = lock.length;
    	M = key.length;
    	keycheck = new boolean[N][N]; // 자물쇠 돌기가 true인 배열
    	count = 0; // 자물쇠 홈의 개수
    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (lock[i][j]==1) {
					keycheck[i][j] = true;
				} else {
					count++;
				}
			}
		}
    	if (count == 0) { // 필요한지 모르겠지만 예외처리
			return true;
		}
    	keycell = new ArrayDeque<int[]>(); // 열쇠의 돌기의 리스트
    	for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				if (key[i][j]==1) {
					keycell.add(new int[] {i,j});
				}
			}
		}
        answer = false;
    	checking();
    	if (answer) {
	        return answer;
		}
    	rotate();
    	checking();
    	if (answer) {
	        return answer;
		}
    	rotate();
    	checking();
    	if (answer) {
	        return answer;
		}
    	rotate();
    	checking();
        return answer;
    }
	private void rotate() {
		int size = keycell.size();
		for (int i = 0; i < size; i++) {
			int[] cell = keycell.poll();
			keycell.add(new int[] {M-1-cell[1], cell[0]});
		}
	}
	private void checking() {
		for (int i = 1-M; i < N; i++) {
			for (int j = 1-M; j < N; j++) {
				boolean checkin = true;
				int correct = 0;
				for (int[] cell : keycell) {
					int x = i+cell[0];
					int y = j+cell[1];
					if (x>=N || x<0 || y>=N || y<0) {
						continue;
					}
					if (keycheck[x][y]) {
						checkin=false;
						break;
					}
					correct++;
				}
				if (checkin && correct==count) {
					answer = true;
					return;
				}
			}
		}
	}
}