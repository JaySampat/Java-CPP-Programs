#include <bits/stdc++.h>

using namespace std; 

vector<int> bestCaseLeft(vector<int> nums, int n, int k){
  vector<int> ans(n); 
  int right = 0; 
  int left = 0; 
  while (left <= right and right < n){
    if(abs(nums[right] - nums[left]) > k){
      left++; 
    }
    else{
      ans[right] = right - left + 1;
      right++; 
    }
  }
  for(int i = 1; i < n; i++){
    ans[i] = max(ans[i-1], ans[i]); 
  }
  return ans; 
}

int main() {
  ifstream cin("diamond.in"); 
  ofstream cout("diamond.out"); 
  int n, k; cin >> n >> k; 
  vector<int> nums(n); 
  for(int i = 0; i < n; i++){
    cin >> nums[i]; 
  }
  sort(nums.begin(), nums.end()); 
  vector<int> left(n), right(n); 
  left = bestCaseLeft(nums,  n, k); 
  reverse(nums.begin(), nums.end()); 
  right = bestCaseLeft(nums, n, k); 
  reverse(right.begin(), right.end()); 
  int ans = 0; 
  for(int i = 0; i < n-1; i++){
    ans = max(left[i] + right[i+1], ans); 
    
  }
  // for(int i : left){
  //   cout << i << " "; 
  // }
  // cout << endl; 
  // for(int i : right){
  //   cout << i << " "; 
  // }
  cout << ans << endl; 
}