package org.linuxlsx.algo.lru;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author rongruo.lsx
 * @date 2022/3/7
 */
public class LRU {

    private HashMap<Integer, Node> map;
    private LinkedList<Node> cache;
    private int capacity; // 容量

    LRU(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new LinkedList<>();
    }

    // LRU的get方法：在OS页面调度中，相当于使用了某页
    public int get(int key) {
        if (!map.containsKey(key)) { // 找不到该页
            return -1;
        }

        int val = map.get(key).val;
        // 重新放入cache，相当于把该页的位置提前
        this.put(key, val);
        return val;
    }

    public void put(int key, int val) {
        // create new Node
        Node temp = new Node(key, val);

        if (map.containsKey(key)) { //如果已经存在了
            cache.remove(map.get(key));
            cache.addFirst(temp);
            // 重点：别忘了更新hashtable
            map.put(key, temp);
        } else { // cache已满，需要移除最后一个并加入当前
            if (capacity == cache.size()) {
                /**
                 * 当缓存容量已满，我们不仅仅要删除最后一个 Node 节点，
                 * 还要把 map 中映射到该节点的 key 同时删除，而这个 key 只能由 Node 得到。
                 * 如果 Node 结构中只存储 val，那么我们就无法得知 key 是什么，就无法删除 map 中的键，造成错误。
                 */
                Node last = cache.removeLast();
                // 重点：勿忘更新hashtable
                map.remove(last.key);
            }
            // 加入当前
            cache.addFirst(temp);
            // 更新cache
            map.put(key, temp);
        }

    }

    public static void main(String[] args) {
        // 具体例子来看看 LRU 算法怎么工作

        /* 缓存容量为 2 */
        LRU cache = new LRU(2);
        // 你可以把 cache 理解成一个队列
        // 假设左边是队头，右边是队尾
        // 最近使用的排在队头，久未使用的排在队尾
        // 圆括号表示键值对 (key, val)

        cache.put(1, 1);
        // cache = [(1, 1)]
        cache.put(2, 2);
        // cache = [(2, 2), (1, 1)]
        cache.get(1); // 返回 1
        // cache = [(1, 1), (2, 2)]
        // 解释：因为最近访问了键 1，所以提前至队头
        // 返回键 1 对应的值 1
        cache.put(3, 3);
        // cache = [(3, 3), (1, 1)]
        // 解释：缓存容量已满，需要删除内容空出位置
        // 优先删除久未使用的数据，也就是队尾的数据
        // 然后把新的数据插入队头
        cache.get(2); // 返回 -1 (未找到)
        // cache = [(3, 3), (1, 1)]
        // 解释：cache 中不存在键为 2 的数据
        cache.put(1, 4);
        // cache = [(1, 4), (3, 3)]
        // 解释：键 1 已存在，把原始值 1 覆盖为 4
        // 不要忘了也要将键值对提前到队头
    }

    /* Node节点数据结构 */
    class Node {
        public int key, val;
        public Node next;

        Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }
}

