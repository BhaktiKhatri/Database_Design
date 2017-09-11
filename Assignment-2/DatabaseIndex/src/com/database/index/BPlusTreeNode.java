package com.database.index;

import java.io.*;

public class BPlusTreeNode implements Serializable{
	/* Member Variables */
	private static final long serialVersionUID = 1L;
	int Count;               // Number of keys stored in the current node
    Item[] record = new Item[Constants.maxNumKeys];
    long child[] = new long[Constants.order];   // Fake pointers to child nodes
    int c=record.length;
    
    
    public BPlusTreeNode(){
    	
    }
	/**
	* Input data file will be parsed as KeyField and DataField
	*/
	public class Item implements Serializable{
        	String KeyField;
        	long DataField;
        //Default constructor to avoid KeyField override
    
        	Item(){
        	
        }	
        Item(String KeyField,long DataField){
        	this.KeyField = KeyField;
        	this.DataField = DataField;
        }
     // getter
        public String getKeyField(){ 
        	return KeyField;
        	}
        public long getDataField() { 
        	return DataField; 
        	}
        
        // setter
        public void setKeyField(String KeyField) {
        	this.KeyField = KeyField; 
        	}
        public void setDataField(long DataField) {
        	this.DataField = DataField; 
        	}       
    }
    // getter
    public int getCount(){ 
       	return Count;
   	}
    public Item getRecord(int index) { 
        return record[index]; 
  	}
    public long[] getChild(){
    	return child;
    }
        
    // setter
    public void setCount(int Count){
        this.Count = Count; 
    }
    public void setRecord(int index,Item item) {
    	record[index] = item; 
    }  	
    public void setChild(long[] child){
    	this.child = child;
    }
    
    public void setChild(int index,long child){
    	this.child[index] = child;
    }
	
}
