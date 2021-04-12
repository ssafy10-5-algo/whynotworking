import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjun1043 {
	private static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] party = new int[M][];
		st = new StringTokenizer(br.readLine());
		// parent 어레이, 1~N은 사람이고 0은 진실, parent가 0으로 연결된다는 것은 진실에 닿아있다는 것.
		parent = new int[N+1]; 
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		//진실에 닿은 사람들
		int truth = Integer.parseInt(st.nextToken());
		for (int i = 0; i < truth; i++) {
			int T = Integer.parseInt(st.nextToken());
			parent[T]=0;
		}
		//진실에 닿은 사람들을 정리한 뒤 거짓말 해도 되는 파티 체크때 또 써야하니까 저장해둔다
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			party[i]=new int[n];
			for (int j = 0; j < n; j++) {
				party[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//진실이 퍼진다
		for (int i = 0; i < M; i++) {
			for (int j = 1; j < party[i].length; j++) {
				union(party[i][0],party[i][j]);
			}
		}
		//거짓말 해도 되는 파티찾기
		int lie = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < party[i].length; j++) {
				if (find(party[i][j])==0) {
					break;
				}
				if (j==party[i].length-1) {
					lie++;
				}
			}
		}
		System.out.println(lie);
	}

	/**파인드*/
	private static int find(int i) {
		if (parent[i]==i) {
			return i;
		}
		return parent[i]=find(parent[i]);
	}

	/**유니온*/
	private static void union(int i, int j) {
		if (find(i)==find(j)) {
			return;
		} else if (find(i)>find(j)) {
			parent[find(i)]=find(j);
			return;
		}
		parent[find(j)]=find(i);
	}
}
