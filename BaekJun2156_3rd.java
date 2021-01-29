import java.util.Scanner;

public class BaekJun2156_3rd {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] max = new int[N];
		int[] array = new int[N];
		int maxNo;
		for (int i = 0; i < N; i++) {
			array[i] = sc.nextInt();
		}
		max[0] = array[0]; // 한잔있으면 마시고
		if (N==1) { // Out of Range 방지
			System.out.println(max[0]);
			System.exit(0);
		}
		max[1] = array[0]+array[1]; // 두잔도 마심
		if (N==2) { // Out of Range 방지
			System.out.println(max[1]);
			System.exit(0);
		}
		max[2] = comparethree(array[0]+array[1],array[0]+array[2],array[1]+array[2]);
		if (N==3) { // Out of Range 방지
			System.out.println(max[2]);
			System.exit(0);
		}
		max[3] = comparethree(array[0]+array[1]+array[3],array[0]+array[2]+array[3],array[1]+array[2]);
		for (int i = 4; i < N; i++) {
//			안마셨을 경우 가능성 : XOOX / XOX / XX
			maxNo = comparethree(max[i-4]+array[i-2]+array[i-1],max[i-3]+array[i-1],max[i-2]);
//			마셨을 경우 가능성 : XOO / XO
			max[i] = comparethree(maxNo,max[i-3]+array[i-1]+array[i],max[i-2]+array[i]);
		}
		System.out.println(max[N-1]);
		sc.close();
	}
	public static int comparethree(int a1, int a2, int a3) {
		if (a2 <=a1 && a3 <= a1) {
			return a1;
		} else if (a3 <=a2){
			return a2;
		} else {
			return a3;
		}
	}

}
