import java.util.LinkedList;

public class PCBStructureList {
    private int parentIndex;
    private LinkedList<Integer> childrenList;

    public PCBStructureList() {
        parentIndex = -1;
        childrenList = new LinkedList<>();
    }

    public PCBStructureList(int parentIndex) {
        this.parentIndex = parentIndex;
        childrenList = new LinkedList<>();
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public Integer[] getChildrenList() {
        Integer[] childrenArray = childrenList.toArray(new Integer[childrenList.size()]);
        
        return childrenArray;
    }

    public void deleteChildrenList() {
        childrenList.clear();
    }

    public void deleteNode(int index) {
        childrenList.removeFirstOccurrence(index);
    }

    public void addChild(int index) {
        childrenList.add(index);
    }

    public String toString() {
        String result = "";
        
        for(int i = 0; i < childrenList.size(); i++) {
            if(childrenList.get(i) != null)
                result += "table["+childrenList.get(i)+"], ";
        }
        
        result += "\n";
        return result;
    }
}