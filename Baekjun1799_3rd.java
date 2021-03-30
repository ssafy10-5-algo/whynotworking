import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baekjun1799_3rd {
	private static int N;
	private static int max_odd;
	private static int max_even;
	private static ArrayDeque<int[]> bishop;
	private static ArrayDeque<int[]>[] Ques;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		//Ques는 대각선 줄에 따른 공간을 받아오는 어레이디큐이다.
		Ques = new ArrayDeque[2*N-1];
		for (int i = 0; i < 2*N-1; i++) {
			Ques[i] = new ArrayDeque<int[]>();
		}
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (st.nextToken().equals("1")) {
					Ques[i+j].add(new int[] {i,j});
				}
			}
		}
    	
    	bishop = new ArrayDeque<int[]>();
    	//검은 칸
    	max_even = 0;
    	bishop_even(0,0);
		//흰 칸
    	max_odd = 0;
    	bishop_odd(0,0);
		System.out.println(max_odd + max_even);
	}

	private static void bishop_odd(int bi, int li) {
		if (li==N-1) {
			if (max_odd<bi) {
				max_odd=bi;
			}
			return;
		}
		
		for (int[] cell : Ques[li*2+1]) {
			if (check(bi,cell)) {
				bishop.add(cell);
				bishop_odd(bi+1,li+1);
				bishop.remove(cell);
			}
		}
		bishop_odd(bi,li+1);
	}

	private static void bishop_even(int bi, int li) {
		if (li==N) {
			if (max_even<bi) {
				max_even=bi;
			}
			return;
		}
		
		for (int[] cell : Ques[li*2]) {
			if (check(bi,cell)) {
				bishop.add(cell);
				bishop_even(bi+1,li+1);
				bishop.remove(cell);
			}
		}
		bishop_even(bi,li+1);
	}
	
	/**n개의 비숍이 놓여있을 때 이 자리에 비숍을 놓을 수 있는지를 판단하는 매서드*/
	private static boolean check(int n, int[] place) {
		for (int i = 0; i < n; i++) {
			int[] abishop = bishop.poll();
//			System.out.println("("+place[0]+","+place[1]+" 비교 " +abishop[0]+","+abishop[1]+")");
			int dx = abishop[0]-place[0];
			int dy = abishop[1]-place[1];
			bishop.add(abishop);
			if (dx==dy || dx==-dy) {
				return false;
			}
		}
		return true;
	}
}
