package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href='https://leetcode.com/problems/search-in-rotated-sorted-array/'>Search in Rotated Sorted Array</a>
 * 8 ms, faster than 98.71% of Java online submissions for Search in Rotated Sorted Array.
 * @author linuxlsx
 * @date 2018/11/2
 */
public class SearchInRotatedSortedArray {

    /**
     * 因为要求算法时间复杂度为 lg(n) 所以必定只能采取二分查找的方式
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        if (nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int midIndex = (nums.length - 1) >>> 1;

        int index = searchHalf(nums, target, 0, midIndex);

        if (index >= 0) {
            return index;
        }

        return searchHalf(nums, target, midIndex + 1, nums.length - 1);
    }


    /**
     * 最快的版本，6ms. 省去了递归查找临界点的开销
     * from : <a href='https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/188887/6ms-runtime-beats-100-of-Java-submissions'>Java 6ms Solution</a>
     * @param nums
     * @param target
     * @return
     */
    public int searchV2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length-1;

        while(left<right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target) {
                return mid;
            }
            if(nums[left] == target) {
                return left;
            }
            if(nums[right] == target) {
                return right;
            }
            if(nums[mid] > nums[left]){
                if(nums[mid] > target && nums[left] < target) {
                    right = mid;
                } else {
                    left = mid+1;
                }
            } else {
                if(nums[mid] < target && nums[right] > target) {
                    left = mid+1;
                } else {
                    right = mid;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }

    public int searchV3(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // 6
            // 4 5 6 7 1 2 3

            if (nums[0] <= nums[mid]) {
                //到这里说明左半部分是有序的
                if (nums[0] <= target && target < nums[mid]) {
                    //如果目标在左半部分，则在左半部分查找
                    r = mid - 1;
                } else {
                    //否则在有半部分查找
                    l = mid + 1;
                }
            } else {
                //到这里说明右半部分是有序的
                if (nums[mid] < target && target <= nums[n - 1]) {
                    //如果目标在右半部分，则在右半部分查找
                    l = mid + 1;
                } else {
                    //否则在左半部分查找
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     *  转换以后的数组被二分以后，只可能有两种情况：一个是完全有序的子数组，另外一个是部分有序的数组（先升序，到临界点，再升序）。
     *  有序的数组 nums[end] > nums[start] , 部分有序的数组  nums[end] < nums[start]
     *  所以对于有序的数组，可以通过判断target 是否在其中，如果在其中直接二分查找。
     *  否则对于部分有序的子数组，采用二分法找到临界的元素，在做二分查找
     *
     * @param nums
     * @param target
     * @param start
     * @param end
     * @return
     */
    private int searchHalf(int[] nums, int target, int start, int end) {

        if (start == end) {
            return nums[start] == target ? start : -1;
        }

        if (nums[end] > nums[start]) {
            if (target >= nums[start] && target <= nums[end]) {
                // lg(m)
                return search(nums, target, start, end);
            }

            return -1;
        }

        //到这里已经知道是部分有序的数组。那么找出中间的临界的位置，然后再二分查找
        // lg(m)
        int mid = findMid(nums, start, end);

        // lg(m/2)
        int index = search(nums, target, start, mid);
        if (index >= 0) {
            return index;
        }

        // lg(m/2)
        return search(nums, target, mid + 1, end);
    }


    /**
     * 在部分有序的数组中找到临界的元素
     * 算法时间复杂度 lg(n)
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int findMid(int[] nums, int start, int end) {

        if (end - start == 1 || start >= end) {
            return nums[start] > nums[end] ? start : end;
        }

        int mid = (start + end) >>> 1;

        if(nums[start] > nums[mid]){
            //说明临界元素在这一段中
            return findMid(nums, start, mid);
        }

        if(nums[mid] > nums[end]){
            //说明临界元素在这一段中
            return findMid(nums, mid, end);
        }

        //逻辑上这里不可能执行的到
        return -1;
    }

    /**
     * 有序数组的二分查找。
     * 算法的时间复杂度 lg(n)
     * @param nums
     * @param target
     * @param start
     * @param end
     * @return
     */
    private int search(int[] nums, int target, int start, int end) {

        while (start <= end) {
            int mid = (start + end) >>> 1;
            int midVal = nums[mid];

            if (midVal < target) {
                start = mid + 1;
            } else if (midVal > target) {
                end = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;

    }


    public static void main(String[] args) {

        SearchInRotatedSortedArray sort = new SearchInRotatedSortedArray();


//        System.out.println(sort.search(new int[]{57, 58, 59, 62, 63, 66, 68, 72, 73, 74, 75, 76, 77, 78, 80, 81, 86, 95, 96, 97, 98, 100, 101, 102, 103, 110, 119, 120, 121, 123, 125, 126, 127, 132, 136, 144, 145, 148, 149, 151, 152, 160, 161, 163, 166, 168, 169, 170, 173, 174, 175, 178, 182, 188, 189, 192, 193, 196, 198, 199, 200, 201, 202, 212, 218, 219, 220, 224, 225, 229, 231, 232, 234, 237, 238, 242, 248, 249, 250, 252, 253, 254, 255, 257, 260, 266, 268, 270, 273, 276, 280, 281, 283, 288, 290, 291, 292, 294, 295, 298, 299, 4, 10, 13, 15, 16, 17, 18, 20, 22, 25, 26, 27, 30, 31, 34, 38, 39, 40, 47, 53, 54}, 30));
//        System.out.println(sort.search(new int[]{1, 3}, 2));
//        System.out.println(sort.search(new int[]{1, 3, 5}, 2));
//        System.out.println(sort.search(new int[]{3, 5}, 3));
//        System.out.println(sort.search(new int[]{3, 5}, 5));
        //System.out.println(sort.search(new int[]{7,8,1,2,3,4,5,6}, 2));
        System.out.println(sort.searchV3(new int[]{5,6,7,0,1,2,3,4}, 7));
    }
}
