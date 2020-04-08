package ua.khpi;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Comparator;
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
	public boolean remove(Object o) {
	    Node<T> previous = null;
	    Node<T> current = _head;
	    while (current != null) {
	        if (current.element.equals(o)) {
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
	            System.out.println("Deleted successfully");
	            return true;
	        }
	        previous = current;
	        current = current.next;
	    }
	    System.out.println("Failed to delete");
	    return false;
	}
	
	public boolean remove(int index) {
		if (index < 0 || index > size - 1) {
			System.out.println("Failed to delete");
			return false;
		}
		if (index == 0) {
			_head = _head.next;
		} else {
	        Node<T> node = findNodeBeforeByIndex(index);
	        Node<T> tmp = findByIndex(index);
	        node.next = tmp.next;
	    }
	    size--;
	    System.out.println("Deleted successfully");
	    return true;
	}
	
	public boolean contains(T o) {
        return indexOf(o) != -1;
    }
	
	public int indexOf(T o) {
        int index = 0;
        if (o == null) {
            for (Node<T> x = _head; x != null; x = x.next) {
                if (x.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node<T> x = _head; x != null; x = x.next) {
                if (o.equals(x.element))
                    return index;
                index++;
            }
        }
        return -1;
    }
	private Node<T> findNodeBeforeByIndex(int index) {
	    if (index <= 0 || index > size - 1) {
	        return null;
	    }

	    int count = 0;
	    Node<T> node = _head;
	    while (node.next != null) {
	        if (count == index - 1) {
	            return node;
	        }
	        count++;
	        node = node.next;
	    }
	    return null;
	}
	public T get(int index) {
	    if (index < 0 || index > size - 1) {
	        throw new IndexOutOfBoundsException();
	    }
	    int tmpIndex = 0;
	    if (_head == null) {
	        throw new IndexOutOfBoundsException();
	    }

	    if (index == 0) {
	        return _head.getElement();
	    }

	    Node<T> node = _head;
	    while (node.next != null) {
	        node = node.next;
	        tmpIndex++;
	        if (tmpIndex == index) {
	            return node.getElement();
	        }
	    }
	    throw new IndexOutOfBoundsException();
	}
	private Node<T> findByIndex(int index) {
	    if (index < 0 || index > size - 1) {
	        throw new IndexOutOfBoundsException();
	    }
	    int tmpIndex = 0;
	    if (_head == null) {
	        throw new IndexOutOfBoundsException();
	    }

	    if (index == 0) {
	        return _head;
	    }

	    Node<T> node = _head;
	    while (node.next != null) {
	        node = node.next;
	        tmpIndex++;
	        if (tmpIndex == index) {
	            return node;
	        }
	    }
	    throw new IndexOutOfBoundsException();
	}
	public void clear()	{
	    _head = null;
	    _tail = null;
	    size = 0;
	}
	public T[] getArray(Class<T> clazz, int size) {
	    @SuppressWarnings("unchecked")
	    T[] array = (T[]) Array.newInstance(clazz, size);
	    Node<T> current = _head;
	    int index = 0;
	    while (current != null) {
	        array[index++] = current.element;
	        current = current.next;
	    }
	    return array;
	}
	public Object toArray() {
	    Node<T> current = _head;
	    Object[] array = new Object[size];
	    int index = 0;
	    while (current != null) {
	        array[index++] = current.element;
	        current = current.next;
	    }
	    return array;
	}
	public String toString() {
		Node<T> current = _head;
		String result = "";
	    while (current != null) {
	    	result += current.element.toString();
	    	current = current.next;
	    }
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
		return new ListIterator<T>(this); 
	}
	class ListIterator<T> implements Iterator<T> { 
	    Node<T> current;
	    public ListIterator(MyLinkedList<T> list) { 
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
	public void xmlSaver(String filename) {
		try{
  			XMLEncoder encoder = new XMLEncoder(new FileOutputStream(filename));
  			encoder.writeObject(this);
  			encoder.close();
  		} catch (Exception e){
  			System.out.println(e);
  		}
	}
	public void xmlLoader(String filename) {
		MyLinkedList<T> list = new MyLinkedList<>();
		try{
			XMLDecoder decoder = new XMLDecoder(new FileInputStream(filename));
			list = (MyLinkedList) decoder.readObject();
			decoder.close();		
		} catch (Exception e){
			System.out.println(e);
		}
		this._head = list._head;
		this._tail = list._tail;
		this.size = list.size;
	}
	public void saveContainer(String filename) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(filename);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	    objectOutputStream.writeObject(this);
	    objectOutputStream.close();
	}
	public void recoverContainer(String filename) throws IOException, ClassNotFoundException {
		MyLinkedList<T> list = new MyLinkedList<>();
		FileInputStream fileInputStream = new FileInputStream(filename);
	    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	    list = (MyLinkedList) objectInputStream.readObject();
	    objectInputStream.close();
	    this._head = list._head;
	    this._tail = list._tail;
	    this.size = list.size;
	}
}
