// 2022-08-14
//[S/W 문제해결 기본] 5일차 - GNS

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
	static String[] numbers = {"ZRO","ONE","TWO","THR","FOR","FIV","SIX","SVN","EGT","NIN"};
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("GNS_test_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			sb.append(st.nextToken() + "\n");
			int N = Integer.parseInt(st.nextToken());
			
			String[] data = in.readLine().split(" ");
			
			for(int i=0; i<numbers.length; i++) {
				Collections.replaceAll(Arrays.asList(data), numbers[i], Integer.toString(i));
			}
			
			Arrays.sort(data);
			
			for(int i=0; i<numbers.length; i++) {
				Collections.replaceAll(Arrays.asList(data), Integer.toString(i), numbers[i]);
			}
			
			for(int i=0; i<data.length; i++) {
				sb.append(data[i] + " ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

}
