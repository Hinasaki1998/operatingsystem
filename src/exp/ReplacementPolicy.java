package exp;

import java.util.*;

class ReplacementPolicy {
	int DataShow[][]; // ���ڴ洢Ҫ��ʾ������
	boolean DataShowEnable[][]; // ���ڴ洢�����е������Ƿ���Ҫ��ʾ
	int Data[]; // ��������
	int Block[]; // �����
	int count[]; // ������
	int N; // ҳ�����
	int M;// ��С�������
	int ChangeTimes;

	// 4,3,2,1,4,3,5,4,3,2,1,5,6,2,3,7,1,2,6,1
	public void dispaly() {
		Scanner scan = new Scanner(System.in);
		int choice;
		do {
			/* �˵� */
			System.out.println("====================");
			System.out.println("\t�˵�");
			System.out.println("====================");
			System.out.println("\t1.LRU");
			System.out.println("\t2.EXIT");

			/* ����ѡ�� */
			do {
				System.out.println("����ѡ����: ");
				while (!scan.hasNextInt()) {
					System.out.println("��ֻ������1��2");
					System.out.println("����ѡ����e: ");
					scan.next();
				}
				choice = scan.nextInt();
				if (choice != 1 && choice != 2) {
					System.out.println("��ֻ������1��2.");
				}
			} while (choice != 1 && choice != 2);

			/* ����2���˳� */
			if (choice == 2) {
				System.out.println("*****************************");
				System.out.println("            �˳�                           ");
				System.out.println("*****************************");
				break;
			}
			/* ����ҳ����� */
			do {
				System.out.println("����ҳ�����: ");
				while (!scan.hasNextInt()) {
					System.out.println("����������.");
					scan.next();
				}
				N = scan.nextInt();
				if (N <= 0) {
					System.out.println("������һ����ȷ����.");
				}

			} while (N <= 0);

			Data = new int[N];

			/* �������� */
			System.out.println("����������� (����֮�䶺�Ÿ���): ");
			do {
				String inputString = scan.next().toString();
				String stringArray[] = inputString.split(",");
				Data = new int[stringArray.length];
				for (int i = 0; i < stringArray.length; i++) {
					Data[i] = Integer.parseInt(stringArray[i]);
				}
				if (Data.length != N) {
					System.out.println("�����ҳ������ " + N + ". It is " + Data.length + ". ��������һ��.");
				}
			} while (Data.length != N);

			/* ������С������� */
			do {
				System.out.println("������С�������: ");
				while (!scan.hasNextInt()) {
					System.out.println("����������.");
					scan.next();
				}
				M = scan.nextInt();
				if (M <= 0) {
					System.out.println("������һ����ȷ������.");

				}

			} while (M <= 0);

			Block = new int[M];
			for (int i = 0; i < N; i++) {
				System.out.print(Data[i] + " ");
			}
			/* ��ʾData */
			System.out.println("ҳ�����������: " + N);
			System.out.println("ҳ����С������: " + M + "\n");

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

	/* LRU�㷨 */
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
		System.out.println("ȱҳ����" + ChangeTimes);
		System.out.println("ȱҳ��" + x + "%");
	}

	public static void main(String args[]) {
		ReplacementPolicy lru = new ReplacementPolicy();
		lru.dispaly();
	}
}
