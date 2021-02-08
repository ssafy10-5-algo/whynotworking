import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjun1992 {
	private static String[][] img;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		img = new String[N][];
		for (int i = 0; i < N; i++) {
			img[i] = br.readLine().split("");
		}
		System.out.println(quadtree(0,0,N));
	}
	/**i,j가 왼쪽위 끝인 크기n 정사각형 배열을 쿼드트리로 압축하는 함수*/
	private static String quadtree(int i, int j, int n) {
		StringBuilder sb = new StringBuilder();
		if (n==2) { // 2*2가 재귀 최소단
			if (img[i][j].equals(img[i+1][j]) && img[i+1][j].equals(img[i][j+1]) && img[i][j+1].equals(img[i+1][j+1])) {
				sb.append(img[i][j]);
			} else {
				sb.append("(").append(img[i][j]).append(img[i][j+1]).append(img[i+1][j]).append(img[i+1][j+1]).append(")");
			}
			return sb.toString();
		}
		
		//크기가 2보다 크면 4등분해서 값을 받아온다.
		String[][] img2 = new String[2][2];
		img2[0][0] = quadtree(i,j,n/2);
		img2[1][0] = quadtree(i+n/2,j,n/2);
		img2[0][1] = quadtree(i,j+n/2,n/2);
		img2[1][1] = quadtree(i+n/2,j+n/2,n/2);
		
		// 네 String이 0이나 1이면서 모두 같아야 합쳐진다....
		if ((img2[0][0].equals("0") || img2[0][0].equals("1")) && img2[0][0].equals(img2[1][0]) && img2[1][0].equals(img2[0][1]) && img2[0][1].equals(img2[1][1])) {
			sb.append(img2[0][0]);
		} else {
			sb.append("(").append(img2[0][0]).append(img2[0][1]).append(img2[1][0]).append(img2[1][1]).append(")");
		}
		
		return sb.toString();
	}
}
