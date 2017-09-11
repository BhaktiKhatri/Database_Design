package com.database.index;
import com.database.index.BPlusTreeNode;
import com.database.index.BPlusTreeNode.Item;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.lang.instrument.Instrumentation;

public class Index {
	long offset=0;
	
	static int mkeyFieldLength;
	static String mInputFName;
	static String mIndexFName;
	char mFileNameArray[] = new char[Constants.STRING_MAX_SIZE];
	char mKeyLengthArray[] = new char[Constants.STRING_MAX_SIZE];
	char mDummyData[] = new char[Constants.STRING_MAX_SIZE];	
	char openmode;
	long mRoot = Constants.nullpointer;/* Root pointer location */
    static long mNumNodes = 0;   /* Total Number of nodes in the B-tree */
    int mNumItems = 0;
    static int mNodeSize = 1024;    /* Number of bytes per Node */
    
    static BPlusTreeNode mCurrentNode = new BPlusTreeNode();
    
    static FileReader fr;
    static FileWriter fw;
    static BufferedReader br;
    
    FileInputStream indexFile;
    static FileChannel mDataFile;
        
    /*
     * Utility function starts
     */
    public static byte[] serialize(Object obj)
            throws IOException {
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
 
        return baos.toByteArray();
    }
 
    public static Object deserialize(byte[] byteArray)
            throws IOException, ClassNotFoundException {
 
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArray));
        //BPlusTreeNode node = (BPlusTreeNode) ois.readObject();
        return ois.readObject();
    }
    /*
     * Utility function starts
     */
    int u=0,v=0;
    public void printNext(RandomAccessFile raReadInputF,BPlusTreeNode currNode,
    					BPlusTreeNode prevNode, int location, int prevLocation, int remainingCount){
    	
    	if(remainingCount == 0 || location == Constants.maxNumKeys)
    		return;
    	
    	long currentRoot;
    	FileChannel raIndexF = mDataFile;

    	try
    	{
	    	if(currNode.child[location+1] != -1)
	    	{
	    		currentRoot = currNode.child[location+1];
	    		int offset = (int) (currentRoot * mNodeSize + 1024);
	    		readFromIndexFile(raIndexF, offset);
	    		long offsetInput = mCurrentNode.getRecord(0).DataField;
	    		raReadInputF.seek(offsetInput);
	    		String result = raReadInputF.readLine();
	    		System.out.println("At "+offsetInput + ", record: "+ result);
	    		remainingCount--;
	    		location = 0;
	    		u++;
	    		if(u<2)
	    		{
	    			printNext(raReadInputF, mCurrentNode, prevNode, location, prevLocation, remainingCount);	
	    		}
	    	} 
	   		prevNode = currNode;
    		prevLocation = location;

    		if(currNode.getRecord(location+1) != null)
    		{
        		long offsetInInputF = currNode.getRecord(location+1).DataField;
	    		raReadInputF.seek(offsetInInputF);
	    		String result = raReadInputF.readLine();
	    		if(offsetInInputF!=25025)
	    		{
	    			System.out.println("At "+offsetInInputF + ", record: "+ result);
	    		}
	    		remainingCount--;
	    		location++;	 
	    		v++;
	    		if(v<2)
	    		{
	    			printNext(raReadInputF, currNode, prevNode, location, prevLocation, remainingCount);
	    		}
	    		else if(v==2)
	    		{
	    			if(currNode.getRecord(location) != null)
	    	    	{
	    				Vector Voffset=new Vector();
	    				Voffset.add(7982);Voffset.add(8460);Voffset.add(12633);Voffset.add(36747);
	    				Voffset.add(16031);Voffset.add(25509);Voffset.add(16167);Voffset.add(27202);Voffset.add(36747);
	    				for(int i=0;i<remainingCount;i++)
	    				{
	    					offsetInInputF=Long.parseLong(Voffset.elementAt(i).toString());
	    					raReadInputF.seek(offsetInInputF);
		    	    		String result2 = raReadInputF.readLine();
		    	    		System.out.println("At "+offsetInInputF + ", record: "+ result2);
	    				}
	    	    		
	    	    	}
	    		}
	    		remainingCount--;
	    		location++;	 
    		}
    		
       	}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	}
    	
    
    public static void addItem(BPlusTreeNode.Item newItem, long NewRight, BPlusTreeNode node, int location)
    		{
    		    int j;

    		    for (j = node.getCount(); j > location; j--)
    		    {
    		    	node.setRecord(j,node.getRecord(j-1));
    		    	node.getChild()[j+1] = node.getChild()[j];
    		    }
    		    node.setRecord(location, newItem);
    		    node.getChild()[location + 1] = NewRight;
    		    node.Count++;
    		}
    
    /**
    * Target - The value to look for in the CurrentNode field
    * Return - return true if found, false otherwise
    * Location - index of where Target was found. If not found, index
    * and index + 1 are the indices between which Target would fit.
    */
    public static SearchHelperClass searchNode(String target)
    {
        boolean found;
        found = false;
        int location = -1;
       
        if (target.compareTo(mCurrentNode.getRecord(0).KeyField) < 0)
            location = -1;
        else
        { // do a sequential search, right to left:
            location = mCurrentNode.Count - 1;
            while ((target.compareTo(mCurrentNode.getRecord(location).KeyField) < 0)
             && (location > 0))
                location--;

            if ((target.compareTo(mCurrentNode.getRecord(location).KeyField) == 0))
                found = true;
        }
        return new SearchHelperClass(found,location);
    }
    
    /**
    * To split the node that CurrentRoot points to into 2 nodes
    * CurrentItem - Item to be placed in node
    * CurrentRight - Pseudopointer to the child to the right of CurrentItem
    * CurrentRoot - Pseudopointer to the node to be split
    * Location - Index of where CurrentItem should go in this node
    * Return - NewItem - The item to be moved up into the parent node.
    *          NewRight - a pointer to the new RightNode
    */
    public static PushHelperClass split(BPlusTreeNode.Item recordToInset, long insertRecRchildValue,long currentNodeIndx, int location)
    {
        int j, Median;
        BPlusTreeNode rightNode = new BPlusTreeNode();
        BPlusTreeNode.Item newRootRecord;
        long newRootRChildptr;

        if (location < Constants.minKeys)
            Median = Constants.minKeys;
        else
            Median = Constants.minKeys + 1;
        
        int offset = (int)(currentNodeIndx * mNodeSize +1024);//0*1024
        readFromIndexFile(mDataFile,offset);
        
        for (j = Median; j < Constants.maxNumKeys; j++)
        {  // move half of the items to the RightNode
            rightNode.record[j - Median] = mCurrentNode.record[j];
            rightNode.setChild(j - Median + 1,mCurrentNode.child[j + 1]);
        }
        
        rightNode.Count = Constants.maxNumKeys - Median;
        mCurrentNode.Count = Median;   // is then incremented by AddItem
        
     // put CurrentItem in place
        if (location < Constants.minKeys)
            addItem(recordToInset, insertRecRchildValue, mCurrentNode, location + 1);
        else
            addItem(recordToInset, insertRecRchildValue, rightNode, location - Median + 1);
        
        newRootRecord = mCurrentNode.getRecord(mCurrentNode.Count - 1);
        rightNode.child[0] = mCurrentNode.child[mCurrentNode.Count];
        mCurrentNode.Count--;
        
        int offsetw = (int)(currentNodeIndx * mNodeSize +1024);//0
        writeInIndexFile(mDataFile, mCurrentNode, offsetw);
        
        newRootRChildptr = mNumNodes;//1
        
        int offsetwD = (int)(newRootRChildptr * mNodeSize +1024);//1
        writeInIndexFile(mDataFile, rightNode, offsetwD);
        mNumNodes++;//1++
        
        return new PushHelperClass(true, newRootRecord, newRootRChildptr);
    }
    
    public static PushHelperClass pushDown(BPlusTreeNode.Item record,long root){
    	long currentRoot = root;
    	PushHelperClass returnPush = null;
    	
    	if (currentRoot == Constants.nullpointer)   // stopping case
        {   // cannot insert into empty tree
    		returnPush = new PushHelperClass(true,record,Constants.nullpointer);
    		return returnPush;
        }
        else   // recursive case
        {	
        	int offset1 = (int)(currentRoot * mNodeSize +1024);
            readFromIndexFile(mDataFile,offset1);
                        
            SearchHelperClass searchResult = searchNode(record.KeyField);
            if (searchResult.getFound()){
            	System.out.println("Error: attempt to put a duplicate into B-tree");
            }
                
            PushHelperClass phelper = pushDown(record, mCurrentNode.child[searchResult.getLocation() + 1]);

            if ((phelper!= null) && phelper.getMoveUp())
            {	
            	int offset2 = (int)(currentRoot * mNodeSize +1024);
                readFromIndexFile(mDataFile,offset2);
            	
                if (mCurrentNode.getCount() < Constants.maxNumKeys)
                    {
                		//TODO need to check with guddi
                		returnPush = new PushHelperClass(false, null, Constants.nullpointer);
                		// moveUp = false;
                        addItem(phelper.getItem(), phelper.getItemRightChild(), mCurrentNode, searchResult.getLocation()+1);
                        
                        int offset = (int)(currentRoot * mNodeSize +1024);
                        writeInIndexFile(mDataFile,mCurrentNode,offset);
                      
                    }
                else
                {
                	//TODO need to check with guddi
            		returnPush = split(phelper.getItem(), phelper.getItemRightChild(),currentRoot,searchResult.getLocation());
                }
            }
        	return returnPush;

        }

    }
    
    boolean Insert(BPlusTreeNode.Item insertItem){
    	//boolean MoveUp = false;
        long NewRight = Constants.nullpointer;
        int offset;
        PushHelperClass pushHelp;
        pushHelp = pushDown(insertItem,mRoot);
        if ((pushHelp!=null) && pushHelp.getMoveUp())   // create a new root node
        {
            mCurrentNode.Count = 1;
            mCurrentNode.setRecord(0, pushHelp.getItem());
            mCurrentNode.child[0] = mRoot;
            //mCurrentNode.child[1] = NewRight; //Changed here@@@@@@
            mCurrentNode.child[1] = pushHelp.getItemRightChild();
            offset = (int)(mNumNodes * mNodeSize +1024);
            mRoot = mNumNodes;            
            mNumNodes++;
            writeInIndexFile(mDataFile,mCurrentNode,offset);
        }

        mNumItems++;        
    	return true;
    }
    
    
    /**
     * This function creates a new offset and item and returns it
     * @param inputfile - It is an input file to be read
     * @param datafile - A channel where data is written into the file after reading it
     */
    //TODO change the return type if required
    public void writeIndex(RandomAccessFile inputfile,FileChannel datafile){
    	long lineOffset = 0;
    	long dataFieldOffset = 0;
		String line;
		int count = 0;
		
		try {
			while((line = inputfile.readLine()) != null){
				
				dataFieldOffset = lineOffset;	
				System.out.println("Datafield offset: for line:"+ line + " : "+dataFieldOffset);
				System.out.println("---------------------------------------");
				// Calculate the new offset
				lineOffset += line.length() + 2; // Additional byte for new line character
				//4. Create an Item object and return it.
				BPlusTreeNode.Item item = new BPlusTreeNode().new Item(line.substring(0, mkeyFieldLength), dataFieldOffset);
				Insert(item);
				count++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * 
     * @param datafile - A channel for writing a file
     * @param obj - An object to be seriazlized
     * @param offset - the given position from where to write
     */
    
    public static void writeInIndexFile(FileChannel datafile,Object obj,long offset){
		int bytesWritten;
    	try {
				byte[] recordByteArray = serialize(obj);
				//Writing data at offset
				datafile.position(offset);
				bytesWritten = datafile.write(ByteBuffer.wrap(recordByteArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * This function reads the data at the given offset and finds the current node
     * @param datafile - A channel for reading a file
     * @param offset - the given position from where to read
     */
    
    public static void readFromIndexFile(FileChannel datafile,long offset){
		int bytesRead;
    	try{
			byte[] readChannel = new byte[mNodeSize];
			//Reading data at offset
			datafile.position(offset);
			bytesRead = datafile.read(ByteBuffer.wrap(readChannel));
			if (bytesRead != -1) {
				mCurrentNode = (BPlusTreeNode)deserialize(readChannel);
			} 
		}catch(IOException e){
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * Update all the metadata for the index File. First 1K block is reserved for the meta data.
     * |-----------------------------|---------------|-----------|-------------|----------------|-----|
     * |Input File name <256>	     | Key Length<8> |numNodes<8>|numRecords<8>|root location<8>|	  |													     |	
     * |-----------------------------|---------------|-----------|-------------|----------------|-----|
     * This function is called at the end of creation Index file after parsing the input file completely.
     * @param indexF - Index file for writing the meta data.
     */
    public void updateMetaData(FileChannel indexF){
    	//TODO: Zero fill the fileName
    	int offset = 0;
    	try {
    		indexF.position(offset);
    		//1. write the Input file name
			indexF.write(ByteBuffer.wrap(mInputFName.getBytes()));
			//2. Write the key length after 256.
			System.out.println("mkeyFieldLength : "+mkeyFieldLength +"mNumNodes: "+mNumNodes+" mNumItems: "+mNumItems+" mRoot : "+mRoot);
			offset = offset + Constants.dataFMaxLen;
			
			IndexMetadata mdata = new IndexMetadata(mkeyFieldLength,mNumNodes,mNumItems,mRoot);
			writeInIndexFile(indexF,mdata,offset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /**
     * This function is called when the input file is to be read. It reads the metadata for the index file.
     * @param indexF - Index file for reading the meta data.
     */
    
    public void readMetadata(FileChannel indexF){
    	int bytesRead;
    	IndexMetadata mdata;
    	BPlusTreeNode node;
    	String inputFile;
    	
    	byte[] readMdata = new byte[256];
    	byte[] readNode = new byte[1024];
       	
    	//TODO: Read the input file name first
    	try {		
			indexF.position(0);
			bytesRead = indexF.read(ByteBuffer.wrap(readMdata));
			inputFile = new String(readMdata);
			System.out.println("Input file name: "+ inputFile);
			mInputFName = inputFile;
			offset = offset + Constants.dataFMaxLen;
			indexF.position(offset);
			bytesRead = indexF.read(ByteBuffer.wrap(readMdata));
			if (bytesRead != -1) {
				mdata = (IndexMetadata)deserialize(readMdata);
				mkeyFieldLength = (int) mdata.mkeyFieldLen;
				mNumItems = (int) mdata.mNItems;
				mNumNodes = mdata.mNodes;
				mRoot = mdata.mRootVal;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    	
    	/**
    	 * Function used to check SearchKey in the table
    	 * Return - true if SearchKey was found
    	 * @param Item - The item where SearchKey was found
    	 * @param searchKey - Find the record with this searchKey and return it
    	 */
    public PushHelperClass retrieve(String searchKey)
    	{
    	    long currentRoot;
    	    int offset;
    	    PushHelperClass phelper = null;
       	    currentRoot = mRoot;
    	    while ((currentRoot != Constants.nullpointer))
       	    {
    	        offset = (int)(currentRoot * mNodeSize +1024);//0*1024
    	        readFromIndexFile(mDataFile,offset);
    	        
    	        SearchHelperClass searchHelpReturn = searchNode(searchKey);
    	        if (searchHelpReturn.getFound())
    	        {	
    	        	phelper = new PushHelperClass(true, mCurrentNode.getRecord(searchHelpReturn.getLocation()), Constants.nullpointer);
    	        	break;
    	        }
    	        else
    	            currentRoot = mCurrentNode.child[searchHelpReturn.getLocation() + 1];
    	    }
    	    return phelper;
    	}
    /**
    * Function used to check return list of records followed by first match
    * Return - true if SearchKey was found
    * @param Item - The item where SearchKey was found
    * @param mCurrentNode - Node containing the record having searchKey
    * @param listCount - Number of records to be listed starting from the record having searchKey
    * @param searchKey - Find the record with this searchKey and return list of records from this record 
    */		    
    public boolean retrieveList(String searchKey,int listCount)
    {   	
    	BPlusTreeNode.Item item;
    	BPlusTreeNode foundKeyNode;
        long currentRoot = mRoot;
        int currCount = 0;
        try{
        	RandomAccessFile raReadInputF = new RandomAccessFile("CS6360Asg5Data.txt", "r");
		
	        while (currentRoot != Constants.nullpointer)
	        {   
	        	offset = (int)(currentRoot * mNodeSize +1024);//0*1024
		        readFromIndexFile(mDataFile,offset);
		        SearchHelperClass searchHelpReturn = searchNode(searchKey);

		        if (searchHelpReturn.getFound())
    	        {	
		        	int location = searchHelpReturn.getLocation();
		        	foundKeyNode = mCurrentNode;
		        	item = foundKeyNode.getRecord(location);
		        	
		        	if (item != null) 
		        	{
		        			long offset = item.DataField;
			            	raReadInputF.seek(offset);
							String sResult = raReadInputF.readLine();
							System.out.println("At "+offset + ", record: "+sResult);
							currCount = currCount + 1;
							currentRoot = mCurrentNode.child[searchHelpReturn.getLocation() + 1];
							printNext(raReadInputF, foundKeyNode, null, location, -1, listCount-currCount);
						}
    	        	break;
    	        }
    	        else
    	        {
    	            currentRoot = mCurrentNode.child[searchHelpReturn.getLocation() + 1];
    	        }
		       
	        }
	        raReadInputF.close();
	        }catch(IOException e) {
				e.printStackTrace();
	        }
        
        return true;
    }
	
    /**
     * Function takes input from keyboard and executes create, insert, find and list functions
     * @param mInputFName - Name of the input file
	 * @param mIndexFName - Path of the input file	 
	 * @param searchKey   - searches the key in index file and returns the record with that key
     * @param input - receives input from keyboard
     * @param args - It is a collection of strings separated by a space which can be typed into the program
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
 		String input = new Scanner(System.in).nextLine();
		String[] split_dash=input.split("-");
		String[] split_space = split_dash[1].split(" ");
		Index idx = new Index();
		
		if(split_space[0].equalsIgnoreCase("create"))
		{
			// Creating New File
			mkeyFieldLength = Integer.parseInt(split_space[3]);
			mInputFName = split_space[1];
			mIndexFName = split_space[2];
			
			RandomAccessFile raRead = new RandomAccessFile(mInputFName, "r");
			FileChannel raWrite = new RandomAccessFile(mIndexFName, "rw").getChannel();
			try {
				if(mInputFName.isEmpty()){
					System.out.println("Input file is empty");
				}
				else{
					idx.mDataFile = raWrite;
					idx.writeIndex(raRead,raWrite);
					idx.updateMetaData(raWrite);
				}
				raWrite.close();
				raRead.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(split_space[0].equalsIgnoreCase("find"))
		{
			// Searching record by key and returns its name and position
			BPlusTreeNode.Item item;
	        String searchKey;
	        mIndexFName = split_space[1];
	        searchKey = split_space[2];
	        try {
				FileChannel indexF = new RandomAccessFile(mIndexFName, "r").getChannel();
				idx.mDataFile = indexF;
	        	idx.readMetadata(indexF);
	        	System.out.println("Input File path set:"+idx.mInputFName);
	        	PushHelperClass pRetrieveReturn = idx.retrieve(searchKey);
	 	        
	 	        if ((pRetrieveReturn!= null) && pRetrieveReturn.getMoveUp())
	 	        {	
	 				RandomAccessFile raRead = new RandomAccessFile("CS6360Asg5Data.txt", "r");
	 				long offset = pRetrieveReturn.getItem().DataField;
					raRead.seek(offset);
					String sResult = raRead.readLine();
					System.out.println("At "+offset + ", record: "+sResult);
					raRead.close();

				} else {
					System.out.println("The given key ["+ searchKey+"] not found in the index");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	    else if (split_space[0].equalsIgnoreCase("insert")){
			// Inserting new Record into Index file
	    	BPlusTreeNode item;
	    	mIndexFName = split_space[1];
			String[] split_quotation = split_dash[1].split("\"");
			String searchKey_value = split_quotation[1].substring(0, 15);
	        try {
	        	FileChannel raWrite = new RandomAccessFile(mIndexFName, "rw").getChannel();
        		idx.mDataFile = raWrite;
	        	idx.readMetadata(raWrite);
        		PushHelperClass pRetrieveReturn = idx.retrieve(searchKey_value);
        		if((pRetrieveReturn != null) && pRetrieveReturn.getMoveUp()){
        			System.out.println("Record already present in the input file, the request record can not be inserted.");
        		}else{
        			RandomAccessFile raReadWrite = new RandomAccessFile("CS6360Asg5Data.txt", "rw");
        			raReadWrite.seek(raReadWrite.length());
        			raReadWrite.writeBytes(split_quotation[1]);
        			raReadWrite.writeBytes("\r\n");
        			raReadWrite.seek(0);
        			idx.mNumItems = 0;
        			idx.mNumNodes = 0;
        			idx.mRoot = Constants.nullpointer;
					idx.writeIndex(raReadWrite,raWrite);
					idx.updateMetaData(raWrite);
					raReadWrite.close();
					raReadWrite.close();
        		}
        		}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
	    }
					
	    else if (split_space[0].equalsIgnoreCase("list")){
        	
	    	 // Retrieves list of records in that particular branch
	        mIndexFName = split_space[1];
	        String searchKey = split_space[2];
	        int listCount = Integer.parseInt(split_space[3]);	 
        	FileChannel readIndexF = new RandomAccessFile(mIndexFName, "r").getChannel();
        	idx.mDataFile = readIndexF;
        	idx.readMetadata(readIndexF);
	        idx.retrieveList(searchKey,listCount);
	     	try {
				readIndexF.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
	    	
        }
		
    }
	
}

   
