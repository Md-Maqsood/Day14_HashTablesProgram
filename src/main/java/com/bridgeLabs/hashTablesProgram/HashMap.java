package com.bridgeLabs.hashTablesProgram;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HashMap<K, V> {
	private static final Logger logger=LogManager.getLogger(HashMap.class);
	private final int numBuckets;
	
	private ArrayList<LinkedList<K>> bucketArray;
	
	public HashMap() {
		this.numBuckets=10;
		this.bucketArray=new ArrayList<LinkedList<K>>();
		for(int i=0;i<numBuckets;i++) {
			this.bucketArray.add(null);
		}
	}
	
	public int getSize() {
		int size=0;
		for(LinkedList<K> linkedList: bucketArray) {
			if(linkedList!=null) {
				size+=linkedList.size();
			}
		}
		return size;
	}
	
	public boolean isEmpty() {
		return getSize()==0;
	}
	
	public int getIndex(K key) {
		int hashKey=Math.abs(key.hashCode());
		int index=hashKey%numBuckets;
		return index;
	}
	
	public V get(K key) {
		int index=getIndex(key);
		LinkedList<K> linkedList=bucketArray.get(index);
		if(linkedList==null) {
			return null;
		}
		MapNode<K, V> mapNode=(MapNode<K, V>) linkedList.search(key);
		return (mapNode==null)?null:mapNode.getValue();
	}
	
	public void add(K key, V value) {
		int index=getIndex(key);
		LinkedList<K> linkedList=bucketArray.get(index);
		if(linkedList==null) {
			linkedList=new LinkedList<K>();
			bucketArray.set(index,linkedList);
		}
		MapNode<K, V> mapNode=(MapNode<K, V>) linkedList.search(key);
		if(mapNode==null) {
			linkedList.append(new MapNode<K, V>(key, value));
		}else {
			mapNode.setValue(value);
		}
	}
	
	public void remove(K key) {
		int index=getIndex(key);
		LinkedList<K> linkedList=bucketArray.get(index);
		if(linkedList!=null) {
			linkedList.remove(key);
		}
	}

	@Override
	public String toString() {
		return "HashMap{"+bucketArray+"}";
	}
	
	public static void main(String[] args) {
		HashMap<String, Integer> hashMap= new HashMap<String, Integer>();
		String sentence="paranoid are not " + 
				"paranoid because they are paranoid but " + 
				"because they keep putting themselves " + 
				"deliberately into paranoid avoidable " + 
				"situations";
		for(String word: sentence.split(" ")) {
			if(hashMap.get(word)==null) {
				hashMap.add(word, 1);
			}else {
				hashMap.add(word, hashMap.get(word)+1);;
			}
		}
		logger.debug(hashMap);
		hashMap.remove("avoidable");
		logger.debug("\n"+hashMap);
	}
}
