package controller.generate;

import java.util.Comparator;

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
