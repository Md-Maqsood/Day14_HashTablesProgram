package com.bridgeLabs.hashTablesProgram;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class HashMapTest {
	private static final Logger logger = LogManager.getLogger(HashMap.class);
	@Test
	public void givenASentenceWhenWordsAddedToHashMapShouldReturnWordFrequency() {
		HashMap<String, Integer> hashMap= new HashMap<String, Integer>();
		String sentence="to be or not to be";
		for(String word: sentence.split(" ")) {
			if(hashMap.get(word)==null) {
				hashMap.add(word, 1);
			}else {
				hashMap.add(word, hashMap.get(word)+1);;
			}
		}
		int toFrequency=hashMap.get("to");
		logger.debug(hashMap);
		Assert.assertEquals(2, toFrequency);
	}
	
	@Test
	public void givenASentenceWhenWordsAddedToHashMapShouldReturnParanoidFrequency() {
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
		int paranoidFrequency=hashMap.get("paranoid");
		logger.debug(hashMap);
		Assert.assertEquals(4, paranoidFrequency);
	}
	
	@Test
	public void givenASentenceWordsAddedToHashMapWordWhenWordRemovedAndSearchedShouldReturnNull() {
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
		Integer avoidableFrequency=hashMap.get("avoidable");
		logger.debug("\n"+hashMap);
		Assert.assertEquals(null, avoidableFrequency);
	}
}
