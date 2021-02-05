import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjun17213 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int r = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		// 문제 : (n-r)H(r) = (n-1)C(r-1)
		System.out.println(combination(n-1,r-1));
	}

	private static int combination(int i, int j) {
		if(j==0 || i==j) {
			return 1;
		}
		return i*combination(i-1,j-1)/j;
	}
//	private static int combination(int i, int j) {
//		if(j==0 || i==j) {
//			return 1;
//		}
//		return combination(i-1,j-1)+combination(i-1,j);
//	}
}
