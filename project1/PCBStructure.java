public class PCBStructure {
    private int parent, first_child, younger_sibling, older_sibling;

    public PCBStructure() {
        parent = first_child = younger_sibling = older_sibling = -1;
    }

    public PCBStructure(int p, int f_c, int y_s, int o_s) {
        parent = p;
        first_child = f_c;
        younger_sibling = y_s;
        older_sibling = o_s;
    }

    public int getParent() { return parent; }

    public int getFirstChild() { return first_child; }

    public int getYoungerSibling() { return younger_sibling; }

    public int getOlderSibling() { return older_sibling; }

    public void setParent(int p) { parent = p; }

    public void setFirstChild(int f_c) { first_child = f_c; }

    public void setYoungerSibling(int y_s) { younger_sibling = y_s; }

    public void setOlderSibling(int o_s) { older_sibling = o_s; }
}
