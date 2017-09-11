package com.database.index;

final class SearchHelperClass {
	    private final boolean found;
	    private final int location;

	    public SearchHelperClass(boolean found, int location) {
	        this.found = found;
	        this.location = location;
	    }
	    public boolean getFound() {
	        return found;
	    }

	    public int getLocation() {
	        return location;
	    }

}
