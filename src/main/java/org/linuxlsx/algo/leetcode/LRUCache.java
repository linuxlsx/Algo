package org.linuxlsx.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    Map<Integer, Node> nodeMap;
    DoubleList doubleList;
    int capacity;
    int size = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new HashMap(capacity);
        this.doubleList = new DoubleList();
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if(node != null){
            //将该节点移动到头结点
            doubleList.remove(node);
            doubleList.add(node);
            return node.value;
        }

        return -1;
    }

    public void put(int key, int value) {
        Node node = nodeMap.get(key);
        if(node != null){
            node.value = value;
            doubleList.remove(node);
            doubleList.add(node);
        }else{
            node = new Node(key, value);
            if(size == capacity){
                Node evict = doubleList.evictOne();
                nodeMap.remove(evict.key);
            }else{
                size++;
            }
            doubleList.add(node);
            nodeMap.put(key, node);
        }
    }

    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    public static class Node{

        public int key;
        public int value;

        public Node prev;
        public Node next;

        public Node(){}

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public static class DoubleList{

        public Node dummyHead;
        public Node dummyTail;

        public DoubleList(){
            dummyHead = new Node();
            dummyTail = new Node();

            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        public void add(Node node){
            //直接添加到列表头
            dummyHead.next.prev = node;
            node.next = dummyHead.next;
            node.prev = dummyHead;
            dummyHead.next = node;
        }

        public void remove(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.prev = null;
            node.next = null;
        }

        public Node evictOne(){
            Node evict = dummyTail.prev;

            if(evict.prev == null){
                //到这里说明列表空了
                return null;
            }

            evict.prev.next = dummyTail;
            dummyTail.prev = evict.prev;

            evict.prev = null;
            evict.next = null;

            return evict;
        }
    }
}
