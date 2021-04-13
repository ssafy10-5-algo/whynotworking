import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjun16235 {
	private static int[][] A;
	private static int[][] G;
	private static PriorityQueue<Tree> trees;
	private static ArrayDeque<Tree> trees_alive = new ArrayDeque<>();
	private static ArrayDeque<Tree> trees_dead = new ArrayDeque<>();
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 땅 넓이
		int M = Integer.parseInt(st.nextToken()); // 나무 수
		int K = Integer.parseInt(st.nextToken()); // 년
		A = new int[N][N];
		G = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j]=Integer.parseInt(st.nextToken());
				G[i][j]=5;
			}
		}
		trees = new PriorityQueue<Tree>(); // 어린 나무부터 양분을 먹기위한 우선순위 큐
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			trees.add(new Tree(x,y,age));
		}
		
		
		for (int i = 0; i < K; i++) { // K년이 지난다
			one_year_later();
		}
		
		System.out.println(trees.size());
	}

	private static void one_year_later() { // 사계절이 지난다
		Spring();
		Summer();
		Fall();
		Winter();
	}

	private static void Spring() {
		while(!trees.isEmpty()) { // 모든 나무들이 (어린 나무부터)
			Tree tree = trees.poll();
			if (G[tree.getX()][tree.getY()]<tree.getAge()) { // 양분이 부족하면 죽고
				trees_dead.add(tree);
			}else { // 양분이 충분하면 양분을 소모하고 살아남아 한살을 먹는다
				G[tree.getX()][tree.getY()] -= tree.getAge();
				tree.setAge(tree.getAge()+1);
				trees_alive.add(tree);
			}
		}
	}

	private static void Summer() {
		while(!trees_dead.isEmpty()) { // 죽은 나무는 양분이 된다
			Tree tree = trees_dead.poll();
			G[tree.getX()][tree.getY()] += tree.getAge()/2;
		}
	}

	private static void Fall() {
		while(!trees_alive.isEmpty()) {
			Tree tree = trees_alive.poll();
			if (tree.getAge()%5==0) { // 나이가 5의 배수인 나무는 번식을 한다
				int x = tree.getX();
				int y = tree.getY();
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (i==0 && j==0) {
							continue;
						}
						if (x+i>=0 && x+i<N && y+j>=0 && y+j<N) {
							Tree newtree = new Tree(x+i,y+j,1);
							trees.add(newtree);
						}
					}
				}
			}
			trees.add(tree);
		}
	}

	private static void Winter() {
		for (int i = 0; i < N; i++) { // 양분을 공급한다
			for (int j = 0; j < N; j++) {
				G[i][j] += A[i][j];
			}
		}
	}
}

class Tree implements Comparable<Tree>{ // 나무 객체, 우선순위 큐를 위한 comparable은 나이로 비교한다
	private int x;
	private int y;
	private int age;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public Tree(int x, int y, int age) {
		super();
		this.x = x;
		this.y = y;
		this.age = age;
	}

	@Override
	public int compareTo(Tree tree) {
		if (this.age > tree.age) {
			return 1;
		} else if (this.age < tree.age) {
			return -1;
		}
		return 0;
	}
	
	
}