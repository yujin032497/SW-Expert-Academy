// 2022-11-11
// 보호 필름

import java.io.*;
import java.util.*;
public class Solution {
	
	static int D,W,K;
	static int[][] map, copyMap;
	static int ans;
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			
			st = new StringTokenizer(in.readLine());
			
			D = Integer.parseInt(st.nextToken()); // 필름의 두께(세로)
			W = Integer.parseInt(st.nextToken()); // 가로크기 (가로)
			K = Integer.parseInt(st.nextToken()); // 합격기준(연속개수)
			
			map = new int [D][W];
			copyMap = new int [D][W];
			
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<W; j++) {
					int n = Integer.parseInt(st.nextToken());
					map[i][j] = n;
					copyMap[i][j] = n;
				}
			}
			
			ans = Integer.MAX_VALUE;
			Simulate(0,0);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	private static void Simulate(int depth, int cnt) {
		
		if(depth == D) {
			// 검사통과하는지 확인
			boolean isTrue = check();
			if(isTrue==true) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		
		// 아무것도 안넣었을 때
		Simulate(depth+1, cnt);
		
		// 약품 A를 넣었을 때
		change(1, depth);
		Simulate(depth+1, cnt+1);
		back(depth);
		
		// 약품 B를 넣었을 때
		change(0, depth);
		Simulate(depth+1, cnt+1);
		back(depth);
		
	}
	
	private static void back(int layer) {
		
		for(int i=0; i<W; i++) {
			map[layer][i] = copyMap[layer][i];
		}
	}
	private static void change(int color, int layer) {
		
		for(int i=0; i<W; i++) {
			map[layer][i] = color;
		}
		
	}
	
	private static boolean check() {
		
		for(int i=0; i<W; i++) {
			int color = map[0][i];
			int cnt = 1;
			boolean isTrue = false;
			for(int j=1; j<D; j++) {
				if(cnt==K) {
					isTrue = true;
					break;
				}
				
				if(color==map[j][i]) {
					cnt++;
				}
				else {
					color = map[j][i];
					cnt=1;
					isTrue = false;
				}
			}
			
			if(cnt==K) isTrue = true; // 마지막에 한번 더 체크
			
			if(!isTrue) return false;
		}
		
		return true;
		
	}

}
