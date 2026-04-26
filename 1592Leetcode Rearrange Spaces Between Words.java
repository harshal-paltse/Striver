class Solution {
    public static String check(String result , String data[], int idx,int spacesNeed,int n){
        if(idx == n) result += data[idx];
        while(spacesNeed > 0){
            result += " ";
            --spacesNeed;
        }
        return result;
    }
    public String reorderSpaces(String text) {
        
        int len = text.length();
        text = text.replaceAll("\\s+", " ").trim();
        String data[] = text.split(" ");

        int n = data.length-1;
        int wLen = 0;
        
        for(String word : data) wLen += word.length();
        int spacesNeed = len - wLen;
        String result = "";
        int idx = 0;

        if(n != 0){
            int eachOne = spacesNeed / n;
            while(spacesNeed > 0 && idx < n){
            result += data[idx];
            if(spacesNeed - eachOne >= 0){
                for(int i =0;i<eachOne;i++) {
                    result += " ";
                    --spacesNeed;
                }
            }
                idx++;
            }
        }
        result = check(result,data,idx,spacesNeed,n);
        return result;
    }
}