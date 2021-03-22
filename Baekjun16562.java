import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjun16562 {
	private static int[] what;
	private static int[] pay;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		pay = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pay[i] = Integer.parseInt(st.nextToken());
		}
		what = new int[N];
		for (int i = 0; i < N; i++) {
			what[i]=i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			union(a,b);
		}
		//parent가 자기가 아닌 녀석은 최저가가 아니므로 친구의 친구로 사귄다.
		int wholecost = 0;
		for (int i = 0; i < N; i++) {
			if (find(i)==i) {
				wholecost += pay[i];
			}
		}
		if (wholecost>k) {
			System.out.println("Oh no");
		} else {
			System.out.println(wholecost);
		}
		for (int i = 0; i < N; i++) {
		}
	}

	//유니온 파인드 알고리즘
	private static void union(int a, int b) {
		if (find(a)==find(b)) {
			return;
		}
		// 그냥 작은index가 아니라 가격이 적은쪽을 parent로 한다
		if (pay[find(a)]>pay[find(b)]) { 
			what[find(a)]=find(b);
		}else {
			what[find(b)]=find(a);
		}
	}
	private static int find(int a) {
		if (a==what[a]) {
			return a;
		}
		return what[a] = find(what[a]);
	}
}
