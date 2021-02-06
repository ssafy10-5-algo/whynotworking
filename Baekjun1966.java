import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjun1966 {
	static int[] amount = new int[10];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			Queue<int[]> que = new ArrayDeque<>();
			int num = Integer.parseInt(st.nextToken());
			int[] imps = new int[10]; // 우선도별 문서 개수
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) { // 큐에 넣는다
				int[] a = {Integer.parseInt(st.nextToken()),0};
				if(i==num) a[1]=1; // 몇번째로 출력되는지를 알고 싶은 문서를 체크한다
				que.add(a);
				imps[a[0]]++;
			}
			
			int top=0;
			for (int i = 9; i > 0; i--) { // 가장 높은 우선도가 몇인지 확인한다
				if(imps[i]>0) {
					top=i;
					break;
				}
			}
			
			int count = 0;
			while(true) {
				int[] a = que.poll();
				if (a[0]==top) { // 우선도 높은 문서 출력 (다시 집어넣지 않음)
					count++; // 출력된 문서 개수 증가
					if(a[1]>0) { // 목표문서가 출력되었으므로 탈출
						break;
					}
					imps[top]--; // 우선도별 문서개수 갱신
					for (int i = 9; i >0; i--) {
						if(imps[i]>0) {
							top=i;
							break;
						}
					}
				} else {
					que.add(a); // 우선도 낮은 문서 다시 맨 뒤에 집어넣음
				}
			}
			sb.append(count).append("\n");
		}
	System.out.println(sb);
	}
}
