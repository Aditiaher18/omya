package Algorithms;
import java.util.*;

public class LRU {

    public static int pageFault (int[] arr, int n, int frames) {

        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        int page_fault = 0 ;

        System.out.println("Strings \t frames \t hit ");
        for (int i=0; i<n;i++) {

            // if frames are empty
            if(set.size() < frames) {
                if (!set.contains(arr[i])) {
                    set.add(arr[i]);
                    page_fault++;
                }
                map.put(arr[i], i);
            }

            // frame not empty
            else {
                if (!set.contains(arr[i])) {

                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;
                    for (int next_ele : set) {
                        if (map.get(next_ele) < lru) {
                            lru = map.get(next_ele);
                            val = next_ele;
                        }
                    }
                    set.remove(val);
                    map.remove(val);
                    set.add(arr[i]);
                    page_fault++;
                }
                map.put(arr[i], i);
            }

            System.out.print(arr[i] + "\t\t" );
            System.out.print(set);
            System.out.println("\t\t");
            // System.out.print(hitarr[i] + "\n");
        }
        System.out.println(map);
        return page_fault;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LRU obj = new LRU() ;

        System.out.print("Enter the length of String : ");
        int len = sc.nextInt();
        System.out.print("Enter the Size of frame : ");
        int frames = sc.nextInt();
        int[] arr = new int[len] ;
        System.out.println("Integer.INT_MAX : " + Integer.MAX_VALUE);
        System.out.println("Integer.INT_MIN : " + Integer.MIN_VALUE);
        System.out.println("Enter the Elements : ");
        for (int i=0; i<len; i++) {
            arr[i] = sc.nextInt();
        }

        int pagefaults = pageFault(arr,len,frames) ;
        int hit = len - pagefaults ;

        System.out.println("Page Faults : " + pagefaults );
        System.out.println("Page Hits : " + hit);
        float hit_ratio = (float)hit/len;
        float fault_ratio = (float)pagefaults/len ;

        System.out.println("Average hit : " + hit_ratio);
        System.out.println("Average fault : " + fault_ratio );
    }

}
