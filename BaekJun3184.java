import java.util.Scanner;

public class BaekJun3184 {
	static int[][] graph;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		int[][] madang = new int[R][C];
		for (int i = 0; i < R; i++) {
			String row = sc.next();
			for (int j = 0; j < C; j++) {
				switch (row.charAt(j)) {
				case '.':
					madang[i][j] = 1;
					break;
				case '#':
					madang[i][j] = 0;
					break;
				case 'o':
					madang[i][j] = 2;
					break;
				default:
					madang[i][j] = 3;
					break;
				}
			}
		}
		sc.close();
		graph = new int[R][C];
		int count = 2; // 0은 울타리, 1은 탈출가능영역, 2부터는 고립된 영역
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (madang[i][j]==0) { // 울타리면 일단 스킵
					graph[i][j] = 0;
				}else if(i*j==0) { // 좌상단 외곽 탈출가능영역
					graph[i][j] = 1;
				}else if(i == R-1) { // 우측 탈출가능영역
					if (graph[i-1][j]>1) {
						unite(graph[i-1][j],1,i,j);
					}
					graph[i][j] = 1;
				}else if(j == C-1) { // 하단 탈출가능영역
					if (graph[i][j-1]>1) {
						unite(graph[i][j-1],1,i,j);
					}
					graph[i][j] = 1;
				}else if(madang[i-1][j]==0 && madang[i][j-1]==0) { // 왼쪽, 위쪽이 둘다 울타리면 새로운 영역
					graph[i][j] = count++;
				}else if(madang[i-1][j]*madang[i][j-1]>0) { // 왼쪽, 위쪽이 둘다 영역이면 두 영역을 합침
					unite(graph[i][j-1],graph[i-1][j],i,j);
					graph[i][j] = graph[i][j-1]<graph[i-1][j] ? graph[i][j-1] : graph[i-1][j];
				}else { // 왼쪽 위쪽중 하나만 영역이면 그 영역에 해당된다
					graph[i][j] = graph[i][j-1]+graph[i-1][j]; // 어차피 둘중하나 0
				}
					
			}
		}
		int[] wolf = new int[count];
		int[] sheep = new int[count];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				switch (madang[i][j]) {
				case 2:
					sheep[graph[i][j]]++;
					break;
				case 3:
					wolf[graph[i][j]]++;
					break;
				default:
					break;
				}
			}
		}
		wolf[0] = 0;
		sheep[0] = 0;
		for (int i = 2; i < count; i++) {
			if (sheep[i]>wolf[i]) { // 양이 늑대를 잡아먹음
				sheep[0] += sheep[i];
			} else { // 늑대가 양을 잡아먹음
				wolf[0] += wolf[i];
			}
		}
		System.out.println(sheep[0]+" "+wolf[0]);
	}
	
	public static void unite(int a, int b, int i, int j) {
		if (a==b) { // 같은영역이면 아무것도 안해도 됨.... 이거 안넣어서 1212ms였네요
			return;
		}
		int c = a>b?b:a;
		int d = a+b-c; // c가 작은수
		//영역 합치는걸 이전 모든 곳에 갱신 d영역이 비겠지만 알바인가
		for (int k = 0; k < i; k++) { 
			for (int k2 = 0; k2 < graph[k].length; k2++) {
				if(graph[k][k2]==d) graph[k][k2]=c;
			}
		}
		for (int k2 = 0; k2 < j; k2++) {
			if(graph[i][k2]==d) graph[i][k2]=c;
		}
	}
}

