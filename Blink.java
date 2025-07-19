import java.util.Scanner;

class SongQueue {
    private String[] queue;
    private int front, rear, size;

    public SongQueue(int capacity) {
        queue = new String[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(String song) {
        if (size == queue.length) {
            System.out.println("Queue is full. Cannot add more songs.");
            return;
        }
        rear = (rear + 1) % queue.length;
        queue[rear] = song;
        size++;
    }

    public String dequeue() {
        if (size == 0) {
            return "Queue is empty.";
        }
        String song = queue[front];
        front = (front + 1) % queue.length;
        size--;
        return song;
    }

    public boolean search(String songName) {
        for (int i = 0; i < size; i++) {
            int idx = (front + i) % queue.length;
            if (queue[idx].equalsIgnoreCase(songName)) {
                return true;
            }
        }
        return false;
    }

    public void display() {
        if (size == 0) {
            System.out.println("No songs in the queue.");
            return;
        }
        System.out.println("Songs in your BLACKPINK queue:");
        for (int i = 0; i < size; i++) {
            int idx = (front + i) % queue.length;
            System.out.println((i + 1) + ". " + queue[idx]);
        }
    }
}

public class Blink {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SongQueue queue = new SongQueue(10);

        System.out.println("Hello! What's your name? ");
        String name = sc.nextLine();

        queue.enqueue("Stay");
        queue.enqueue("Jump");
        queue.enqueue("Don't Know What To Do");
        queue.enqueue("Pretty Savage");
        queue.enqueue("How You Like That");
        queue.enqueue("Kill This Love");

        System.out.println("\nWelcome, " + name);
        System.out.println("This is your BLACKPINK Songs Recommendation Queue!");

        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Display Queue");
            System.out.println("2. Remove a song");
            System.out.println("3. Search for a song");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number (1–4): ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine(); // absorb leftover newline

            switch (choice) {
                case 1:
                    queue.display();
                    break;
                case 2:
                    String removed = queue.dequeue();
                    System.out.println("Removed: " + removed);
                    break;
                case 3:
                    System.out.print("Enter song name to search: ");
                    String songName = sc.nextLine();
                    boolean found = queue.search(songName);
                    if (found) {
                        System.out.println(songName + " is in your queue.");
                    } else {
                        System.out.println(songName + " is not found.");
                    }
                    break;
                case 4:
                    System.out.println("Thanks for using BLACKPINK Recommender. Goodbye, " + name + "!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}