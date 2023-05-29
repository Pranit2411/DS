import java.util.*;

public class BullyAlgorithm {
    private int numProcesses;
    private boolean[] processes;
    private int leader;

    public BullyAlgorithm(int numProcesses) {
        this.numProcesses = numProcesses;
        this.processes = new boolean[numProcesses];
        Arrays.fill(processes, true);
        this.leader = numProcesses - 1;
    }

    public void startElection(int failedProcess) {
        System.out.println("Process " + failedProcess + " has failed!");

        for (int i = failedProcess + 1; i < numProcesses; i++) {
            if (processes[i]) {
                System.out.println("Sending election message from " + i + " to " + leader);
                if (sendMessage(i, leader)) {
                    leader = i;
                }
            }
        }
        System.out.println("Process " + leader + " is the new leader!");
    }

    public boolean sendMessage(int source, int destination) {
        System.out.println("Sending message from " + source + " to " + destination);
        return processes[destination];
    }

    public static void main(String[] args) {
        int numProcesses = 5;
        BullyAlgorithm algorithm = new BullyAlgorithm(numProcesses);
        algorithm.startElection(2);
    }
}
