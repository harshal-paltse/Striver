class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        // Get the length of the original input array
        int arrayLength = nums.length;
        // Double the length to simulate circular behavior (handling wrap-around)
        int doubledLength = arrayLength * 2;

        // Array to store the shortest distance to a duplicate for every index (0 to 2N-1)
        // We initialize with a large value (doubledLength) as a "placeholder" for infinity
        int[] minDistances = new int[doubledLength];
        Arrays.fill(minDistances, doubledLength);

        // --- FIRST PASS: LOOKING LEFT ---
        // Map to keep track of the most recent index we saw a specific number
        Map<Integer, Integer> lastSeenPositions = new HashMap<>();
        for (int i = 0; i < doubledLength; i++) {
            // Use modulo (%) to cycle through the original nums array indices
            int currentElement = nums[i % arrayLength];

            // If we have encountered this number before, calculate how far back it was
            if (lastSeenPositions.containsKey(currentElement)) {
                int distanceToLeft = i - lastSeenPositions.get(currentElement);
                // Update the distance array with the smaller value
                minDistances[i] = Math.min(minDistances[i], distanceToLeft);
            }

            // Record/Update the current index as the "latest" position for this number
            lastSeenPositions.put(currentElement, i);
        }

        // --- SECOND PASS: LOOKING RIGHT ---
        // Map to keep track of the next index where a specific number appears
        Map<Integer, Integer> nextPositions = new HashMap<>();
        // Iterate backward from the end of the doubled array
        for (int i = doubledLength - 1; i >= 0; i--) {
            int currentElement = nums[i % arrayLength];

            // If this number exists further ahead in the array, calculate that distance
            if (nextPositions.containsKey(currentElement)) {
                int distanceToRight = nextPositions.get(currentElement) - i;
                // Update the distance array if this right-side duplicate is closer
                minDistances[i] = Math.min(minDistances[i], distanceToRight);
            }

            // Record/Update the current index as the "nearest future" position for this number
            nextPositions.put(currentElement, i);
        }

        // --- THIRD PASS: CONSOLIDATING CIRCULAR RESULTS ---
        // Because it's circular, the "best" distance for index i might have been
        // found in the first half or the second half of our doubled array.
        for (int i = 0; i < arrayLength; i++) {
            // Compare the result at index i with its counterpart at index i + N
            minDistances[i] = Math.min(minDistances[i], minDistances[i + arrayLength]);
        }

        // --- FOURTH PASS: ANSWERING QUERIES ---
        List<Integer> results = new ArrayList<>();
        for (int queryIndex : queries) {
            int distance = minDistances[queryIndex];
            
            // If the shortest distance is still the initial 'infinity' (or >= arrayLength),
            // it means no duplicate exists in the circular array. Return -1.
            results.add(distance >= arrayLength ? -1 : distance);
        }

        return results;
    }
}
