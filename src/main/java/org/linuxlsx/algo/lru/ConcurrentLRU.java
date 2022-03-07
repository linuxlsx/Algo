package org.linuxlsx.algo.lru;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rongruo.lsx
 * @date 2022/3/7
 */
public class ConcurrentLRU<K, V> {

    private Map<K, Node<K, V>> nodeMap = new ConcurrentHashMap<>();
    private DoubleLinkList list = new DoubleLinkList();
    private int capacity;
    private Long expireTime;

    public static void main(String[] args) {

        ConcurrentLRU<String, String> lru = new ConcurrentLRU<>(3);

        lru.put("1", "1");
        lru.put("2", "2");
        lru.put("3", "3");
        lru.get("1");
        lru.put("4", "4");
        lru.remove("4");
        lru.remove("3");
        lru.remove("1");
        lru.remove("0");

        System.out.println("hello");

    }

    public ConcurrentLRU(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity should be greater than zero!");
        }

        this.capacity = capacity;
    }

    public ConcurrentLRU(int capacity, Long expireTime) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity should be greater than zero!");
        }

        if (expireTime == null || expireTime <= 0L) {
            throw new IllegalArgumentException("expireTime should not be null and greater than zero!");
        }

        this.capacity = capacity;
        this.expireTime = expireTime;
    }


    public V get(K key) {

        if (key != null && nodeMap.containsKey(key)) {
            Node<K, V> node = nodeMap.get(key);
            //将node移到队首
            list.moveToHead(node);

            return node.value;
        }

        return null;
    }

    public void put(K key, V value) {

        if (key == null) {
            return;
        }

        if (nodeMap.containsKey(key)) {
            //存在移到队首
            Node<K,V> node = nodeMap.get(key);
            list.moveToHead(node);
        } else {
            //说明是新增
            Node<K, V> node = new Node<>(key, value, System.currentTimeMillis());
            //添加之前判断下容量，如果超过了，删除队尾元素
            while (list.size >= capacity) {
                Node removeNode = list.removeLast();
                if (removeNode != null) {
                    nodeMap.remove(removeNode.key);
                }
            }

            list.add(node);
            nodeMap.put(key, node);
        }

    }

    public void remove(K key) {
        if (key != null && nodeMap.containsKey(key)) {
            list.remove(nodeMap.get(key));
            nodeMap.remove(key);
        }
    }

    public int size() {
        return list.size;
    }


    class DoubleLinkList {

        private volatile int size;

        private volatile Node dummyHead;
        private volatile Node dummyTail;

        private DoubleLinkList() {
            dummyHead = new Node();
            dummyTail = new Node();

            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }


        private void add(Node newNode) {

            newNode.next = dummyHead.next;
            dummyHead.next.prev = newNode;
            newNode.prev = dummyHead;
            dummyHead.next = newNode;

            size++;

        }

        private void moveToHead(Node node) {

            //将节点从列表中删除
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.next = dummyHead.next;
            dummyHead.next.prev = node;
            node.prev = dummyHead;
            dummyHead.next = node;

        }

        private Node removeLast() {

            //说明已经删除完了
            if (dummyTail.prev.prev == null) {
                return null;
            }

            Node node = dummyTail.prev;

            //删除最后一个
            dummyTail.prev.prev.next = dummyTail;
            dummyTail.prev = dummyTail.prev.prev;

            node.next = null;
            node.prev = null;

            size--;

            return node;

        }

        private void remove(Node node) {
            //将节点从列表中删除
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.next = null;
            node.prev = null;

            size--;
        }

    }


    class Node<K, V> {

        K key;
        V value;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Long putTime) {
            this.key = key;
            this.value = value;
            this.putTime = putTime;
        }

        volatile Node prev;

        volatile Node next;

        volatile Long putTime;
    }
}


