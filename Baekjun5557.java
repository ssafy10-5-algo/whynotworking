import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjun5557 {
	private static long[] arr; // 개수 무진장 많으니 long형
	private static int T;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		arr = new long[21]; // 0~20까지의 수가 나오는 경우의 수
		arr[K]=1;
		//DP 진행
		for (int i = 0; i < N-2; i++) {
			check(Integer.parseInt(st.nextToken()));
		}
		T = Integer.parseInt(st.nextToken());
		System.out.println(arr[T]);
	}

	/**DP 함수*/
	private static void check(int su) {
		long[] arr2 = new long[21];
		//중간에 0~20의 범위를 벗어나면 배제하므로 범위 내의 경우만 고려한다.
		for (int i = 0; i < 21-su; i++) {
			arr2[i] = arr[i+su];
		}
		for (int i = su; i < 21; i++) {
			arr2[i] += arr[i-su];
		}
		arr = arr2;
	}
}
