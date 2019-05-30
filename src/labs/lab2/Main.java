package labs.lab2;

public class Main {
    public static void main(String[] args) {
        Tree a = new Tree();
        Tree b = new Tree();
        for (int i = 0; i < 10; i++) {
            a.add((int) (Math.random() * 50));
            b.add((int) (Math.random() * 50));
        }
        System.out.print("Tree a : ");
        soutPr(a);
        System.out.print("Tree b : ");
        soutSim(b);
        operationUObr(a, b.getRoot());
        System.out.print("New tree a : ");
        soutPr(a);
    }

    private static void operationUObr(Tree tree, Tree.Node node) {
        if (node == null) {
            return;
        }
        operationUObr(tree, node.getLeftChild());
        tree.add(node.getValue());
        operationUObr(tree, node.getRightChild());
    }

    private static void soutPr(Tree tree) {
        System.out.print("[");
        String string = pr(tree.getRoot());
        System.out.println(string.substring(0, string.length() - 2) + "]");
    }

    private static String pr(Tree.Node node) {
        if (node == null) {
            return "";
        }
        return node.getValue() + ", " +
                pr(node.getLeftChild()) +
                pr(node.getRightChild());
    }

    private static void soutSim(Tree tree) {
        System.out.print("[");
        String string = sim(tree.getRoot());
        System.out.println(string.substring(0, string.length() - 2) + "]");
    }

    private static String sim(Tree.Node node) {
        if (node == null) {
            return "";
        }
        return pr(node.getLeftChild()) +
                pr(node.getRightChild()) +
                node.getValue() + ", ";
    }
}
