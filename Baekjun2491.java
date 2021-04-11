import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjun2491 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		int lastsu =  s.charAt(0)-'0';
		int now = 1; // 현재 오름/내림차순 숫자열의 길이
		int max = 1;
		boolean updown = true;
		int same = 1; // 이번 숫자까지 연속된 같은숫자의 개수
		for (int i = 1,index = 2; i < N; i++, index +=2) {
			int su = s.charAt(index)-'0';
			if (su==lastsu) { // 이전 숫자와 같을 경우 길이 +1, 같은숫자개수 +1
				now++;
				same++;
			} else if(su>lastsu){ // 이전숫자보다 클 경우
				if (updown) { // 오름차순이었으면 그냥 개수 중가
					now++;
				} else { // 내림차순중이었으면 오름차순으로 변경, 개수 갱신
					updown = true;
					if (now>max) {
						max = now;
					}
					now = same+1;
				}
				same=1; // 다른숫자 출현
			} else {
				if (updown) { // 오름차순중이었으면 내림차순으로 변경, 개수 갱신
					updown = false;
					if (now>max) {
						max = now;
					}
					now = same+1;
				} else { // 내림차순이었으면 그냥 개수 증가
					now++;
				}
				same=1;// 다른숫자 출현
			}
			lastsu = su;
		}
		if (now>max) { // 오름/내림차순 변경시에만 갱신해주었으므로 마지막 숫자열이 갱신되지 않아있음.
			max = now;
		} // 요거땜에 한참 헤멨다... ㅠ
		System.out.println(max);
	}
}
