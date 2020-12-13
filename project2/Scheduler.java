import java.util.LinkedList;

public class Scheduler {
    private LinkedList<Process> readyQueue;

    public Scheduler() {
        readyQueue = new LinkedList<>();
    }

    public void add(int index, Process p) {
        readyQueue.add(index, p);
    }

    public Process remove(int i){
        return readyQueue.remove(i);
    }

    public Process getProcess(int i) {
        return readyQueue.get(i);
    }

    public int size() {
        return readyQueue.size();
    }
}
