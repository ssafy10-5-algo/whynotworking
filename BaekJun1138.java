import java.util.Scanner;

public class BaekJun1138 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] jul = new int[N];
		for (int i = 1; i <= N; i++) {
			int a = sc.nextInt();
			for (int j = 0; j < N; j++) {
				if (jul[j]==0) { // 빈자리 발견
					if (a==0) { // 이자리는 내자리다
						jul[j]=i;
						break;
					} else { // 이 자리는 나보다 더 큰 사람을 위한 자리다
						a--;
					}
				} 
			}
		}
		sc.close();
		for (int i = 0; i < N; i++) {
			System.out.print(jul[i]+" ");
		}	
	}
}
