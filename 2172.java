class Solution {
    int max=0;
    HashMap<String,Integer>dp=new HashMap<>();
    private int helper(int idx,int[] nums,int[] slot,int numSlots){
        if(idx==nums.length){
            return 0;
        }
        String key=idx+","+Arrays.toString(slot);
        if(dp.containsKey(key))return dp.get(key);
        int max=0;
        for(int j=1; j<=numSlots; j++){
            if(slot[j]<2){
                slot[j]++;
                int cur=(nums[idx]&j)+helper(idx+1,nums,slot,numSlots);
                max=Math.max(max,cur);
                slot[j]--;
            }
        }
        dp.put(key,max);
        return max;
    }
    public int maximumANDSum(int[] nums, int numSlots) {
        int n=nums.length;
        int[] slot=new int[numSlots+1];
        return helper(0,nums,slot,numSlots);
    }
}
