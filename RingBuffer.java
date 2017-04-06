/**
 * A type of queue
 */
public class RingBuffer {

    /**
     * create an empty ring buffer, with given max capacity
     *
     * @param capacity max capacity
     */
    public RingBuffer(int capacity) {
    }

    /**
     * return number of items currently in the buffer
     *
     * @return number of items
     */
    public int size() {
    }

    /**
     * is the buffer empty (size equals zero)?
     *
     * @return <code>true</code> if buffer is empty
     */
    public boolean isEmpty() {
    }
    
    /**
     * is the buffer full (size equals capacity)?
     *
     * @return <code>true</code> if the buffer is full
     */
    public boolean isFull() {
    }

    /**
     * add item x to the end
     *
     * @param x a <code>double</code> 
     */
    public void enqueue(double x) {
    }

    /**
     * delete and return item from the front
     *
     * @return a <code>double</code> value
     */
    public double dequeue() {
    }

    /**
     * return (but do not delete) item from the front
     *
     * @return front of queue
     */
    double peek() {
    }


}
