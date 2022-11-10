// 2022-11-10
// 파핑파핑 지뢰찾기

import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static char[][] map;
	static int[][] state;
	static boolean[][] visited;
	static int[] dx = {-1,-1,-1,0,1,1,1,0};
	static int[] dy = {-1,0,1,1,1,0,-1,-1};
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			
			N = Integer.parseInt(in.readLine());
			
			map = new char[N][N];
			state = new int [N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				map[i] = in.readLine().toCharArray();
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]=='*') state[i][j] = 9; // 지뢰인 곳은 상태맵에 9 표시
				}
			}
			
			//1. 8방탐색을 통해 상태맵에 지뢰 수를 표시한다.
			checkMap(); 

			int ans = 0; // 최소 클릭 수
			
			//2. 상태맵에 0이면서(지뢰가 없으면서) 맵에 지뢰가 아니고 아직 탐색을 하지 않은 곳이라면
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(state[i][j]==0 && !visited[i][j]) {
						BFS(i,j); //BFS 탐색
						ans++;
					}
				}
			}
			
			// 3. 아직 방문하지 않았고, 지뢰가 아닌 곳 클릭수 더하기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && map[i][j]!='*') {
						ans++;
					}
				}
			}
			
			sb.append(ans).append("\n");
		
		}
		
		System.out.println(sb);
	}
	private static void BFS(int x, int y) {
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[x][y] = true;
		q.offer(new int[] {x,y});
		
		while(!q.isEmpty()) {
			
			int[] p = q.poll();
			
			int curX = p[0]; // 현재 위치
			int curY = p[1];
			
			for(int i=0; i<8; i++) {
				
				int nx = curX + dx[i]; // 탐색 위치
				int ny = curY + dy[i];
				
				
				// 배열 범위 안이고, 아직 방문하지 않았고, 상태맵이 0인 곳이면
				if(isValid(nx,ny) && !visited[nx][ny]) {
					visited[nx][ny] = true; // 방문체크하고
					
					if(state[nx][ny]==0) { // 상태맵이 0이면
						q.offer(new int[] {nx,ny}); // 다음 탐색을 위해 큐에 넣는다.
					} 
				}
					
			}
		}
	}
	
	private static void checkMap() {
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				if(map[i][j]=='*') continue; // 지도에 지뢰가 있는 곳은 탐색 X
				
				int curX = i;
				int curY = j;
				int mineCnt = 0;
				
				for(int k=0; k<8; k++) {
					
					int nx = curX + dx[k];
					int ny = curY + dy[k];
					
					// 배열 범위 안이고 탐색한 칸에 지뢰가 있다면
					if(isValid(nx,ny) && map[nx][ny]=='*') {
						mineCnt++; // 지뢰개수 1증가
					}
				}
				
				state[curX][curY] = mineCnt; // 해당 칸에 지뢰개수를 넣는다.
			}
		}
	}
	private static boolean isValid(int nx, int ny) {
		if(nx>=0 && ny>=0 && nx<N && ny<N) return true;
		return false;
	}
}
