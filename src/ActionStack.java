public class ActionStack {
    private String[] stack = new String[50];
    private int top = -1;

    public void push(String action) {
        if (top < stack.length - 1) {
            stack[++top] = action;
        } else {
            System.out.println("History Full! Oldest logs might be lost.");
        }
    }

    public void display() {
        if (top == -1) {
            System.out.println("No recent history.");
            return;
        }
        System.out.println("\n--- Recent Actions (Stack LIFO) ---");
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}