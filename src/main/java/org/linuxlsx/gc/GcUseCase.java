package org.linuxlsx.gc;

import org.linuxlsx.gc.common.Heap;
import org.linuxlsx.gc.marksweep.MarkSweepAlgo;
import org.linuxlsx.gc.marksweep.MarkSweepObj;

/**
 * @author linuxlsx
 * @date 2017/12/28
 */
public class GcUseCase {

    public static void main(String[] args) {

        MarkSweepAlgo algo = new MarkSweepAlgo(20, Heap.FIRST_FIT_STRATEGY);


        MarkSweepObj obj1 = algo.newObj(2);
        MarkSweepObj obj2 = algo.newObj(1);
        obj1.next.add(obj2);

        MarkSweepObj obj3 = algo.newObj(3);
        algo.makeItToRoot(obj3);

        MarkSweepObj obj4 = algo.newObj(4);
        algo.makeItToRoot(obj4);
        MarkSweepObj obj5 = algo.newObj(1);
        MarkSweepObj obj6 = algo.newObj(1);
        obj4.next.add(obj5);
        obj4.next.add(obj6);


        MarkSweepObj obj7 = algo.newObj(2);

        algo.markSweep();
        algo.combine();

        MarkSweepObj obj8 = algo.newObj(7);
        obj3.next.add(obj8);
        MarkSweepObj obj9 = algo.newObj(4);

    }
}
