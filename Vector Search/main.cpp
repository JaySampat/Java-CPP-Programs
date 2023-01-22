#include <bits/stdc++.h>

using namespace std; 

bool validPath(vector <vector<char>> &wall, int m, vector <vector<int>> &visited, int row, int col, int numB){
  bool valid = visited[row][col] == numB; 
  if(visited[!row][col] == 0 && wall[!row][col] == 'B'){
    visited[!row][col] = visited[row][col] + 1; 
    valid |= validPath(wall, m, visited, !row, col, numB); 
  }
  if((col + 1) < m && visited[row][col+1] == 0 && wall[row][col + 1] == 'B'){
    visited[row][col + 1] = visited[row][col] + 1; 
    valid |= validPath(wall, m, visited, row, col + 1, numB);
  }
  return valid; 
}

int main() {
  int t; cin >> t; 
  while(t--){
    int m; cin >> m; 
    vector <vector<char>> wall(2, vector<char>(m));
    vector <vector<int>> visited(2, vector<int>(m));
    int numB = 0; 
    for(int i = 0; i < 2; i++){
      for(int j = 0; j < m; j++){
        cin >> wall[i][j]; 
      }
      numB += count(wall[i].begin(), wall[i].end(), 'B'); 
    }
    string ans = "NO"; 
    if(wall[0][0] == 'B'){
      visited[0][0] = 1; 
      if(validPath(wall, m, visited, 0, 0, numB)){
        ans = "YES"; 
      }
    }
    
    if(wall[1][0] == 'B'){
      fill(visited[0].begin(), visited[0].end(), 0);
      fill(visited[1].begin(), visited[1].end(), 0); 
      visited[1][0] = 1;  
      if(validPath(wall, m, visited, 1, 0, numB)){
        ans = "YES"; 
      }
    }
    cout << ans << endl; 
  }
  
}