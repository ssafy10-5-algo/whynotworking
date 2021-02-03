package study0121;

import java.util.Scanner;
public class BaekJoon1302 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String book;
		int N = sc.nextInt(); // N 받는곳
		String[] name = new String[N];
		int[] count = new int[N];
		for (int i = 0; i < N; i++) {
			book = sc.next(); // 책이름 받는곳
			for (int j = 0; j < i+1; j++) {
				if (count[j]==0) {
					name[j]=book;
					count[j]++;
					break;//새거추가
				} else if (name[j].equals(book)) {
					count[j]++;
					break;//같은거추가
				}
			}
		}
		int big=0;
		String bigbook=name[0];
		for (int i = 1; i < N; i++) {
			if (count[i]==0) { //확인 다했으니 나감
				break;
			} else if (count[i]>count[big]) { //많으면 그냥 바꿈
				big=i;
				bigbook=name[i];
			} else if ((count[i]==count[big])&&(bigbook.compareTo(name[i])>0)) { //같으면 사전순으로
				big=i;
				bigbook=name[i];
			}
		}
		System.out.println(bigbook);
		sc.close();
	}//어케하는거여
}