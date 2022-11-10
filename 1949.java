// 2022-11-11
// 등산로 조성

import java.io.*;
import java.util.*;

public class Solution {
	
	static int N,K;
	static int[][] map;
	static int maxHeight, ans;
	static boolean[][] visited;
	static int[] dx = {-1,0,1,0}; // 상우하좌
	static int[] dy = {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			
			st = new StringTokenizer(in.readLine());
			
			N = Integer.parseInt(st.nextToken()); // 한 변 길이
			K = Integer.parseInt(st.nextToken()); // 딱 한 곳 정해서 팔 수 있는 깊이
			
			map = new int [N][N]; // 맵
			maxHeight = 0; // 가장 높은 봉우리 높이
			ans = 0; // 가장 긴 등산로 길이
			
			// 맵 그리기
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, map[i][j]);
				}
			}
			
			// 1. 가장 높은 봉우리 찾기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == maxHeight) { 
						// 2. 가장 높은 봉우리에서 긴 등산로 찾기
						visited = new boolean[N][N];
						DFS(i,j,0,1,0);
					}
				}
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	private static void DFS(int x, int y, int isCut, int depth, int cut) {
		
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			
			// 배열 범위 안이거나 아직 방문하지 않았다면
			if(isValid(nx,ny) && !visited[nx][ny]) {
				
				// 현재봉우리보다 다음 봉우리가 작다면
				if(map[nx][ny] < map[x][y]) {
					
					if(isCut == 1) { // 깎은 봉우리라면
						
						if(map[nx][ny] < map[x][y] - cut) { // 현재 봉우리에서 깎은 만큼 빼고 다음 봉우리보다 크다면
							DFS(nx,ny,isCut,depth+1,0);
							visited[nx][ny] = false;
						}
						
					} else { // 한번도 안깎은 봉우리라면
						DFS(nx,ny,isCut,depth+1,0); // 그대로 다음 봉우리로 간다.
						visited[nx][ny] = false;
					}
					
				} else { // 현재봉우리보다 다음 봉우리가 크거나 같다면
					
					if(isCut == 0) { // 아직 깎지 않았다면
						
						// 다음 봉우리에서 현재봉우리만큼 빼고 +1만큼 깎아서 최대 K만큼 깎거나 작게 깎을 수 있으면
						if(map[nx][ny] - map[x][y] + 1 <= K) { 
							
							DFS(nx,ny,1,depth+1,map[nx][ny]-map[x][y]+1); // 다음봉우리로 진행
							visited[nx][ny] = false;
						}
					}
				}
			}
		}
		
		// 더 이상 진행할 수 없다면
		ans = Math.max(ans, depth);
	}
	private static boolean isValid(int nx, int ny) {
		if(nx>=0 && ny>=0 && nx<N && ny<N) return true;
		return false;
	}
}
