package branchandbound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jean
 */
public class Tree {

    private Node root;
    private Map<Integer, ObjectData> itens;
    private String[] item_names;
    private double[] values;
    private double[] weights;
    private final double max_Weight = 400;

    public Tree() {
        itens = new HashMap<>();
        item_names = new String[]{"map", "compass", "water", "sandwich", "glucose", "tin", "banana", "apple",
            "cheese", "beer", "suntan cream", "camera", "T-shirt", "trousers", "umbrella", "waterproof trousers",
            "waterproof overclothes", "note-case", "sunglasses", "towel", "socks", "book"};
        weights = new double[]{9, 13, 153, 50, 15, 68, 27, 39, 23, 52, 11, 32, 24, 48, 73,
            42, 43, 22, 7, 18, 4, 30};

        values = new double[]{150, 35, 200, 160, 60, 45, 60, 40, 30, 10, 70, 30, 15, 10, 40,
            70, 75, 80, 20, 12, 50, 10};

        fillMap();
    }

    private void fillMap() {
        ObjectData object;

        for (int index = 0; index < item_names.length; index++) {
            object = new ObjectData(item_names[index], values[index], weights[index]);
            itens.put(index, object);
        }
    }

    public void createTree() {
        List<Node> lista_temp = new ArrayList<>();
        List<Node> lista_atual = new ArrayList<>();
        Node nodeLeft;
        Node nodeRight;

        root = new Node(0, null, 0, 0, -1);
        lista_atual.add(root);

        for (int index = 0; index < item_names.length; index++) {
            lista_temp.clear();
            for (Node n : lista_atual) {
                if (n.getWeight() < max_Weight) {
                    nodeLeft = new Node(0, n, n.getWeight(), n.getValue(), index);
                    lista_temp.add(nodeLeft);

                    if (n.getWeight() + weights[index] < max_Weight) {
                        nodeRight = new Node(1, n, n.getWeight() + weights[index], n.getValue() + values[index], index);
                        lista_temp.add(nodeRight);
                    }

                }
            }

            lista_atual.clear();
            lista_atual = new ArrayList<>(lista_temp);

            /*System.out.println("\nindex: " + index);
            for (Node n : lista_atual) {
                System.out.println("Weight: " + n.getWeight() + " Value: " + n.getValue() + " Exists: " + n.getExists());
            }*/
        }

        double w = 0;
        double v = 0;

        Node winner = null;

        System.out.println("\n");
        for (Node n : lista_atual) {
            //System.out.println("Weight: " + n.getWeight() + " Value: " + n.getValue());
            if (n.getWeight() < max_Weight && n.getValue() > v) {
                w = n.getWeight();
                v = n.getValue();
                winner = n;
            }

        }
        System.out.println("=================== RESULTADO ===================");
        System.out.println("Weight: " + w);
        System.out.println("Value: " + v);
        System.out.println("\n===== Items =====");

        List<ObjectData> itens = new ArrayList<>();
        while (winner.getParent() != null) {
            if (winner.getExists() == 1) {
                ObjectData obj = new ObjectData(item_names[winner.getIndex()], values[winner.getIndex()], weights[winner.getIndex()]);
                itens.add(obj);
                System.out.println(item_names[winner.getIndex()] + " Weight: " + weights[winner.getIndex()] + " Value: " + values[winner.getIndex()]);
            }
            winner = winner.getParent();
        }
        
        ObjectData obj = new ObjectData("Resultado", v, w);
        itens.add(obj);
        
        Tela t = new Tela();
        t.setDataAndShow(itens);
        
    }
}

class Node {

    private int index;
    private double value;
    private double weight;
    private Node parent;
    private Node left;
    private Node right;
    private int exists;

    public Node(int exists, Node parent, double weight, double value, int index) {
        this.exists = exists;
        this.parent = parent;
        this.weight = weight;
        this.value = value;
        this.index = index;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getExists() {
        return exists;
    }

    public void setExists(int exists) {
        this.exists = exists;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

class ObjectData {

    private String name;
    private double value;
    private double weight;

    public ObjectData(String name, double value, double weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
