import java.util.Scanner;

public class LibrarySystem {

    static LibraryDatabase db = new LibraryDatabase();
    static WaitListQueue waitlist = new WaitListQueue();
    static ActionStack history = new ActionStack();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-loading some books for testing
        db.addBook(101, "Java Programming", "James Gosling", 5);
        db.addBook(102, "Data Structures", "Robert Lafore", 2);

        while (true) {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Student");
            System.out.println("3. Exit System");
            System.out.print("Enter choice: ");

            int loginChoice = sc.nextInt();
            sc.nextLine(); // Fix scanner bug

            if (loginChoice == 1) {
                System.out.print("Enter Admin Password (admin123): ");
                String pass = sc.nextLine();
                if (pass.equals("admin123")) {
                    adminMenu();
                } else {
                    System.out.println("Wrong password!");
                }
            } else if (loginChoice == 2) {
                System.out.print("Enter Student Name: ");
                String studentName = sc.nextLine();
                studentMenu(studentName);
            } else if (loginChoice == 3) {
                System.out.println("Exiting... Have a nice day!");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    public static void adminMenu() {
        while (true) {
            System.out.println("\n--- ADMIN DASHBOARD ---");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Process Waitlist");
            System.out.println("4. View Waitlist");
            System.out.println("5. View History");
            System.out.println("6. Display All Books");
            System.out.println("7. Logout");
            System.out.print("Admin Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Copies: ");
                    int copies = sc.nextInt();
                    db.addBook(id, title, author, copies);
                    history.push("Admin added book: " + title);
                    break;
                case 2:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    if (db.deleteBook(did)) {
                        System.out.println("Deleted successfully.");
                        history.push("Admin deleted book ID: " + did);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    waitlist.processNextStudent();
                    break;
                case 4:
                    waitlist.viewWaitlist();
                    break;
                case 5:
                    history.display();
                    break;
                case 6:
                    db.displayAll();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void studentMenu(String studentName) {
        while (true) {
            System.out.println("\n--- STUDENT DASHBOARD (" + studentName + ") ---");
            System.out.println("1. Search Book");
            System.out.println("2. Rent Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Available Books");
            System.out.println("5. Logout");
            System.out.print("Student Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID to Search: ");
                    int sid = sc.nextInt();
                    int idx = db.binarySearch(sid);
                    if (idx != -1) {
                        Book b = db.getBook(idx);
                        System.out.println("Found: " + b.getTitle() + " by " + b.getAuthor() + " (Copies: " + b.getCopies() + ")");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Book ID to Rent: ");
                    int iid = sc.nextInt();
                    int iidx = db.binarySearch(iid);
                    if (iidx != -1) {
                        Book b = db.getBook(iidx);
                        if (b.getCopies() > 0) {
                            b.setCopies(b.getCopies() - 1); // Encapsulation Use
                            System.out.println("Book Rented Successfully!");
                            history.push(studentName + " rented book ID: " + iid);
                        } else {
                            System.out.print("Out of stock! Join waitlist? (1 for Yes): ");
                            int w = sc.nextInt();
                            if (w == 1) waitlist.addStudent(studentName);
                        }
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Book ID to Return: ");
                    int rid = sc.nextInt();
                    int ridx = db.binarySearch(rid);
                    if (ridx != -1) {
                        Book b = db.getBook(ridx);
                        b.setCopies(b.getCopies() + 1); // Encapsulation Use
                        System.out.println("Book Returned. Thank you!");
                        history.push(studentName + " returned book ID: " + rid);
                    } else {
                        System.out.println("This book does not belong to our library.");
                    }
                    break;
                case 4:
                    db.displayAll();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}