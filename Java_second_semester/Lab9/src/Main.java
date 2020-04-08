package ua.khpi;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class Main {
	//saving in eclipse-workspace directory
	private final static String filename = "..\\savedContainer.xml";
	private final static String filename2 = "..\\savedContainer.txt";
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String[] mas = {"one","two","three"};
		MyLinkedList<String> udot = new MyLinkedList<>(mas);
		
		
		
	    System.out.println(udot.toString());
	    saveSystem(udot);
	    udot.clear();
	    System.out.println(udot.toString());
	    udot = loadSystem();
	    System.out.println(udot.toString());
	    udot.add("five");
	    System.out.println(udot.toString());
	    System.out.println(udot.remove("five"));
	    System.out.println(udot.toString());

	    System.out.println("---------------------------------------------------");
	    
	    saveContainer(udot);
	    udot.clear();
	    System.out.println(udot.toString());
	    udot = recoverContainer();
	    System.out.println(udot.toString());
	    udot.add("ten");
	    System.out.println(udot.toString());
	    System.out.println(udot.remove("one"));
	    System.out.println(udot.toString());
	    
	    System.out.println("---------------------------------------------------");
	    
		for(Object s : udot) {
			System.out.println(s.toString());
		}
	}
	
	
	
	static public void saveContainer(MyLinkedList list) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(filename2);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	    objectOutputStream.writeObject(list);
	    objectOutputStream.close();
	}
	static public MyLinkedList recoverContainer() throws IOException, ClassNotFoundException {
		MyLinkedList list = new MyLinkedList<>();
		FileInputStream fileInputStream = new FileInputStream(filename2);
	    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	    list = (MyLinkedList) objectInputStream.readObject();
	    objectInputStream.close();
	    return list;
	}
	
	static void saveSystem(MyLinkedList list) {
		try{
  			XMLEncoder encoder = new XMLEncoder(new FileOutputStream(filename));
  			encoder.writeObject(list);
  			encoder.close();
  		} catch (Exception e){
  			System.out.println(e);
  		}
	}
	static MyLinkedList loadSystem() {
		MyLinkedList<Account> list = new MyLinkedList<>();
		try{
			XMLDecoder decoder = new XMLDecoder(new FileInputStream(filename));
			list = (MyLinkedList) decoder.readObject();
			decoder.close();		
		} catch (Exception e){
			System.out.println(e);
		}
		return list;
	}
}