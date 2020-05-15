public class MyStack<T>{
    private SinglyLinkedList<T> stack;
    public MyStack(){
        this.stack = new SinglyLinkedList<T>(); 
    } 

    public void push(T item){
        this.stack.insertAt(item, 0);
    }

    public T pop(){
        T data = this.stack.getNthFromFirst(0);
        this.stack.remove(data);
        return data;
    }

    public boolean isEmpty(){
        return this.stack.isEmpty();
    }

    public T peek(){
        return this.stack.getNthFromFirst(0);
    }
}
