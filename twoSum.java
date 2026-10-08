class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap  = new HashMap<>();
        for(int i=0; i < nums.length; i++) {
            int remanining = target - nums[i];
            if(hashMap.containsKey(remanining)) {
               return new int[] { hashMap.get(remanining), i };
            }

            //if not  found put the result into the collection
            hashMap.put(nums[i], i);
        }

        return hashMap;
    }
}

// I iterate through the array and calculate the complement as target - nums[i].
// I store each number and its index in a HashMap. If the complement already exists in the map, I return its index and the current index.
// https://leetcode.com/problems/two-sum/?utm_source=chatgpt.com
