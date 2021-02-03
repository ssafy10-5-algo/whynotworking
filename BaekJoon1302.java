package study0121;

import java.util.Scanner;
public class BaekJoon1302 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String book;
		int N = sc.nextInt(); // N �޴°�
		String[] name = new String[N];
		int[] count = new int[N];
		for (int i = 0; i < N; i++) {
			book = sc.next(); // å�̸� �޴°�
			for (int j = 0; j < i+1; j++) {
				if (count[j]==0) {
					name[j]=book;
					count[j]++;
					break;//�����߰�
				} else if (name[j].equals(book)) {
					count[j]++;
					break;//�������߰�
				}
			}
		}
		int big=0;
		String bigbook=name[0];
		for (int i = 1; i < N; i++) {
			if (count[i]==0) { //Ȯ�� �������� ����
				break;
			} else if (count[i]>count[big]) { //������ �׳� �ٲ�
				big=i;
				bigbook=name[i];
			} else if ((count[i]==count[big])&&(bigbook.compareTo(name[i])>0)) { //������ ����������
				big=i;
				bigbook=name[i];
			}
		}
		System.out.println(bigbook);
		sc.close();
	}//�����ϴ°ſ�
}