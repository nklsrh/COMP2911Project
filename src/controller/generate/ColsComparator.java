package controller.generate;

import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * This class implements the comparator interface to compare the columns of Square objects and determine their
 * order.
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 *
 */
public class ColsComparator implements Comparator<Square>{
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Square c1, Square c2) {
		
		if (c1.col < c2.col){
            return -1;
        }
        if (c1.col >= c2.col){
            return 1;
        }
        return 0;
	}
}
