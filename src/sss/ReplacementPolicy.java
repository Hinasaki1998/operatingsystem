package sss;

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
	String inputString;

	// 4,3,2,1,4,3,5,4,3,2,1,5,6,2,3,7,1,2,6,1
	public ReplacementPolicy(int N, int M, String inputString) {
		this.N = N;
		this.M = M;
		this.inputString = inputString;
	}

	public void dispaly() {
		/* ����ҳ����� */
		do {
			if (N <= 0) {
				System.out.println("������һ����ȷ����.");
			}

		} while (N <= 0);

		Data = new int[N];

		/* �������� */
		String stringArray[] = inputString.split(",");
		Data = new int[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			Data[i] = Integer.parseInt(stringArray[i]);
		}
		Block = new int[M];
		/* ��ʾData */
		System.out.println("ҳ��ĸ�����: " + N);
		System.out.println("ҳ����С������: " + M + "\n");

		/* LRU */
		System.out.println("************************");
		System.out.println("\tLRU");
		System.out.println("************************");
		LRU(N, Data, Block, M);
	}

	/* LRU�㷨 */
	public void LRU(int N, int Data[], int Block[], int M) {
		int k=0;
		int leastFrequency; 
		boolean find=true;
		int page;
		int temp;
		ChangeTimes = 0;
		DataShowEnable = new boolean[M][N];
		DataShow = new int[M][N];
		count = new int[M];
		
		for(int i = 0 ; i < M ; i++ ){
			count[ i ] = 0;
        }
        for( int pg = 0 ; pg <N ; pg++ ){
            page = Data[ pg ];                                                         //assign temp page = pages[page]
            find = true;                                                                    //initially, flag is true because it has not yet found a page hit

            for( int j=0 ; j < M ; j++ ){                                           //checks if page hit
                if( page==Block[ j ]  ){                                                //If page hit, no page fault occurs
                    find = false;
                    count[ j ]++;                                                           //add 1 to its frequency
                    break;                                                              //break
                }
            }

            if( find ){                                                                 //If a page hit occurs,
                leastFrequency = count[ 0 ];
                for( int j = 0 ; j < M ; j++ ){                                     //Look for least number of frequency
                    if( count[ j ] < leastFrequency ){
                        leastFrequency = count[ j ];
                        break;
                    }
                }
                for( int j = 0 ; j < M ; j++ ){                                     //Find the page with the least frequency from the left
                    if( leastFrequency == count[ j ] ){                                                 //The left-most page will be the one to be replaced
                        Block[ j ] = page;
                        k = j;
                        break;
                    }
                }
                count[ k ] = 1;                                                             //set the frequency of new page to 1
                for( int j = 0 ; j < M ; j++ ){
                    System.out.print( Block[ j ]+"   " );
                    ChangeTimes++;                                                           //add 1 to page fault counter
                }
                System.out.println( " --> Page fault!" );
            }
            else{                                                                       //If page hit, no replacement
                System.out.print( "frame: " );
                /* display frame buffer array */
                for( int j = 0 ; j < M ; j++ )
                    System.out.print( Block[ j ]+"   " ); 
                System.out.print( " --> Page hit!" );
                System.out.println();
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
		System.out.println("ȱҳ����" + ChangeTimes);
		System.out.println("ȱҳ��" + x + "%");
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
