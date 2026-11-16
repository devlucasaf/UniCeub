package atividadestrabalhos.trabalho;

public class Heap {
    int arr[];
    int size;

    public Heap(int arr[]) {
        this.arr = arr;
        this.size = arr.length;

        buildHeap();
    }

    public void buildHeap() {
        for (int i=this.size/2-1; i>=0; i--) {
            heapify(i);
        }
    }

    public void heapify(int rootIndex) {
        int maxIndex = rootIndex;
        int child = rootIndex * 2 + 1;
        // size is not the same as arr.length .... size will change if elements
        // are taken out of the heap
        if (child<size) {
            if (arr[child] > arr[maxIndex]) {
                maxIndex = child;
            }
        }

        if (child+1<size) {
            if (arr[child+1]>arr[maxIndex]) {
                maxIndex = child+1;
            }
        }

        swap(arr, rootIndex, maxIndex);
        if (rootIndex != maxIndex) {
            heapify(maxIndex);
        }
    }

    public void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public int extractRoot() {
        int max = arr[0];
        arr[0] = arr[--size];
        heapify(0);

        return max;
    }

    public int getSize() {
        return size;
    }
}



