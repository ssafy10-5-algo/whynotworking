import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjun20440 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> mIn = new PriorityQueue<Integer>(); // 모기 입장시각 PQ
		PriorityQueue<Integer> mOut = new PriorityQueue<Integer>(); // 모기 퇴장시각 PQ
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			mIn.add(Integer.parseInt(st.nextToken()));
			mOut.add(Integer.parseInt(st.nextToken()));
		}
		int in = mIn.poll(); // 다음 모기가 들어오는 시간 변수
		int out = mOut.poll(); // 다음 모기가 나가는 시간 변수
		int[] max = new int[3]; // 모기수가 최대인 시간대
		max[0]=in;
		max[1]=out;
		max[2]=1;
		int T_Em = in; // 현재 기간 시작점
		int T_Xm = out; // 현재기간 마지막점
		int mAmount = 1; // 현재 모기 수
		while(true) {
			if (in==T_Em) { // 모기가 입장했으니 다음에 입장할 모기를 준비하는 if문
				if (mIn.isEmpty()) {
					break;
				}
				in = mIn.poll();
			}
			if (in==out) { // 모기가 바톤터치하면 마리수 변화는 없으므로 기간만 변경
				out = mOut.poll();
				T_Xm=out;
				if (max[0]==T_Em) {
					max[1]=out;
				}
				if (mIn.isEmpty()) {
					break;
				}
				in = mIn.poll();
				continue;
			}
			if (in>out) { // 모기 나가는게 먼저일 때
				out = mOut.poll();
				mAmount--;
				T_Xm=out;
				continue;
			}
			//모기 들어오는게 먼저일 때
			T_Em = in;
			mAmount++;
			if (max[2]<mAmount) { // 최대값 변경되는지 확인
				max[0]=T_Em;
				max[1]=T_Xm;
				max[2]=mAmount;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(max[2]).append("\n").append(max[0]).append(" ").append(max[1]);
		System.out.println(sb);
	}
}
