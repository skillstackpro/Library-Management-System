public class Book {
    private int id;
    private String title;
    private String author;
    private int copies;

    public Book(int id, String title, String author, int copies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.copies = copies;
    }

    // Getters (Values parhne ke liye)
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getCopies() { return copies; }

    // Setters (Values change karne ke liye)
    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + title + " by " + author + " (Available: " + copies + ")";
    }
}