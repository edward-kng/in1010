public class IndeksertListe<T> extends Lenkeliste<T> {
    public void leggTil(int pos, T x) {
        Node nyNode = new Node(x);

        if (0 <= pos && pos <= stoerrelse) {
            if (stoerrelse == 0) {
                start = nyNode;
            } else if (pos == 0) {
                nyNode.neste = start;
                start = nyNode;
            } else {
                Node forrige = start;

                for (int i = 0; i < pos - 1; i++) {
                    forrige = forrige.neste;
                }

                nyNode.neste = forrige.neste;
                forrige.neste = nyNode;
            }

            stoerrelse++;
        } else {
            throw new UgyldigListeindeks(pos);
        }
    }

    public void sett(int pos, T x) {
        if (0 <= pos && pos < stoerrelse) {
            Node node = start;

            for (int i = 0; i < pos; i++) {
                node = node.neste;
            }

            node.data = x;
            } else {
                throw new UgyldigListeindeks(pos);
            }
    }

    public T hent(int pos) {
        if (0 <= pos && pos < stoerrelse) {
            Node node = start;

            for (int i = 0; i < pos; i++) {
                node = node.neste;
            }
            
            return node.data;
        } else {
            throw new UgyldigListeindeks(pos);
        }
    }

    public T fjern(int pos) {
        if (0 <= pos && pos < stoerrelse) {
            T data;
            
            if (stoerrelse == 1) {
                data = start.data;
                start = null;
            } else if (pos == 0) {
                data = start.data;
                start = start.neste;
            } else {
                Node forrige = start;
                for (int i = 0; i < pos - 1; i++) {
                    forrige = forrige.neste;
                }
                
                data = forrige.neste.data;
                forrige.neste = forrige.neste.neste;
            }

            stoerrelse--;
            return data;
        } else {
            throw new UgyldigListeindeks(pos);
        }
    }

    @Override
    public String toString() {
        return "IndeksertListe: " + super.toString();
    }
}
