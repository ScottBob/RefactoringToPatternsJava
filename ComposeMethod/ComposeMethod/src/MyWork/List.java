package MyWork;

public class List
{
    private final static int GROWTH_INCREMENT = 10;
    private boolean readOnly;
    private int size;
    private Object[] elements = new Object[]{};

    public void add(Object element) {
        if (readOnly) {
            return;
        }
        if (atCapacity()) {
            Object[] newElements = new Object[elements.length + GROWTH_INCREMENT];

            for (int i = 0; i < size; i++)
                newElements[i] = elements[i];

            elements = newElements;
        }

        elements[size++] = element;
    }

    private boolean atCapacity() {
        return (size + 1) > elements.length;
    }

    public int getCount() {
        return size;
    }

    public Object getAt(int index) {
        return elements[index];
    }
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}
