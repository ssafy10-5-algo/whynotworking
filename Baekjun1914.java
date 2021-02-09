import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjun1914 {
	private static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] a = bigpow2(n);
		boolean c = false;
		for (int i = 0; i < 5; i++) {
			if(c) { // 앞자리수가 있으므로 빈자리에 0 넣어서 출력
				System.out.printf("%07d",a[i]);
			}else if(a[i]>0) { // 첫자리가 포함된 7자리
				System.out.print(a[i]);
				c = true;
			}
		}
		
		if (n<=20) { // 20 이하에서만 하노이 진행
			sb = new StringBuilder();
			hanoi(n,1,3);
			System.out.print(sb);
		}
		
	}
	private static void hanoi(int n, int i, int j) { // 하노이진행. EZ
		if(n==1) {
			sb.append("\n").append(i).append(" ").append(j);
			return;
		}
		int a = 6-i-j;
		hanoi(n-1,i,a);
		sb.append("\n").append(i).append(" ").append(j);
		hanoi(n-1,a,j);
		return;
	}
	private static int[] bigpow2(int n) { // 2^n 계산결과. 10^30승으로 나오므로 long의 범위도 벗어나므로 7자리씩 잘라서 진행했다.
		int[] a = new int[5];
		a[4] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 5; j++) {
				a[j] = 2*a[j];
				if (a[j]>=10000000) {
					a[j-1]+= a[j]/10000000;
					a[j]=a[j]%10000000;
				}
			}
		}
		a[4]--;
		return a;
	}
}
