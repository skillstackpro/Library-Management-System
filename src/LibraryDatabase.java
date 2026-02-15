public class LibraryDatabase {
    private Book[] books = new Book[100];
    private int count = 0;

    // ADD BOOK - Inserts and Sorts automatically
    public void addBook(int id, String title, String author, int copies) {
        if (count == books.length) {
            System.out.println("Library is full!");
            return;
        }
        books[count++] = new Book(id, title, author, copies);
        sortBooks(); // Keeping data sorted for Binary Search
    }

    // BUBBLE SORT - O(n^2)
    public void sortBooks() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (books[j].getId() > books[j + 1].getId()) { // Using Getter
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
    }

    // BINARY SEARCH - O(log n)
    public int binarySearch(int id) {
        int left = 0;
        int right = count - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (books[mid].getId() == id)
                return mid;
            else if (books[mid].getId() < id)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    // DELETE BOOK
    public boolean deleteBook(int id) {
        int index = binarySearch(id);
        if (index == -1)
            return false;
        for (int i = index; i < count - 1; i++) {
            books[i] = books[i + 1];
        }
        books[count - 1] = null;
        count--;
        return true;
    }

    public Book getBook(int index) {
        if (index >= 0 && index < count)
            return books[index];
        return null;
    }

//    public void displayAll() {
//        if (count == 0) {
//            System.out.println("Library is empty.");
//            return;
//        }
//        System.out.println("\nID\tTitle\t\tAuthor\t\tCopies");
//        for (int i = 0; i < count; i++) {
//            System.out.println(books[i].getId() + "\t" + books[i].getTitle() + "\t\t" + books[i].getAuthor() + "\t\t" + books[i].getCopies());
//        }
//    }
}