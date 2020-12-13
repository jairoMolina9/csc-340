import java.util.ArrayList;

public class FCFS extends Scheduler {
    int sysTime;
    ArrayList<Process> allProcesses;

    public FCFS(ArrayList<Process> allProcesses) {
        super();
        sysTime = 0;
        this.allProcesses = allProcesses;
    }

    public void Simulate() {
        System.out.println("Scheduling Algorithm: First Come First Server");
        System.out.println("============================================================");
        
        Process curr = null;
        int burst = -1;

        double burstCounter = 0;
        double avgWaitingTime = 0.0;
        double avgResponseTime = 0.0;
        double avgTurnAroundTime = 0.0;

        int visitedAll = allProcesses.size();

        while(true) {
            
            if(visitedAll != 0) {
                for(Process p : allProcesses) {
                    if(p.getArrivalTime() == sysTime) {
                        super.add(super.size(),p);
                        visitedAll--;
                    }
                }
            }

            if(super.size() > 0) {
                if(curr == null) {
                    curr = super.remove(0);
                    burst = curr.getBurstLength();
                   
                    avgTurnAroundTime += burst;
                } else {
                    if(burst == 0) {
                        burstCounter += curr.getBurstLength();
                        
                        curr = super.remove(0);
                        burst = curr.getBurstLength();
                        
                        avgResponseTime += (sysTime - curr.getArrivalTime());
                        avgWaitingTime += (burstCounter - curr.getArrivalTime());
                        avgTurnAroundTime += (burstCounter - curr.getArrivalTime()) + burst;
                    }
                }
            }
            
            if(burst != -1 && 0 != burst--) {
                System.out.print("<system time \t" + sysTime);
                System.out.print("> process \t" + curr.getProcessID());
                System.out.print(" is running\n");
            }

            if(burst == 0 && super.size() != 0) {
                System.out.print("<system time \t" + (sysTime+1));
                System.out.print("> process \t" + curr.getProcessID());
                System.out.print(" is finished....\n");
            }
            
            if( (burst == 0 && super.size() == 0)) {
                burstCounter += curr.getBurstLength();

                avgWaitingTime = Math.round((avgWaitingTime/allProcesses.size()) * 100.0) / 100.0;
                avgResponseTime = Math.round((avgResponseTime/allProcesses.size()) * 100.0) / 100.0;
                avgTurnAroundTime = Math.round((avgTurnAroundTime/allProcesses.size()) * 100.0) / 100.0;

                System.out.print("<system time \t" + (++sysTime));
                System.out.print("> All processes finished.....\n");
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