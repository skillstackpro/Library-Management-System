class StudentNode {
    String name;
    StudentNode next;

    StudentNode(String name) {
        this.name = name;
        this.next = null;
    }
}

public class WaitListQueue {
    private StudentNode head = null;
    private StudentNode tail = null;

    public void addStudent(String name) {
        StudentNode node = new StudentNode(name);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        System.out.println("Student " + name + " added to waitlist.");
    }

    public void processNextStudent() {
        if (head == null) {
            System.out.println("No students in waitlist.");
            return;
        }
        System.out.println("Notifying student: " + head.name + " (Book is available!)");
        head = head.next;
        if (head == null)
            tail = null;
    }

    public void viewWaitlist() {
        if (head == null) {
            System.out.println("Waitlist is empty.");
            return;
        }
        System.out.println("\n--- Current Waitlist (Queue FIFO) ---");
        StudentNode temp = head;
        while (temp != null) {
            System.out.print(temp.name + " -> ");
            temp = temp.next;
        }
        System.out.println("END");
    }
}