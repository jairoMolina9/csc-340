import java.util.ArrayList;

public class SRTF extends Scheduler {
    int sysTime;
    ArrayList<Process> allProcesses;

    public SRTF(ArrayList<Process> allProcesses) {
        super();
        sysTime = 0;
        this.allProcesses = allProcesses;
    }

    public void Simulate() {
        System.out.println("Scheduling Algorithm: Shortest Remaining Time First");
        System.out.println("============================================================");

        Process curr = null;
        int burst = -1;

        double burstCounter = 0.0;
        double arrivalCounter = 0.0;
        double departCounter = 0.0;
        double responseCounter = 0.0;

        int visitedAll = 0;
        ArrayList<Process> initiatedProcesses = new ArrayList<>();

        while(true) {

            if(visitedAll != allProcesses.size()) {
                for(Process p : allProcesses) {
                    if(p.getArrivalTime() == sysTime) {
                        super.add(super.size(),p);
                        arrivalCounter += sysTime;
                        burstCounter += p.getBurstLength();
                        visitedAll++;
                    }
                }
            }

            if(super.size() > 0) {
                if(curr == null) {
                    curr = getShortestProcess(super.getProcess(0));
                } else {
                    curr = getShortestProcess(curr);
                }

                if(!initiatedProcesses.contains(curr)){
                    initiatedProcesses.add(curr);
                    responseCounter += sysTime;
                }
                
                burst = curr.getBurstLength();
            }

            if(0 != burst && burst != -1) {
                System.out.print("<system time \t" + sysTime);
                System.out.print("> process \t" + curr.getProcessID());
                System.out.print(" is running\n");

                curr.setBurstLength(burst-1);
                burst--;
            }

            if(burst == 0 && super.size() != 0) {
                System.out.print("<system time \t" + (sysTime+1));
                System.out.print("> process \t" + curr.getProcessID());
                System.out.print(" is finished....\n");

                departCounter += (sysTime+1);
            }

            if( (burst == 0 && super.size() == 0)) {
                System.out.print("<system time \t" + (sysTime));
                System.out.print("> All processes finished.....\n");

                double avgWaitingTime =  Math.round(((departCounter - arrivalCounter - burstCounter)/allProcesses.size()) * 100.0) / 100.0;
                double avgResponseTime = Math.round(((responseCounter - arrivalCounter)/allProcesses.size()) * 100.0) / 100.0;
                double avgTurnAroundTime = Math.round(((departCounter - arrivalCounter)/allProcesses.size()) * 100.0) / 100.0;

                System.out.println("============================================================");
                System.out.println("Average CPU usage:\t " + ((burstCounter / sysTime) * 100.00) + "%");
                System.out.println("Average waiting time:\t " + avgWaitingTime);
                System.out.println("Average response time:\t " + avgResponseTime);
                System.out.println("Average turnaround time: " + avgTurnAroundTime);
                System.out.println("============================================================");

                 break;
            }
            sysTime++;
        }
    }

    public Process getShortestProcess(Process curr) {
        int tmpSize = super.size();
        int i = 0;
        
        while(i < tmpSize) {
            if((curr.getBurstLength() > super.getProcess(i).getBurstLength()) || curr.getBurstLength() == 0) {
                int tmpBurst = curr.getBurstLength();
                Process p = curr;
                curr = super.remove(i);
                
                if(tmpBurst != 0)
                    super.add(i,p);
            }
            
            i++;
            
            if(tmpSize != super.size()) {
                i = 0;
                tmpSize = super.size();
            }
        }
        return curr;
    }
}