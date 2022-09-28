public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T> {
    @Override
    public void leggTil(T x) {
        if (stoerrelse == 0) {
            super.leggTil(x);
        } else {
            Node nyNode = new Node(x);
            Node node = start;
            if (x.compareTo(start.data) == -1) {
                nyNode.neste = start;
                start = nyNode;
                stoerrelse++;
                return;
            } else {
                for (int i = 0; i < stoerrelse - 1; i++) {
                    if (x.compareTo(node.neste.data) == -1) {
                        nyNode.neste = node.neste;
                        node.neste = nyNode;
                        stoerrelse++;
                        return;
                    }
    
                    node = node.neste;
                }

                super.leggTil(x);
            }
        }
    }

    @Override
    public String toString() {
        return "Prioritetskoe: " + super.toString();
    }
}