import java.util.Scanner;

public class TokenRing {
    private int numNodes;
    private int[] nodes;
    private int token;

    public TokenRing(int numNodes) {
        this.numNodes = numNodes;
        this.nodes = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            nodes[i] = i;
        }
        this.token = 0;
    }

    public void send(int sender, int receiver, int data) {
        System.out.print("Token passing: ");
        for (int i = token, j = token; (i % numNodes) != sender; i++, j = (j + 1) % numNodes) {
            System.out.print(j + "->");
        }
        System.out.println(sender);
        System.out.println("Sender " + sender + " sending data: " + data);
        for (int i = sender + 1; i != receiver; i = (i + 1) % numNodes) {
            System.out.println("data " + data + " forwarded by " + i);
        }
        System.out.println("Receiver " + receiver + " received data: " + data);
        token = sender;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the num of nodes:");
        int numNodes = scan.nextInt();
        TokenRing algorithm = new TokenRing(numNodes);

        int sender, receiver, data, choice;
        do {
            System.out.println("Enter sender:");
            sender = scan.nextInt();
            System.out.println("Enter receiver:");
            receiver = scan.nextInt();
            System.out.println("Enter data:");
            data = scan.nextInt();
            algorithm.send(sender, receiver, data);
            System.out.println("Do you want to send again? Enter 1 for Yes and 0 for No:");
            choice = scan.nextInt();
        } while (choice == 1);
    }
}
