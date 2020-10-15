package com.bridgeLabs.hashTablesProgram;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HashMap<K, V> {
	private static final Logger logger=LogManager.getLogger(HashMap.class);
	private LinkedList<K> linkedList;
	
	public HashMap() {
		this.linkedList=new LinkedList<K>();
	}
	
	public V get(K key) {
		MapNode<K, V> mapNode=(MapNode<K, V>) linkedList.search(key);
		return (mapNode==null)?null:mapNode.getValue();
	}
	
	public void add(K key, V value) {
		MapNode<K, V> mapNode=(MapNode<K, V>) linkedList.search(key);
		if(mapNode==null) {
			linkedList.add(new MapNode<K, V>(key, value));
		}else {
			mapNode.setValue(value);
		}
	}

	@Override
	public String toString() {
		return "HashMap{"+linkedList+"}";
	}
	
	public static void main(String[] args) {
		HashMap<String, Integer> hashMap= new HashMap<String, Integer>();
		String sentence="to be or not to be";
		for(String word: sentence.split(" ")) {
			if(hashMap.get(word)==null) {
				hashMap.add(word, 1);
			}else {
				hashMap.add(word, hashMap.get(word)+1);;
			}
		}
		logger.debug(hashMap);
	}
}
