import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BaekJun1918 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayDeque<String> sik = new ArrayDeque<>();
		String s = br.readLine();
		for (int i = 0; i < s.length(); i++) {
			sik.add(Character.toString(s.charAt(i)));
		}
		System.out.println(huwi(sik));
	}
	
	private static String huwi(ArrayDeque<String> sik) {
		ArrayDeque<String> imsi = new ArrayDeque<>();
		boolean chk = true;
		while(chk&&(!sik.isEmpty())) { // 우선사항 처리부
			String check = sik.poll();
			switch (check) {
			case "(": // 괄호 내부는 또다시 그들만의 우선도가 적용되므로 재귀로 해결
				imsi.add(huwi(sik));
				break;
			case "*": case"/": // 우선도 높은 연산 우선
				StringBuilder sb = new StringBuilder();
				String a = sik.poll();
				if(a.charAt(0)=='(') { // 다음 요소를 빼오므로 괄호연산 존재여부 확인해야함
					a=huwi(sik);
				}
				sb.append(imsi.pollLast()).append(a).append(check);
				imsi.add(sb.toString());
				break;
			case ")" : // 재귀를 탈출하는 파트이므로 그냥 while문을 마칠 수 있으면 충분
				chk=false;
				break;
			default: // 수 or 괄호연산이 완료된 식 및 +-연산자 일단 통과시킴
				imsi.add(check);
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!imsi.isEmpty()) { // 우선도 높은 것을 이미 처리했기에 순서대로 처리하기만 하면 되는 파트
			String check = imsi.poll();
			switch (check) {
			case "+": case "-":
				sb.append(imsi.poll()).append(check);
				break;
			default:
				sb.append(check);
				break;
			}
		}
		return sb.toString();
	}
	
}
