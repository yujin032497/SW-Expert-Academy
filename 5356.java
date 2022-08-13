// 2022-08-14
// 의석이의 세로로 말해요

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String args[]) throws Exception {
		
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			sb.append("#"+test_case+" ");
			String ans = "";
			
			char[][] chr = new char [5][15];
			String str = "";

			for(int i=0; i<5; i++) {
				str = in.readLine();
				for(int j=0; j<str.length(); j++) {
					chr[i][j] = str.charAt(j);
				}
			}
			
			for(int i=0; i<15; i++) {
				for(int j=0; j<5; j++) {
					if(chr[j][i]==0) continue;

					ans += chr[j][i];
				}
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.print(sb);
	}
}
