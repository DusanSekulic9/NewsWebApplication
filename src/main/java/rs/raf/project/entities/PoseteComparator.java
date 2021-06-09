package rs.raf.project.entities;

import java.util.Comparator;

public class PoseteComparator implements Comparator<Vest>{

    @Override
    public int compare(Vest o1, Vest o2) {
        if(o1.getPosete() > o2.getPosete()){
            return 1;
        }else if(o1.getPosete() == o2.getPosete()){
            return 0;
        }else{
            return -1;
        }
    }
}
