import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjun1074 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int order = 0;
		for (int i = 0; i < N; i++) {
			order+=(c%2*2+r%2)*(int)Math.pow(4, i);
			c=c/2;
			r=r/2;
			if(c==0&&r==0) break;
		}
		System.out.println(order);
	}
}
