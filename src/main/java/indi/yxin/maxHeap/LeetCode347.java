package indi.yxin.maxHeap;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

// 使用自己实现的优先队列解决
public class LeetCode347 {

    // 将每一个键值对分装成一个类，以便处理
    // 因为这个玩意儿要可以比较，所以需要实现Comparable接口，比较的对象也是一个Freq
    private class Freq implements Comparable<Freq>{
        int e, freq;

        public Freq(int e,int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            // 频次越小，优先级更大
            if (this.freq < another.freq)
                return 1;
            else if (this.freq > another.freq)
                return -1;
            else
                return 0;
        }
    }

    public List<Integer> topKFrequent(int[] nums,int k) {
        // 先使用一个map统计每一个数出现的频次
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else
                map.put(num, 1);
        }
        /**
         * 使用优先队列存储频次高的前k个元素
         * 对于我自己实现的优先队列，底层使用最大堆实现，所以这里需要重新定义一下优先级
         * 频次出现越多，优先级越低；
         * 频次出现越少，优先级越高。
         * 所以，每次有新的键值对传入，只需要出队优先级最高的，即频次最低的，
         * 最后就只剩下优先级最低，即频次出现最多的k个元素
         */
        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for (int key : map.keySet()) {
            if (pq.getSize() < k) {
                // 说明这个队列还没有装满,继续往里面装
                pq.enqueue(new Freq(key, map.get(key)));
            } else if (map.get(key) > pq.getFront().freq){
                pq.dequeue();
                pq.enqueue(new Freq(key, map.get(key)));
            }
        }
        // 经过上面的操作，现在队列中剩下的就是频次最多的元素了 O(log k)

        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(pq.dequeue().e);
        }
        return res;
    }
}
