import java.util.Scanner;

public class Baekjun14501 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] salary = new int[N + 1][2];
		int Ti = 0;
		int Pi = 0;
		salary[0][0] = Ti; // 플랜의 잔여 작업 시간
		salary[0][1] = Pi; // 플랜으로 얻는 수익
		int max;
		for (int i = 1; i < N+1; i++) {
			Ti = sc.nextInt();
			Pi = sc.nextInt();
			max=0;
			for (int j = 0; j < i; j++) { // 잔여 작업이 없는 수익이 가장 큰 플랜 search
				if (salary[j][0]==0&&salary[j][1]>max) {
					max = salary[j][1];
				}
			}
			salary[i][0] = Ti;
			salary[i][1] = max+Pi;
			for (int j = 0; j < i+1; j++) { // 하루 경과에 따른 잔여 작업시간 갱신
				if(salary[j][0]>0) salary[j][0]--;
			}
		}
		max=0;
		for (int i = 0; i < N+1; i++) { // 최대수익 search
			if (salary[i][0]==0&&salary[i][1]>max) {
				max = salary[i][1];
			}
		}
		System.out.println(max);
	}
}
