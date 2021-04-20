import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baekjun15681 {
	private static ArrayDeque<Integer>[] connected;
	private static int[] descendant;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		connected = new ArrayDeque[N+1]; // 연결된 노드 목록 역할의 어레이디큐 배열
		for (int i = 0; i <= N; i++) {
			connected[i] = new ArrayDeque<Integer>();
		}
		//연결된 노드 목록 추가
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			connected[u].add(v);
			connected[v].add(u);
		}
		ArrayDeque<Integer> check = new ArrayDeque<>();
		check.add(R);
		boolean[] checking = new boolean[N+1];
		// 각 점과 연결된 노드 목록에서 부모를 제거
		while(!check.isEmpty()) {
			int now = check.poll();
			checking[now]=true;
			int size = connected[now].size();
			for (int i = 0; i < size; i++) {
				int isSon = connected[now].poll();
				if (checking[isSon]) { // 부모노드 제거
					continue;
				}
				connected[now].add(isSon);
				check.add(isSon); // 검토할 노드들 추가
			}
		}
		descendant = new int[N+1]; //모든 점 기준 그 점이 루트인 서브트리에 속한 정점의 개수
		measure(R);
		for (int i = 0; i < Q; i++) {
			System.out.println(descendant[Integer.parseInt(br.readLine())]);
		}
	}

	//모든 점 기준 그 점이 루트인 서브트리에 속한 정점의 개수를 갱신해준다
	private static int measure(int r) {
		int sum=0;
		while(!connected[r].isEmpty()) {
			sum += measure(connected[r].poll());
		}
		return descendant[r]=sum+1;
	}
}
