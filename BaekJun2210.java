import java.util.*;

public class BaekJun2210 {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>(); // set은 같은수가 들어가지 않는다
		
		Scanner sc = new Scanner(System.in);
		int[][] digits = new int[5][5];
//		for (int i = 0; i < 25; i++) {
//			digits[i/5][i%5] = sc.nextInt();
//		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				digits[i][j] = sc.nextInt();
			}
		}
		sc.close();
		int size6su=0;
		int ii, jj;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) { // i,j에서 시작하는 모든수
				for (int k1 = 0; k1 < 4; k1++) { // 0,1,2,3 이 각 방향
ex1:				for (int k2 = 0; k2 < 4; k2++) {
ex2:					for (int k3 = 0; k3 < 4; k3++) {
ex3:						for (int k4 = 0; k4 < 4; k4++) {
ex4:							for (int k5 = 0; k5 < 4; k5++) {
									//i,j에서 k1,k2,k3,k4,k5방향으로 순서대로 움직여 만들어지는 6자리수
									ii = i;
									jj = j;
									size6su=digits[ii][jj]*100000;
									
									//이하에서 k1,k2,k3,k4방향으로 움직이며 영역을 벗어날 경우 count할 필요 없으므로
									//해당 방향이 벗어난 지점을 탈출해야 하므로 break ex;를 사용함
									switch (k1) {
									case 0:
										if (ii<=0) break ex1;
										ii--;
										break;
									case 1:
										if (jj<=0) break ex1;
										jj--;
										break;
									case 2:
										if (ii>3) break ex1;
										ii++;
										break;
									default:
										if (jj>3) break ex1;
										jj++;
										break;
									}
									size6su+=digits[ii][jj]*10000;
									
									switch (k2) {
									case 0:
										if (ii<=0) break ex2;
										ii--;
										break;
									case 1:
										if (jj<=0) break ex2;
										jj--;
										break;
									case 2:
										if (ii>3) break ex2;
										ii++;
										break;
									default:
										if (jj>3) break ex2;
										jj++;
										break;
									}
									size6su+=digits[ii][jj]*1000;
									
									switch (k3) {
									case 0:
										if (ii<=0) break ex3;
										ii--;
										break;
									case 1:
										if (jj<=0) break ex3;
										jj--;
										break;
									case 2:
										if (ii>3) break ex3;
										ii++;
										break;
									default:
										if (jj>3) break ex3;
										jj++;
										break;
									}
									size6su+=digits[ii][jj]*100;
									
									switch (k4) {
									case 0:
										if (ii<=0) break ex4;
										ii--;
										break;
									case 1:
										if (jj<=0) break ex4;
										jj--;
										break;
									case 2:
										if (ii>3) break ex4;
										ii++;
										break;
									default:
										if (jj>3) break ex4;
										jj++;
										break;
									}
									size6su+=digits[ii][jj]*10;
									
									//k5의 경우 현재 for문을 다시 시행해야 하므로 break ex;로 탈출이 불가능하다
									//그러므로 영역 내일 경우에만 set에 저장하게 하여 해결
									switch (k5) {
									case 0:
										if (ii>0) {
											size6su+=digits[ii-1][jj];
											set.add(size6su);
										}
										break;
									case 1:
										if (jj>0) {
											size6su+=digits[ii][jj-1];
											set.add(size6su);
										}
										break;
									case 2:
										if (ii<4) {
											size6su+=digits[ii+1][jj];
											set.add(size6su);
										}
										break;
									default:
										if (jj<4) {
											size6su+=digits[ii][jj+1];
											set.add(size6su);
										}
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println(set.size()); // set의 크기 = 서로다른 수의 개수
	}
}
