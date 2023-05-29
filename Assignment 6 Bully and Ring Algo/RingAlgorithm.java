import java.util.*;

public class RingAlgorithm {
    private int numProcesses;
    private int[] processes;
    private int leader;

    public RingAlgorithm(int numProcesses) {
        this.numProcesses = numProcesses;
        this.processes = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            processes[i] = i;
        }
        this.leader = numProcesses - 1;
    }

    public void startElection(int failedProcess) {
        System.out.println("Process " + failedProcess + " has failed!");

        int sender = (failedProcess + 1) % numProcesses;
        boolean leaderFound = false;

        while (!leaderFound) {
            System.out.println("Sending election message from " + sender + " to " + (sender + 1) % numProcesses);
            int receiver = (sender + 1) % numProcesses;
            if (sendMessage(sender, receiver)) {
                sender = receiver;
            } else {
                for (int i = 0; i < numProcesses; i++) {
                    if (i != sender && sendMessage(sender, i)) {
                        sender = i;
                    }
                }
                leaderFound = true;
            }
        }
        System.out.println("Process " + leader + " is the new leader!");
    }

    public boolean sendMessage(int source, int destination) {
        System.out.println("Sending message from " + source + " to " + destination);
        if (destination == leader) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int numProcesses = 5;
        RingAlgorithm algorithm = new RingAlgorithm(numProcesses);
        algorithm.startElection(2);
    }
}
