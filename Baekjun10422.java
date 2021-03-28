import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baekjun10422 {
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			int L = Integer.parseInt(br.readLine());
			System.out.println(bracket(L));
		}
	}

	private static long bracket(int length) {
		long[] whole = new long[length+1]; // 전체
		long[] one = new long[length+1]; // 첫괄호가 마지막괄호로 닫힘
		long[] none = new long[length+1]; // 그 외
		if (length%2==1) {
			return 0;
		}
		one[2]=1;
		whole[2]=1;

		for (int i = 4; i <= length; i+=2) {
			one[i]=whole[i-2]; //i-2개 모든경우에 괄호씌움
			for (int j = 2; j < i; j+=2) { // 첫괄호의 길이에 따라 세기
				none[i]+= whole[i-j]*one[j]%1000000007;
			}
			whole[i] = (one[i] + none[i])%1000000007;
		}
		return whole[length];
	}
}
