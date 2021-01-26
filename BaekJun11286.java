import java.io.*;
public class BaekJun11286 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] que = new int[N]; // 절대값 큐로 sorting할 것이다
		int index=0;
		int input, tmp, now;
		for (int i = 0; i < N; i++) {
			input = Integer.parseInt(br.readLine());
			if (input==0) { // 0이 들어오면 2번 행동을 시행
				if (index==0) { // 안에 들어있는게 없으니 0을 출력
					System.out.println(0);
				} else { // 들어있는게 있으니 가장 우선순위가 높은 첫번쨰 요소를 제거
					System.out.println(que[0]);
					que[0] = que[--index]; // 큐 delete 매커니즘 : 마지막 값을 맨 앞으로 가져와서 위치 재배열
					now = 0;
					while (true) {
						if (now*2>=index) { // 가지가 최외각 : sorting 종료
							break;
						}else if (now*2+1==index) { // 가지가 아래로 하나
							if (Math.abs(que[now*2+1])>Math.abs(que[now])) { // 아래 가지가 절대값 큼 : sorting 종료
								break;
							} else if (Math.abs(que[now*2+1])==Math.abs(que[now])) {
								if (que[now*2+1]<que[now]) { // 아래 가지가 음수고 내가 양수 : 바꾸고 마저 진행
									tmp=que[now];
									que[now]=que[now*2+1];
									que[now*2+1]=tmp;
									now = now*2+1;
								} else { // 아래 가지가 양수고 내가 음수 : sorting 종료
									break;
								}
							} else { // 아래 가지가 절대값 작음 : 바꾸고 마저 진행
								tmp=que[now];
								que[now]=que[now*2+1];
								que[now*2+1]=tmp;
								now = now*2+1;
							}
						} else { // 아래가지가 둘일 경우 둘중 우선도가 높은 것에 대해 위와 동일
							if (Math.abs(que[now*2+1])<Math.abs(que[now*2+2]) || (Math.abs(que[now*2+1])==Math.abs(que[now*2+2])&&que[now*2+1]<que[now*2+2])) {
								if (Math.abs(que[now*2+1])>Math.abs(que[now])) {
									break;
								} else if (Math.abs(que[now*2+1])==Math.abs(que[now])) {
									if (que[now*2+1]<que[now]) {
										tmp=que[now];
										que[now]=que[now*2+1];
										que[now*2+1]=tmp;
										now = now*2+1;
									} else {
										break;
									}
								} else {
									tmp=que[now];
									que[now]=que[now*2+1];
									que[now*2+1]=tmp;
									now = now*2+1;
								}
							} else {
								if (Math.abs(que[now*2+2])>Math.abs(que[now])) {
									break;
								} else if (Math.abs(que[now*2+2])==Math.abs(que[now])) {
									if (que[now*2+2]<que[now]) {
										tmp=que[now];
										que[now]=que[now*2+2];
										que[now*2+2]=tmp;
										now = now*2+2;
									} else {
										break;
									}
								} else {
									tmp=que[now];
									que[now]=que[now*2+2];
									que[now*2+2]=tmp;
									now = now*2+2;
								}
							}
						}
					}
				}
			} else { // 큐 add 매커니즘 : 맨 뒤에 넣고 우선순위 맞게 위로 끌어올리기
				que[index]=input;
				now = index++;
				while(true) {
					if (now==0) { // 맨앞까지 옴 : sorting 종료
						break;
					} else { // 위쪽 가지와 우선순위 비교, 위와 동일한 순서
						if (Math.abs(que[(now-1)/2])>Math.abs(que[now])) { 
							tmp=que[now];
							que[now]=que[(now-1)/2];
							que[(now-1)/2]=tmp;
							now = (now-1)/2;
						} else if (Math.abs(que[(now-1)/2])==Math.abs(que[now])) {
							if (que[(now-1)/2]>que[now]) {
								tmp=que[now];
								que[now]=que[(now-1)/2];
								que[(now-1)/2]=tmp;
								now = (now-1)/2;
							} else {
								break;
							}
						} else {
							break;
						}
					}
				}
			}
		}
	}
}
