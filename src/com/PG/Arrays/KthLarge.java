package com.PG.Arrays;

public class KthLarge {

    public static void main(String[] args) {
        int [] arr = {23, 45, 57, 12, 2, 3, 1};
        Solution s = new Solution();

        System.out.println(s.findKthLargest(arr, 4));
    }

}

class Solution {
    public int findKthLargest(int[] arr, int k) {
        int[] heap = new int[k];

        createHeap(heap, arr, k);

        for(int i=k; i<arr.length; i++) {
            if(arr[i] > heap[0]) {
                heap[0] = arr[i];
                heapify(heap, 0, k);
            }
        }

        return heap[0];
    }

    public void createHeap(int[] heap, int[] arr, int k) {
        System.arraycopy(arr, 0, heap, 0, k);

        for(int i = k/2-1; i>=0; i--) {
            heapify(heap, i, k);
        }
    }

    public void heapify(int[] heap, int i, int n) {
        int smallest = i;
        int l = 2*i+1;
        int r = 2*i+2;

        if(l<n && heap[l]<heap[smallest]) {
            smallest = l;
        } if(r<n && heap[r]<heap[smallest]) {
            smallest = r;
        }

        if(smallest != i) {
            int temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
            heapify(heap, smallest, n);
        }
    }
}