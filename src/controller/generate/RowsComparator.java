package controller.generate;

import java.util.Comparator;

public class RowsComparator implements Comparator<Square>{

	@Override
	public int compare(Square c1, Square c2) {
		
		if (c1.row < c2.row){
            return -1;
        }
        if (c1.row >= c2.row){
            return 1;
        }
        return 0;
	}

}
