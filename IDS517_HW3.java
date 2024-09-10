public class IDS517_HW3<T> {
    private static class LLNode<U> {
        protected U info;
        protected LLNode<U> link;

        public LLNode(U info) {
            this.info = info;
            this.link = null;
        }

        public U getInfo() {
            return info;
        }

        public LLNode<U> getLink() {
            return link;
        }

        public void setLink(LLNode<U> link) {
            this.link = link;
        }
    }
    private LLNode<T> head;

    public IDS517_HW3() {
        this.head = null;
    }
    // Method to add a node at the front of the linked list
    public void addAtFront(T data) {
        LLNode<T> newNode = new LLNode<>(data);
        newNode.setLink(head);
        head = newNode;
    }
    public void printForward() {
        LLNode<T> current = head;
        while (current != null) {
            System.out.print(current.getInfo() + " -> ");
            current = current.getLink();
        }
        System.out.println("null");
    }
    public void printReverse() {
        recPrintList(head);
    }
    private void recPrintList(LLNode<T> listRef) {
        if (listRef != null) {
            recPrintList(listRef.getLink());
            System.out.println(listRef.getInfo());
        }
    }
    public static void main(String[] args) {
    	IDS517_HW3<String> list = new IDS517_HW3<>();

        // Add elements to the linked list
        int N = 40; //
        for (int i = N; i > 0; i--) {
            list.addAtFront("N" + i);
        }
        // Print the list in order
        System.out.println("Printing forward:");
        list.printForward();

        // Print the list in reverse order
        System.out.println("\nPrinting in reverse:");
        list.printReverse();
    }
}
