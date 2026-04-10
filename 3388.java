class Solution {
    public int beautifulSplits(int[] nums) {
        int n = nums.length  ;
        int ways = 0;
        int[][] dp =  new int[n+1][n+1];
        for (int i = n-1; i>=0 ; i-- ){
            for (int j = i; j <n ; j++){// keeps cnt of same number (int prefixSubArrays, starting i,j)
                if (nums[i] == nums[j]){
                    dp[i][j] = dp[i+1][j+1] + 1;// same elements (adding the cnt of forward same nums)
                }
            }
        }
        // nums1 = nums[0:i+1]
        // nums2 = nums[i+1 : j+1]
        // nums3 = nums[j+1 : n]
        for (int i = 0 ; i<n-1 ; i++){// firstSplit 0 to i
            for (int j = i+1;  j<n-1 ; j++){ // second Split i+1 to n-1 (3rd part atleast 1 element)
                boolean firstPrefix =( i+1 <= j-i ) && (dp[0][i+1] >= i+1);
                boolean secPrefix = (j-i <= n-j ) && (dp[i+1][j+1] >= j-i);
                if (firstPrefix || secPrefix){
                    ways++;
                }
            }
        }
        
        return ways;
    }
}