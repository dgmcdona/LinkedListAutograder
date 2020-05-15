import java.util.*;
import java.lang.StringBuilder;

public class SinglyLinkedList <T> implements Iterable <T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SinglyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T item){
        Node<T> newNode = new Node<>(item, null);
        if(this.size == 0){
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        this.size++;
    }

    public void insertAt(T element, int n){
        if(n > this.size){
            throw new IndexOutOfBoundsException();
        }
        if(n == 0){
            this.head = new Node<T>(element, this.head);
        } else{
            Node<T> node = this.head;
            for(int i = 0; i < n; i++){
                node = node.getNext(); 
            } 
            Node<T> newNode = new Node<>(element, node.getNext());
            node.setNext(newNode);
        }
        this.size++;
    }

    public void remove(T item){
        T data = null;
        if(this.head.getData().equals(item)){
            this.head = this.head.getNext();
            this.size--;
        }
        else{
            boolean found = false;
            Node<T> cursor = head;
            while(cursor != null){
                if(cursor.getNext().getData().equals(item)){
                    cursor.setNext(cursor.getNext().getNext());
                    this.size--;
                    return;
                }
                cursor = cursor.getNext();
            }

        }
    }

    public void clear(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Boolean isEmpty(){
        return this.size == 0;
    }

    public int size(){
        return this.size;
    }

    public T getNthFromFirst(int n){
        if(n >= this.size){
            throw new IndexOutOfBoundsException();
        }
        SinglyLinkedListIterator iter = new SinglyLinkedListIterator();
        T data = iter.next();
        for(int i = 0; i < n; i++){
            data = iter.next();
        }
        return data;
    }

    public T getNthFromLast(int n){
        if(n >= this.size){
            throw new IndexOutOfBoundsException();
        }
        SinglyLinkedListIterator iter = new SinglyLinkedListIterator();
        T data = null;
        for(int i = 0; i < this.size - n; i++){
            data = iter.next();
        }
        return data;
    }

    public SinglyLinkedListIterator iterator(){
        return new SinglyLinkedListIterator();
    }

    public String toString(){
        if(this.size == 0){
            return "{}";
        } else {
            StringBuilder str = new StringBuilder();
            str.append("{ ");
            for(T element: this){
                str.append(element);
                str.append(", ");
            }
            str.setCharAt(str.length()-2, ' ');
            str.setCharAt(str.length()-1, '}');
            return str.toString();
        }

    }

    private class Node <T>{
        private T data;
        private Node<T> nextNode;

        Node(T data, Node<T> nextNode){
                this.data = data;
                this.nextNode = nextNode;
        }

        public Node<T> getNext(){
                return this.nextNode;
        }

        public void setNext(Node<T> n){
                this.nextNode = n;
        }

        public T getData(){
                return this.data;
        }
    }

    private class SinglyLinkedListIterator implements java.util.Iterator<T>{

        private Node<T> cursor;

        public SinglyLinkedListIterator(){
            cursor = head;
        }

        public T next(){
            T data = null;
            if(cursor != null){
                data = cursor.getData();
                cursor = cursor.getNext();
            }
            return data;

        }

        public boolean hasNext(){
            return (cursor != null);
        }

        public void remove(){
            throw new UnsupportedOperationException("Remove operation is not supported" + 
                            "in this implementation of Iterable<T>");
        }

    }


}
