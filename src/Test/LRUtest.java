package Test;

import java.util.*;

class LRUtest {
	int DataShow[][]; // 用于存储要显示的数组
	boolean DataShowEnable[][]; // 用于存储数组中的数据是否需要显示
	int Data[]; // 保存数据
	int Block[]; // 物理块
	int count[]; // 计数器
	int N; // 页面个数
	int M;// 最小物理块数
	int ChangeTimes;
	String inputString;

	// 4,3,2,1,4,3,5,4,3,2,1,5,6,2,3,7,1,2,6,1
	public LRUtest(int N, int M, String inputString) {
		this.N = N;
		this.M = M;
		this.inputString = inputString;
	}

	public void dispaly() {
		/* 输入页面个数 */
		do {
			if (N <= 0) {
				System.out.println("请输入一个正确整数.");
			}

		} while (N <= 0);

		Data = new int[N];

		/* 输入序列 */
		String stringArray[] = inputString.split(",");
		Data = new int[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			Data[i] = Integer.parseInt(stringArray[i]);
		}
		Block = new int[M];
		for(int i=1;i<M;i++)
			Block[i]=-1;
		/* 显示Data */
		System.out.println("页面的个数是: " + N);
		System.out.println("页面最小块数是: " + M + "\n");

		/* LRU */
		System.out.println("************************");
		System.out.println("\tLRU");
		System.out.println("************************");
		LRU(N, Data, Block, M);
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
			} 
			else
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
		x = (double) ChangeTimes * (double) 100 / (double) N;
		System.out.println("缺页次数" + ChangeTimes);
		System.out.println("缺页率" + x + "%");
	}
	
	public String Output1() {
		String x="";
		for (int i = 0; i < N; i++)
			x+=Data[i] + " ";
		return x;
	}
	
	public String Output2() {
		String x="";
		for (int j = 0; j < M; j++) {	
			for (int i = 0; i < N; i++) {
				if (DataShowEnable[j][i])
					x+=DataShow[j][i] + "|";
				else
					x+="  |";
			}
			x+="/r/n";
		}
		return x;
	}
	
	public int Outputcishu() {
		return ChangeTimes;
	}

	public String Outputlv() {
		double x;
		x = (double) ChangeTimes * (double) 100 / (double) N;
		return x + "%";
	}
}
