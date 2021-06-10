import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baekjun1068 {
	private static ArrayDeque<Integer>[] sons;
	private static int[] downleaf;
	private static int cut;
	private static int[] parent;
	private static boolean onlycut;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parent = new int[N];
		sons = new ArrayDeque[N];
		for (int i = 0; i < sons.length; i++) {
			sons[i]=new ArrayDeque<>();
		}
		downleaf = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int root = -1;
		for (int i = 0; i < N; i++) {
			parent[i] = Integer.parseInt(st.nextToken());
			if (parent[i]==-1) {
				root=i;
				continue;
			}
			sons[parent[i]].add(i);
		}
		cut = Integer.parseInt(br.readLine());
		onlycut=false;
		checkleaf(root);
		if (onlycut) {
			System.out.println(downleaf[root]-downleaf[cut]+1);
		} else {
			System.out.println(downleaf[root]-downleaf[cut]);
		}
	}

	private static void checkleaf(int n) {
		if (sons[n].isEmpty()) {
			downleaf[n]=1;
			return;
		}
		if (n==parent[cut] && sons[parent[cut]].size()==1) {
			onlycut=true;
		}
		while(!sons[n].isEmpty()) {
			int son = sons[n].poll();
			checkleaf(son);
			downleaf[n] += downleaf[son];
		}
	}
}
