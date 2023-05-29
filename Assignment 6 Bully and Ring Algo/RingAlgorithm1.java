import java.util.Scanner;

public class RingAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int numNodes = sc.nextInt();

        System.out.print("Enter the ID of the current node: ");
        int currentNodeID = sc.nextInt();

        System.out.println("Node " + currentNodeID + " is initiating the election...");

        int nextNode = (currentNodeID + 1) % numNodes;

        while (nextNode != currentNodeID) {
            System.out.println("Node " + currentNodeID + " sends an Election message to Node " + nextNode);
            nextNode = (nextNode + 1) % numNodes;
        }

        System.out.println("Election message has completed the ring.");

        boolean isLeader = true;

        for (int i = 0; i < currentNodeID; i++) {
            System.out.print("Wait for response from Node " + i + "... (1 for response, 0 for no response): ");
            int response = sc.nextInt();

            if (response == 1) {
                isLeader = false;
                System.out.println("Node " + i + " responded. Node " + i + " is the new leader.");
                break;
            }
        }

        if (isLeader) {
            System.out.println("No higher ID node responded. Node " + currentNodeID + " is the leader.");
        }
    }
}