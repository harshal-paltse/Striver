class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        for(int l = 0; l < Math.min(m,n)/2; l++) {

            int M = m - l * 2;
            int N = n - l * 2;

            int arr[] = new int[2*(M+N) - 4];
            int idx = 0;

            // Top Row
            for(int c = l; c < l+N; c++) {
                arr[idx++] = grid[l][c];
            }

            // Right Column
            for(int r = l+1; r < l+M; r++) {
                arr[idx++] = grid[r][l+N-1];
            } 

            // Bottom Row
            for(int c = l+N-2; c >= l; c--) {
                arr[idx++] = grid[l+M-1][c];
            }

            // Left Column
            for(int r = l+M-2; r > l; r--) {
                arr[idx++] = grid[r][l];
            }

            rotate(arr,k);

            // Re assign values
            idx = 0;

            // Top Row
            for(int c = l; c < l+N; c++) {
                grid[l][c] = arr[idx++];
            }

            // Right Column
            for(int r = l+1; r < l+M; r++) {
                grid[r][l+N-1] = arr[idx++];
            } 

            // Bottom Row
            for(int c = l+N-2; c >= l; c--) {
                grid[l+M-1][c] = arr[idx++];
            }

            // Left Column
            for(int r = l+M-2; r > l; r--) {
                grid[r][l] = arr[idx++];
            }
        }

        return grid;
    }

    public void rotate(int[] arr, int k) {

        int n = arr.length;
        k %= n;

        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
        reverse(arr, 0, n - 1);
    }

    public void reverse(int[] arr, int l, int r) {

        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            l++;
            r--;
        }
    }
}