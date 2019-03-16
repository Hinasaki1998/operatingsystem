package exp;

import java.util.*;

class ReplacementPolicy {
	int DataShow[][]; // 用于存储要显示的数组
	boolean DataShowEnable[][]; // 用于存储数组中的数据是否需要显示
	int Data[]; // 保存数据
	int Block[]; // 物理块
	int count[]; // 计数器
	int N; // 页面个数
	int M;// 最小物理块数
	int ChangeTimes;

	// 4,3,2,1,4,3,5,4,3,2,1,5,6,2,3,7,1,2,6,1
	public void dispaly() {
		Scanner scan = new Scanner(System.in);
		int choice;
		do {
			/* 菜单 */
			System.out.println("====================");
			System.out.println("\t菜单");
			System.out.println("====================");
			System.out.println("\t1.LRU");
			System.out.println("\t2.EXIT");

			/* 输入选择 */
			do {
				System.out.println("输入选择功能: ");
				while (!scan.hasNextInt()) {
					System.out.println("您只能输入1和2");
					System.out.println("输入选择功能e: ");
					scan.next();
				}
				choice = scan.nextInt();
				if (choice != 1 && choice != 2) {
					System.out.println("您只能输入1和2.");
				}
			} while (choice != 1 && choice != 2);

			/* 输入2是退出 */
			if (choice == 2) {
				System.out.println("*****************************");
				System.out.println("            退出                           ");
				System.out.println("*****************************");
				break;
			}
			/* 输入页面个数 */
			do {
				System.out.println("输入页面个数: ");
				while (!scan.hasNextInt()) {
					System.out.println("请输入整数.");
					scan.next();
				}
				N = scan.nextInt();
				if (N <= 0) {
					System.out.println("请输入一个正确整数.");
				}

			} while (N <= 0);

			Data = new int[N];

			/* 输入序列 */
			System.out.println("输入访问序列 (数字之间逗号隔开): ");
			do {
				String inputString = scan.next().toString();
				String stringArray[] = inputString.split(",");
				Data = new int[stringArray.length];
				for (int i = 0; i < stringArray.length; i++) {
					Data[i] = Integer.parseInt(stringArray[i]);
				}
				if (Data.length != N) {
					System.out.println("输入的页数不是 " + N + ". It is " + Data.length + ". 请再输入一遍.");
				}
			} while (Data.length != N);

			/* 输入最小物理块数 */
			do {
				System.out.println("输入最小物理块数: ");
				while (!scan.hasNextInt()) {
					System.out.println("请输入整数.");
					scan.next();
				}
				M = scan.nextInt();
				if (M <= 0) {
					System.out.println("请输入一个正确的整数.");

				}

			} while (M <= 0);

			Block = new int[M];
			for (int i = 0; i < N; i++) {
				System.out.print(Data[i] + " ");
			}
			/* 显示Data */
			System.out.println("页面访问序列是: " + N);
			System.out.println("页面最小块数是: " + M + "\n");

			System.out.println("\n");

			/* LRU */
			if (choice == 1) {
				System.out.println("************************");
				System.out.println("\tLRU");
				System.out.println("************************");
				LRU(N, Data, Block, M);
			}

		} while (choice != 2);
	}

	/* LRU算法 */
	public void LRU(int N, int Data[], int Block[], int M) {
		int i, j;
		boolean find;
		int point = 0;
		int temp;
		ChangeTimes = 0;
		DataShowEnable = new boolean[M][N];
		DataShow = new int[M][N];
		count = new int[M];
		for (j = 0; j < M; j++)
			for (i = 0; i < N; i++)
				DataShowEnable[j][i] = false;
		for (i = 0; i < M; i++) {
			count[i] = 0;
		}

		for (i = 0; i < N; i++) {
			for (j = 0; j < M; j++)
				count[j]++;
			find = false;

			for (j = 0; j < M; j++) {
				if (Block[j] == Data[i]) {
					count[j] = 0;
					find = true;
				}
			}
			if (find)
				continue;
			ChangeTimes++;
			if ((i + 1) > M) {
				temp = 0;
				for (j = 0; j < M; j++) {
					if (temp < count[j]) {
						temp = count[j];
						point = j;
					}
				}
			} else
				point = i;
			Block[point] = Data[i];
			count[point] = 0;
			for (j = 0; j < M; j++) {
				DataShow[j][i] = Block[j];
				DataShowEnable[i < M ? (j <= i ? j : i) : j][i] = true;
			}
		}
		DataOutput();
	}

	public void DataOutput() {
		int i, j;
		for (i = 0; i < N; i++)
			System.out.print(Data[i] + " ");
		System.out.println();
		for (j = 0; j < M; j++) {
			for (i = 0; i < N; i++) {
				if (DataShowEnable[j][i])
					System.out.print(DataShow[j][i] + " ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
		double x;
		x = (ChangeTimes * 100) / N;
		System.out.println("缺页次数" + ChangeTimes);
		System.out.println("缺页率" + x + "%");
	}

	public static void main(String args[]) {
		ReplacementPolicy lru = new ReplacementPolicy();
		lru.dispaly();
	}
}
