public class Process {
    private int processID;
    private int arrivalTime;
    private int burstLength;

    public Process() {
        processID = arrivalTime = burstLength = -1;
    }

    public Process(int processID, int arrivalTime, int burstLength) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.burstLength = burstLength;
    }

    public void setProcessID(int id) {
        processID = id;
    }

    public void setArrivalTime(int time) {
        arrivalTime = time;
    }

    public void setBurstLength(int length) {
        burstLength = length;
    }

    public int getProcessID() { return processID; }

    public int getArrivalTime() { return arrivalTime; }

    public int getBurstLength() { return burstLength; }
}