import java.util.Scanner;
//가장멍청한 방식으로 풀어본 것
public class BaekJun1935trash {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int operand = sc.nextInt();
		int[] operandElement = new int[operand];
		String Postfix = sc.next();
		for (int i = 0; i < operand; i++) {
			operandElement[i] = sc.nextInt();
		}
		String[] PostfixElement = Postfix.split("");
		int index = -1;
		double[] calculator = new double[PostfixElement.length];
		for (int i = 0; i < PostfixElement.length; i++) {
			switch (PostfixElement[i]) { // sorry for dirty code
			case "A":
				calculator[++index] = operandElement[0];
				break;
			case "B":
				calculator[++index] = operandElement[1];
				break;
			case "C":
				calculator[++index] = operandElement[2];
				break;
			case "D":
				calculator[++index] = operandElement[3];
				break;
			case "E":
				calculator[++index] = operandElement[4];
				break;
			case "F":
				calculator[++index] = operandElement[5];
				break;
			case "G":
				calculator[++index] = operandElement[6];
				break;
			case "H":
				calculator[++index] = operandElement[7];
				break;
			case "I":
				calculator[++index] = operandElement[8];
				break;
			case "J":
				calculator[++index] = operandElement[9];
				break;
			case "K":
				calculator[++index] = operandElement[10];
				break;
			case "L":
				calculator[++index] = operandElement[11];
				break;
			case "M":
				calculator[++index] = operandElement[12];
				break;
			case "N":
				calculator[++index] = operandElement[13];
				break;
			case "O":
				calculator[++index] = operandElement[14];
				break;
			case "P":
				calculator[++index] = operandElement[15];
				break;
			case "Q":
				calculator[++index] = operandElement[16];
				break;
			case "R":
				calculator[++index] = operandElement[17];
				break;
			case "S":
				calculator[++index] = operandElement[18];
				break;
			case "T":
				calculator[++index] = operandElement[19];
				break;
			case "U":
				calculator[++index] = operandElement[20];
				break;
			case "V":
				calculator[++index] = operandElement[21];
				break;
			case "W":
				calculator[++index] = operandElement[22];
				break;
			case "X":
				calculator[++index] = operandElement[23];
				break;
			case "Y":
				calculator[++index] = operandElement[24];
				break;
			case "Z":
				calculator[++index] = operandElement[25];
				break;
			case "+":
				calculator[--index] = calculator[index]+calculator[index+1];
				break;
			case "-":
				calculator[--index] = calculator[index]-calculator[index+1];
				break;
			case "*":
				calculator[--index] = calculator[index]*calculator[index+1];
				break;
			case "/":
				calculator[--index] = calculator[index]/calculator[index+1];
				break;
			default:
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