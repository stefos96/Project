package Game;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;



public class NewList {
    
    private int size;
    
    private static final int DEFAULT_CAPACITY = 10;
    
    private static final Room[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    
    private static final Room[] EMPTY_ELEMENTDATA = {};
    
    transient Room[] elementData;
    
    
    
    
    public NewList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Room[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }
    
    public NewList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }       
    
    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
    
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
    
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
    
    Room elementData(int index) {
        return (Room) elementData[index];
    }
    
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Room o) {
        return indexOf(o) >= 0;
    }

    public Room[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    public Room[] toArray(Room[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (Room[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }
    
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }    


    public void clear() {
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    public Object get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    public Object set(int index, Room element) {
        rangeCheck(index);

        Room oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

//    public void add(int index, Room element) {
//    }

    public Object remove(int index) {
        rangeCheck(index);

        Room oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    public int indexOf(Room o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    public int lastIndexOf(Room o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    public Iterator listIterator() {
        return new NewList.Itr();
    }

    public Iterator listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new NewList.Itr();
    }
    
    public boolean add(Room e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }
    
    private class Itr implements Iterator<Room> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Room next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Room[] elementData = NewList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (Room) elementData[lastRet = i];
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();

            try {
                NewList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<?super Room> consumer) {
            final int size = NewList.this.size;
            int i = cursor;
            if (i >= size) {
                return;
            }
            final Room[] elementData = NewList.this.elementData;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            while (i != size) {
                consumer.accept((Room) elementData[i++]);
            }
            // update once at end of iteration to reduce heap write traffic
            cursor = i;
            lastRet = i - 1;
        }
    }
    
    
    
    
    
    
}