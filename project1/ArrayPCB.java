public class ArrayPCB {
    private PCBStructure [] table;

    public ArrayPCB(int size) {
        table = new PCBStructure[size];
    }

    public Boolean Create(int index) {
        int availableIndex = findAvailableIndex();

        if(table[index] == null) {
            //System.out.println("created a new PCB at table[" + index + "]");
            table[index] = new PCBStructure();
            
            return true;
        }
        else if(availableIndex == -1) {
            System.out.println("cannot add more processes, table is full");
        }
        else if(table[index].getFirstChild() == -1){
            //System.out.println("added a PCB child to table[" + index + "]");
            table[availableIndex] = new PCBStructure();
            table[availableIndex].setParent(index);
            table[index].setFirstChild(availableIndex);
            
            return true;
        } else {
            //System.out.println("added a PCB child to table[" + index + "]");
            table[availableIndex] = new PCBStructure();
            table[availableIndex].setParent(index);
            int tmp = table[index].getFirstChild();
            int lastChildIndex = tmp;

            for(;tmp != -1; tmp = table[tmp].getYoungerSibling())
                lastChildIndex = tmp;

            table[availableIndex].setOlderSibling(lastChildIndex);
            table[lastChildIndex].setYoungerSibling(availableIndex);

            return true;
        }
        
        return false;
    }

    public void DestroyHelper(int index, int original) {
        if(table[index].getFirstChild() != -1)
            DestroyHelper(table[index].getFirstChild(), index);
            
        int y_s = table[index].getYoungerSibling();
        int o_s = table[index].getOlderSibling();
    
        if(y_s != -1 && table[y_s].getParent() == original)
            DestroyHelper(y_s, original);

        if (y_s != -1 && table[index].getParent() != original)
            table[y_s].setOlderSibling(table[index].getOlderSibling());
        
        if (o_s != -1 && table[index].getParent() != original)
            table[o_s].setYoungerSibling(table[index].getYoungerSibling());
        
        if (index != -1) {
            int originalParent = table[original].getParent();
            if(originalParent != -1) {
                if(table[originalParent].getFirstChild() == index)
                table[table[index].getParent()].setFirstChild(table[index].getYoungerSibling());

            }
        }

        table[index] = null;
    }

    public void Destroy(int index) {
        DestroyHelper(index, index);
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
        System.out.println(this);
        long stopTime = System.nanoTime();
        System.out.print("Method 2 w/o Linked Lists seconds = " + ((double)(stopTime - startTime)/1000000000.0));
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < table.length; i++) {
            if(table[i] != null) {
                result += "table["+i+"]\n";
                result += "parent: table["+table[i].getParent()+"]\n";
                result += "first child: table["+table[i].getFirstChild()+"]\n";
                result += "younger sibling: table["+table[i].getYoungerSibling()+"]\n";
                result += "older sibling: table["+table[i].getOlderSibling()+"]\n\n";
            }
        }
        return result;
    }
}
