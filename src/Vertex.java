import java.util.LinkedList;

public class Vertex <T> implements Comparable{

    private T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @SuppressWarnings("unchecked")
    public Vertex(T data) {
        this.data = data;

    }

    @Override
    public String toString() {
        if(data instanceof Airport){
            return ((Airport) data).name;
        }
        return super.toString();
    }

    @Override
    public int compareTo(Object o) {
        if (this.equals(o)) {
            return 1;
        }

        return 0;
    }
}
