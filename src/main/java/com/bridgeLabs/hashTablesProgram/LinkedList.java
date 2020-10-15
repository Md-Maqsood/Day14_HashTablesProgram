package com.bridgeLabs.hashTablesProgram;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LinkedList<K> {
	private static final Logger logger = LogManager.getLogger(LinkedList.class);

	public INode<K> head;
	public INode<K> tail;
	private int size;

	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}

	public void add(INode<K> newNode) {
		if (this.head == null) {
			this.head = newNode;
		}
		if (this.tail == null) {
			this.tail = newNode;
		} else {
			INode<K> temp = this.head;
			this.head = newNode;
			this.head.setNext(temp);
		}
		this.size++;
	}

	public void append(INode<K> newNode) {
		if (this.head == null) {
			this.head = newNode;
		}
		if (this.tail == null) {
			this.tail = newNode;
		} else {
			this.tail.setNext(newNode);
			this.tail = this.tail.getNext();
		}
		this.size++;
	}

	public void insertAfter(INode<K> node, INode<K> newNode) {
		if(node==this.tail) {
			append(newNode);
		} else {
			INode<K> temp = node.getNext();
			node.setNext(newNode);
			node.getNext().setNext(temp);
			this.size++;
		}
	}

	public INode<K> pop() {
		INode<K> temp = this.head;
		this.head = this.head.getNext();
		size--;
		return temp;
	}

	public INode<K> popLast() {
		INode<K> secondLastNode = this.head;
		if (secondLastNode == null) {
			return null;
		} else if (secondLastNode == this.tail) {
			this.head = null;
			this.tail = null;
			return secondLastNode;
		} else {
			while (secondLastNode.getNext() != this.tail) {
				secondLastNode = secondLastNode.getNext();
			}
		}
		INode<K> temp = this.tail;
		this.tail = secondLastNode;
		this.tail.setNext(null);
		size--;
		return temp;
	}

	public void printList() {
		INode<K> node = this.head;
		if (node != null) {
			logger.debug(node.getKey());
			while ((node = node.getNext()) != null) {
				logger.debug("->" + node.getKey());
			}
		}
		logger.debug(" Size: " + size + "\n");
	}

	public INode<K> search(K searchValue) {
		INode<K> node = this.head;
		while (node != null) {
			if (node.getKey().equals(searchValue)) {
				return node;
			}
			node = node.getNext();
		}
		return null;
	}

	public void insertAfter(K value, INode<K> newNode) {
		INode<K> node = this.head;
		while (node != null) {
			if (node.getKey().equals(value)) {
				break;
			}
			node = node.getNext();
		}
		if (node != null) {
			insertAfter(node, newNode);
		}
	}

	public void remove(K value) {
		INode<K> node = this.head;
		if (node == this.tail && node != null) {
			if (node.getKey().equals(value)) {
				this.head = null;
				this.tail = null;
				size--;
			}
		} else if (node != null) {
			while (node != this.tail && !(node.getNext().getKey().equals(value))) {
				node = node.getNext();
			}
			if (node != this.tail) {
				INode<K> nodeRemoved = node.getNext();
				node.setNext(nodeRemoved.getNext());
				nodeRemoved.setNext(null);
				if (nodeRemoved == this.tail) {
					this.tail = node;
				}
				size--;
			}
		}
	}

	@Override
	public String toString() {
		return "[" + this.head + "]";
	}
	
}

interface INode<K> {
	K getKey();

	void setKey(K key);

	INode<K> getNext();

	void setNext(INode<K> next);
}

class MapNode<K, V> implements INode<K>{
	private K key;
	private V value;
	private MapNode<K, V> next;
	public MapNode(K key, V value) {
		this.key=key;
		this.value=value;
		this.next=null;
	}
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public MapNode<K, V> getNext() {
		return next;
	}
	public void setNext(INode<K> next) {
		this.next = (MapNode<K, V>) next;
	}
	@Override
	public String toString() {
		return key+":"+value+(this.getNext()==null?"":" -> "+this.getNext());
	}
}