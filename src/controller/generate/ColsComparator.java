package controller.generate;

import java.util.Comparator;

/**
 * This class implements the comparator interface to compare the columns of Square objects and determine their
 * order.
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 *
 */
public class ColsComparator implements Comparator<Square>{
	
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
