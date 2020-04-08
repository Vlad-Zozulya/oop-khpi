package ua.khpi;

import java.io.*;
import java.util.Iterator;

public class MyLinkedList<T> implements Serializable, Iterable<T> {
	private static final long serialVersionUID = 1L;
	private Node<T> _head;
	private Node<T> _tail;
	private int size;
	
	public static class Node<T> implements Serializable {
		private static final long serialVersionUID = 1L;
		private T element;
		private Node<T> next;
		
		public Node(T element) {
			this.element = element;
		}
		public Node() {
			
		}
		
		public T getElement() {
			return element;
		}
		public void setElement(T element) {
			this.element = element;
		}
		public Node<T> getNext() {
			return next;
		}
		public void setNext(Node<T> next) {
			this.next = next;
		}
	}
	
	public MyLinkedList() {
        _head = null;
        _tail = null;
        size = 0;
    }
	public MyLinkedList(T[] elements) {
		for(T item : elements) {
			add(item);
		}
	}

	
	public void add(T element) {
	    Node<T> node = new Node<T>(element);
	    if (_head == null) {
	        _head = node;
	        _tail = node;
	    }
	    else {
	        _tail.next = node;
	        _tail = node;
	    }
	    size++;
	}
	public boolean remove(T item) {
	    Node<T> previous = null;
	    Node<T> current = _head;
	    while (current != null) {
	        if (current.element.equals(item)) {
	            if (previous != null) {
	                previous.next = current.next;

	                if (current.next == null) {
	                    _tail = previous;
	                }
	            }
	            else {
	                _head = _head.next;
	                if (_head == null) {
	                    _tail = null;
	                }
	            }
	            size--;
	            return true;
	        }
	        previous = current;
	        current = current.next;
	    }
	    return false;
	}
	
	public boolean contains(T item) {
	    Node<T> current = _head;
	    while (current != null) {
	        if (current.element.equals(item)) {
	            return true;
	        }
	        current = current.next;
	    }
	    return false;
	}
	public void clear()	{
	    _head = null;
	    _tail = null;
	    size = 0;
	}
	public Object[] toArray() {
	    Node<T> current = _head;
	    Object[] array = new Object[size];
	    int index = 0;
	    while (current != null) {
	        array[index++] = current.element;
	        current = current.next;
	    }
	    return array;
	}
	public int size() {
	    return size;
	}
	public String toString() {
		Node<T> current = _head;
		String result = "[ ";
	    while (current != null) {
	    	result += ("\"" + current.element.toString() + "\" ");
	    	current = current.next;
	    }
	    result += "]";
		return result;
	}
	
	
	public Node<T> get_head() {
		return _head;
	}
	public void set_head(Node<T> _head) {
		this._head = _head;
	}
	public Node<T> get_tail() {
		return _tail;
	}
	public void set_tail(Node<T> _tail) {
		this._tail = _tail;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public Iterator<T> iterator() {
		return new MyListIterator<T>(this); 
	}
	class MyListIterator<T> implements Iterator<T> { 
	    Node<T> current;
	    public MyListIterator(MyLinkedList<T> list) { 
	        current = (Node<T>) _head;
	    }
	    public boolean hasNext() { 
	        return current != null; 
	    } 
	    public T next() { 
	        T data = current.getElement(); 
	        current = current.getNext(); 
	        return data; 
	    }
	    public void remove() { 
	        throw new UnsupportedOperationException(); 
	    } 
	} 
}
