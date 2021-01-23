import java.util.Scanner;
// 가장멍청한 방식으로 풀어본 것
public class BaekJun1935 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int operand = sc.nextInt();
		int[] operandElement = new int[operand];
		String Postfix = sc.next();
		for (int i = 0; i < operand; i++) {
			operandElement[i] = sc.nextInt();
		}
		int index = -1;
		int checksum;
		double[] calculator = new double[Postfix.length()];
		for (int i = 0; i < Postfix.length(); i++) {
			checksum = (int)Postfix.charAt(i);
			switch (checksum) { // sorry for dirty code
			case 43: // +
				calculator[--index] = calculator[index]+calculator[index+1];
				break;
			case 45: // -
				calculator[--index] = calculator[index]-calculator[index+1];
				break;
			case 42: // *
				calculator[--index] = calculator[index]*calculator[index+1];
				break;
			case 47: // /
				calculator[--index] = calculator[index]/calculator[index+1];
				break;
			default: // A = 66
				calculator[++index] = operandElement[checksum-65];
				break;
			}
		}
		if (index!=0) {
			System.out.println("계산식에 문제가 있습니다.");
		} else {
			System.out.printf("%.2f",calculator[index]);
		}
	}
}