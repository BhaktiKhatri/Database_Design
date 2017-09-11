package com.database.index;

final class PushHelperClass {
		private final boolean moveUp;
		private final BPlusTreeNode.Item item ;
		private final long itemRightChild;
		
		public PushHelperClass (boolean moveUp,BPlusTreeNode.Item item,long itemRightChild){
			this.moveUp = moveUp;
			this.item = item;
			this.itemRightChild = itemRightChild;
		}
		
		public boolean getMoveUp(){
			return this.moveUp;
		}
		
		public BPlusTreeNode.Item getItem(){
			return this.item;
		}

	   /* public PushHelperClass (String keyField, long dataField, long itemRightChild) {
	        this.item.KeyField = keyField;
	        this.item.DataField = dataField;
	        this.itemRightChild = itemRightChild;
	    }
	    public String getkeyField() {
	        return item.KeyField;
	    }

	    public long getDataField() {
	        return item.DataField;
	    }*/
	    
	    public long getItemRightChild(){
	    		return itemRightChild;
	    }
}
