package tp1.logic;

public enum Move {
	
	LEFT(-1,0), LLEFT(-2,0), RIGHT(1,0), RRIGHT(2,0), DOWN(0,1), UP(0,-1), NONE(0,0);
	
	private int x;
	private int y;
	
	private Move(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	 public Move flip() {
	    	if(this == Move.LEFT) {
	    		return Move.RIGHT;
	    	}else if(this == Move.RIGHT) {
	    		return Move.LEFT;
	    		
	    	}else return Move.NONE;
	    }
	
	 public static Move valueOfIgnoreCase(String param) {
		 for (Move m : Move.values())
	            if (m.name().equalsIgnoreCase(param)) 
	            	return m;
	 	
	 	return null;
	 }
	 
}