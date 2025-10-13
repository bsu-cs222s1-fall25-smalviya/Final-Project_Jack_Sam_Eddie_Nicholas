package edu.bsu.cs;

public class Pair {
    String left;
    String right;

    public Pair(String left, String right){
        this.left = left;
        this.right = right;
    }

    protected String getLeft(){
        return this.left;
    }

    protected String getRight(){
        return this.right;
    }
}
