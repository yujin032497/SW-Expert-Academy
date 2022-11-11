// 2022-11-11
// 미생물 격리

import java.io.*;
import java.util.*;

public class Solution {
	static class Bug implements Comparable<Bug>{
		int x; // X좌표
		int y; // Y좌표
		int n; // 미생물 수
		int d; // 이동 방향
		
		public Bug(int x, int y, int n, int d) {
			this.x = x;
			this.y = y;
			this.n = n;
			this.d = d;
		}

		@Override
		public int compareTo(Bug o) {
			return o.n - this.n; // 미생물 수 내림차순
		}
	}
	static int N,M,K;
	static int[] dx = {0,-1,1,0,0}; //1: 상, 2:하, 3:좌, 4:우
	static int[] dy = {0,0,0,-1,1};
	static ArrayList<Bug> bugs;
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			
			st = new StringTokenizer(in.readLine());
			
			N = Integer.parseInt(st.nextToken()); // N*N 한 변의 길이
			M = Integer.parseInt(st.nextToken()); // 격리 시간
			K = Integer.parseInt(st.nextToken()); // 군집의 개수
			
			bugs = new ArrayList<>(); // 미생물 군집 리스트
			
			// 미생물 군집 정보를 군집 리스트에 넣어준다.
			for(int i=0; i<K; i++) {
				
				st = new StringTokenizer(in.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
			
				bugs.add(new Bug(x, y, n, d));
			}
			
			for(int i=0; i<M; i++) {
				Simulate(); // 버그들이 움직인다.
			}
			
			// M 시간 이후 버그들의 수를 구한다.
			int ans = 0;
			
			for(int i=0; i<bugs.size(); i++) {
				ans += bugs.get(i).n;
			}
			
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	private static void Simulate() {
		
		// 1. 다음 셀로 이동한다.
		for(int i=0; i<bugs.size(); i++) {
			
			Bug bug = bugs.get(i);
			
			bug.x += dx[bug.d]; // 다음 셀의 좌표로 이동한다.
			bug.y += dy[bug.d];
			
			if(!isValid(bug.x, bug.y)) { // 약품있는 경계선으로 간다면
				bug.n/=2; // 미생물의 수는 /2
				
				if(bug.n==0) { // 군집의 미생물 수가 0이 된다면
					bugs.remove(i); // 군집 리스트에서 제거된다.
					i--;
					continue;
				}
				
				// 방향을 전환해준다.
				if(bug.d==1) bug.d=2; // 상=>하
				else if(bug.d==2) bug.d=1; // 하=>상
				else if(bug.d==3) bug.d=4; // 좌=>우
				else if(bug.d==4) bug.d=3; // 우=>좌
			}
		}
		
		
		// 군집의 미생물 수 가 많은 순으로 정렬한다.
		Collections.sort(bugs);
		
		// 같은 좌표에 있는 군집은 합쳐주기
		for(int i=0; i<bugs.size()-1; i++) {
			for(int j=i+1; j<bugs.size(); j++) {
				if(bugs.get(i).x==bugs.get(j).x && bugs.get(i).y==bugs.get(j).y) {
					bugs.get(i).n += bugs.get(j).n;
					bugs.remove(j);
					j--;
				}
			}
		}
	}
	
	
	private static boolean isValid(int x, int y) {
		if(x>0 && y>0 && x<N-1 && y<N-1) return true;
		return false;
	}
}
