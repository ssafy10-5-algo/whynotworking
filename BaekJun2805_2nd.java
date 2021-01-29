import java.util.Scanner;

public class BaekJun2805_2nd {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int m = sc.nextInt();
		int[] woods = new int[N]; 		// 나무길이 목록
		int highest = 0; 				// highest : 가장 높은 나무의 높이
		
		//나무길이 받아오고 highest 갱신
		for (int i = 0; i < N ; i++) {
			woods[i] = sc.nextInt();
			if (highest<woods[i]) {
				highest=woods[i];
			}
		} 
		
		// 이분탐색 시작
		int left = 0;
		int right = highest;
		int mid = highest/2;
		while(left<mid) { // left + 1 = right가 될 때 까지 
			if (cutAll(mid,woods)<m) {
				right = mid; // 일반적인 이분탐색은 right = mid-1을 넣지만 이 문제의 경우 정확한 m값을 가지지 않을 수 있으므로 mid를 넣어야 한다
				mid = (left+right)/2;
			} else { // left + 1 = right일때 mid = left이므로 m값을 가질때도 left를 가져와야 한다. 
				left = mid;
				mid = (left+right)/2;
			}
		}
		System.out.println(left); // m>0이므로 항상 right>답
		
		sc.close();
	}
	
	/** 높이 high로 잘랐을때 얻을 수 있는 나무의 길이 합을 구하는 함수
	 *  int의 크기가 약 20억이므로 최대 10억인 나무 100만개의 합은 int로 감당이 불가능하니 long를 써야한다. */
	public static long cutAll(int high, int[] woods) {
		long meter = 0;
		for (int i = 0; i < woods.length; i++) {
			meter += (woods[i]>high ? woods[i]-high : 0); // 3항연산자로 차가 음수면 0으로
		}
		return meter;
	}
}
