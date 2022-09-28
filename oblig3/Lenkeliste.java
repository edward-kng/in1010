public abstract class Lenkeliste<T> implements Liste<T> {
    protected Node start;
    protected int stoerrelse;

    class Node {
        Node neste;
        T data;

        Node(T data) {
            this.data = data;
        }
    }

    @Override
    public int stoerrelse() {
        return stoerrelse;
    }

    @Override
    public void leggTil(T x) {
        Node nyNode = new Node(x);

        if (stoerrelse == 0) {
            start = nyNode;
        } else {
            Node node = start;

            for (int i = 0; i < stoerrelse - 1; i++) {
                node = node.neste;
            }

            node.neste = nyNode;
        }

        stoerrelse++;
    }

    @Override
    public T hent() {
        if (stoerrelse > 0) return start.data;
        else throw new UgyldigListeindeks(0);
    }

    @Override
    public T fjern() {
        if (stoerrelse > 0) {
            T data = start.data;
            if (stoerrelse > 1) start = start.neste;
            stoerrelse--;
            return data;
        }
        throw new UgyldigListeindeks(0);
    }

    @Override
    public String toString() {
        String txt = "(";
        Node denneNoden = start;

        while (denneNoden != null) {
            txt += denneNoden.data;

            if (denneNoden.neste != null) {
                txt += ", ";
            }

            denneNoden = denneNoden.neste;
        }
        
        return txt + ")";
    }
}