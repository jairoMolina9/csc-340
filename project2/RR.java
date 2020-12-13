import java.util.ArrayList;

public class RR extends Scheduler {
    int sysTime;
    ArrayList<Process> allProcesses;
    int qTime;

    public RR(ArrayList<Process> allProcesses, int qTime) {
        super();
        sysTime = 0;
        this.allProcesses = allProcesses;
        this.qTime = qTime;
    }

    public void Simulate() {
        System.out.println("Scheduling Algorithm: Round Robin (" + qTime + ")");
        System.out.println("============================================================");

        Process curr = null;
        int burst = -1;
        int time = 0;

        double burstCounter = 0.0;
        double arrivalCounter = 0.0;
        double departCounter = 0.0;
        double responseCounter = 0;

        int visitedAll = allProcesses.size();
        ArrayList<Process> initiatedProcesses = new ArrayList<>();

        while(true) {
            
            if(visitedAll != 0) {
                for(Process p : allProcesses) {
                    if(p.getArrivalTime() == sysTime) {
                        super.add(super.size(),p);
                        arrivalCounter += sysTime;
                        burstCounter += p.getBurstLength();
                        visitedAll--;
                    }
                }
            }

            if(super.size() > 0) {
                if(curr == null) {
                    curr = super.remove(0);
                } else {
                    if(burst == 0) {
                        curr = super.remove(0);
                        time = 0;
                    }
                    if (time == qTime) {
                        super.add(super.size(), curr);
                        curr = super.remove(0);
                        time = 0;
                    }
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
                time++;
            }

            if(burst == 0) {
                System.out.print("<system time \t" + (sysTime+1));
                System.out.print("> process \t" + curr.getProcessID());
                System.out.print(" is finished....\n");

                departCounter += (sysTime+1);
            }

            if( (burst == 0 && super.size() == 0)) {
                System.out.print("<system time \t" + (++sysTime));
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
}