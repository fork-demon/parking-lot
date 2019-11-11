package services.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pair {

    private String slotNumber;
    private String regsitrationNumber;
    private Map<String,Object> params = new HashMap<>();

    public Pair(){
    }

    public Pair(String left,String right){
    this.slotNumber = left;
    this.regsitrationNumber = right;

    }

    public Pair(String left,String right,Map<String,Object> attributes){
        this.slotNumber = left;
        this.regsitrationNumber = right;
        this.params = attributes;
    }

    public static Pair of (String left,String right){
        return new Pair(left,right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return slotNumber.equals(pair.slotNumber) &&
                regsitrationNumber.equals(pair.regsitrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotNumber, regsitrationNumber);
    }

    public String getLeft(){
      return slotNumber;
    }

    public String getRight(){
        return regsitrationNumber;
    }

}
