import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Baekjun1405 {
	private static double[] moveP;
	private static int[] moveXY;
	private static Set<Integer> moving;
	private static double totalP=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		moveP = new double[4];
		moveP[0] = Double.parseDouble(st.nextToken())/100;
		moveP[1] = Double.parseDouble(st.nextToken())/100;
		moveP[2] = Double.parseDouble(st.nextToken())/100;
		moveP[3] = Double.parseDouble(st.nextToken())/100;
		int xaxis = 20; // x축 이동을 나타내줄 값
		moveXY = new int[] {xaxis,-xaxis,1,-1}; // 사방이동
		
		moving = new HashSet<>(); // 딱히 특수기능이 필요하지 않으므로 아무거나
		moving.add(0); // 시작지점 좌표 추가
		crazy(N,0,1);
		
		System.out.println(totalP);
	}

	private static void crazy(int moveC, int now, double p) {
		if (moveC==0) { // 끝까지 이동이 성공헀다는 것은 경로가 단순하다는 뜻! 확률을 더하자
			totalP += p;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int newnow = now + moveXY[i];
			if(moving.add(newnow)) { // 일단 집어넣는다. 그런데 중복이면 add가 false가 뜨므로 if문이 작동하지 않는다.
				crazy(moveC-1,newnow,p*moveP[i]); // 이동횟수소모, 현재좌표이동, 확률갱신
				moving.remove(newnow); // set을 원래 상태로 돌려줘야한다.
			} // 중복이면 추가되지 않으므로 else의 경우 remove할 필요 없다
		}
	}
}
