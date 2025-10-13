class Heap {
    int arr[];
    int size;
    public Heap(int arr[]) {
        this.arr = arr;
        this.size = arr.length;

        buildHeap();
    }


    void buildHeap() {
        for(int i=this.size/2-1; i>=0; i--) {
            heapify(i);
        }
    }


    void heapify(int root_index) {
        int max_index = root_index;
        int child = root_index * 2 + 1;
        // size is not the same as arr.length .... size will change if elements
        // are taken out of the heap
        if(child<size) {
            if(arr[child] > arr[max_index]) {
                max_index = child;
            }
        }

        if(child+1<size) {
            if(arr[child+1]>arr[max_index]) {
                max_index = child+1;
            }
        }


        swap(arr, root_index, max_index);
        if(root_index!=max_index) {
            heapify(max_index);
        }
    }


    void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    int extractRoot() {
        int max = arr[0];
        arr[0] = arr[--size];
        heapify(0);


        return max;
    }

    int getSize() {
        return size;
    }
}


class HeapSort {
    public static void main(String args[]) {
        int arr[] = {
        432, 809, 213, 725, 37, 960, 578, 63, 921, 145, 689, 281, 506, 955, 194, 374, 820, 62,
        890, 485, 786, 911, 394, 178, 627, 902, 420, 579, 733, 96, 311, 654, 250, 771, 9, 624,
        712, 135, 505, 884, 445, 688, 77, 912, 721, 390, 538, 893, 470, 679, 1, 869, 302, 946,
        561, 144, 790, 422, 769, 57, 899, 308, 687, 469, 237, 630, 961, 36, 578, 799, 180, 628,
        886, 298, 835, 62, 974, 214, 518, 746, 132, 899, 243, 511, 798, 235, 681, 61, 918, 375,
        692, 993, 183, 553, 846, 24, 954, 286, 647, 129};
        Heap h = new Heap(arr);

        while(h.getSize()>0) {
            System.out.print(h.extractRoot()+", ");
        }
    }
}
