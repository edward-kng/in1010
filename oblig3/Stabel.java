public class Stabel<T> extends Lenkeliste<T> {
    @Override
    public void leggTil(T x) {
        Node nyNode = new Node(x);

        if (stoerrelse > 0) {
            nyNode.neste = start;
        }
        
        start = nyNode;
        stoerrelse++;
    }

    @Override
    public String toString() {
        return "Stabel: " + super.toString();
    }
}