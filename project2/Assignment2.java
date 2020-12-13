import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment2 {
    public static void main (String args []) {

        try {
            FileInputStream file = new FileInputStream("assignment2.txt");
            Scanner reader = new Scanner(file);

            ArrayList<Process> allProcesses = new ArrayList<>();

            while(reader.hasNextLine()) {
                String[] data = reader.nextLine().split("\\s+");
                int processID = Integer.parseInt(data[0]);
                int arrivalTime = Integer.parseInt(data[1]);
                int burstLength = Integer.parseInt(data[2]);
                Process process = new Process(processID, arrivalTime, burstLength);
                
                allProcesses.add(process);
            }

            reader.close();

            switch(args[0].toUpperCase()) {
                case "FCFS":
                    FCFS fcfs = new FCFS(allProcesses);
                    fcfs.Simulate();
                break;
                case "SRTF":
                    SRTF srtf = new SRTF(allProcesses);
                    srtf.Simulate();
                break;
                case "RR":
                    if(args.length > 0) {
                        RR rr = new RR(allProcesses, Integer.parseInt(args[1]));
                        rr.Simulate();
                    }
                break;
                default:
                    throw new IllegalArgumentException("FCFS, SRTF or RR as parameter");
            }

        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}