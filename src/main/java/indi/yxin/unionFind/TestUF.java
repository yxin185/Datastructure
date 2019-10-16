package indi.yxin.unionFind;

import java.util.Random;

public class TestUF {

    /**
     *
     * @param uf :创建一个UF实例
     * @param m ：执行次数
     * @return
     */
    private static double testUF(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0;i < m;i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a,b);
        }
        for (int i = 0;i < m;i++) {
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            uf.isConnected(p,q);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;

    }

    public static void main(String[] args) {
        double time1 = testUF(new UnionFind1(100000), 100000);
        double time2 = testUF(new UnionFind2(100000), 100000);
//        double time3 = testUF(new UnionFind3(10000000), 10000000);
//        double time4 = testUF(new UnionFind4(10000000), 10000000);
//        double time5 = testUF(new UnionFind5(10000000), 10000000);

        System.out.println("time1 = " + time1 + "s");
        System.out.println("time2 = " + time2 + "s");
//        System.out.println("time3 = " + time3 + "s");
//        System.out.println("time4 = " + time4 + "s");
//        System.out.println("time5 = " + time5 + "s");
    }
}
