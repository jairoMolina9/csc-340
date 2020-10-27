public class ArrayPCBList {
    private PCBStructureList [] table;

    public ArrayPCBList (int size) {
        table = new PCBStructureList[size];
    }
    
    public Boolean Create(int index) {
        int availableIndex = findAvailableIndex();

        if(table[index] == null) {
            //System.out.println("created a new PCB at table[" + index + "]");
            table[index] = new PCBStructureList();
            
            return true;
        }
        else if (availableIndex == -1) {
            System.out.println("cannot add more processes, table is full");
        }
        else {
            //System.out.println("added a PCB child to table[" + index + "]");
            table[index].addChild(availableIndex);
            table[availableIndex] = new PCBStructureList(index);
            return true;
        }

        return false;
    }

    public void Destroy(int index) {
        int parentIndex = table[index].getParentIndex();
        Integer[] childrenIndexes = table[index].getChildrenList();

        if(parentIndex != -1)
            table[parentIndex].deleteNode(index);

        if(childrenIndexes != null) {
            for(Integer i : childrenIndexes)
                Destroy(i);
        }

        table[index].deleteChildrenList();
        table[index] = null;
    }

    public int findAvailableIndex() {
        int index = 0;
        
        for(; index < table.length; index++) {
            if(table[index] == null)
                break;
        }

        return index;
    }


    public void Execute() {
        long startTime = System.nanoTime();

        for(int i = 0; i < 1000000; i++) {
            Create(0);
            Create(0);
            Create(0);
            Create(0);
            Destroy(1);
            Create(2);
            Create(2);
            Destroy(0);
            Create(2);
            Create(2);
            Create(1);
            Create(1);
            Create(0);
            Destroy(1);
            Destroy(2);
        }
        
        long stopTime = System.nanoTime();
        System.out.print("Method 1 w/ Linked Lists in seconds = " + ((double)(stopTime - startTime)/1000000000.0));
    }

    public String toString() {
        String result ="";

        for(int i = 0; i < table.length; i++) {
            if(table[i] != null) {
                result += "table[" + i + "]\n";
                result += "parent: table[" + table[i].getParentIndex() +"]\n";
                result += "children: " + table[i] + "\n";
            }
        }
        
        return result;
    }
}