import java.util.Iterator;

public interface Path {
    public void computePath(String from, String to);
    public Iterator getPath();
    public int getPathLength();
}
