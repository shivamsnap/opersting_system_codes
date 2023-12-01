import java.util.concurrent.Semaphore;
class SharedBuffer {
    private int[] buffer;
    private int size;
    private int in, out;
    private Semaphore mutex;
    private Semaphore empty;
    private Semaphore full;
    public SharedBuffer(int size) {
        this.size = size;
        this.buffer = new int[size];
        this.in = 0;
        this.out = 0;
        this.mutex = new Semaphore(1);
        this.empty = new Semaphore(size);
        this.full = new Semaphore(0);
    }
    public void produce(int item) throws InterruptedException {
        empty.acquire();
        mutex.acquire();
        buffer[in] = item;
        in = (in + 1) % size;
        mutex.release();
        full.release();
    }

    public int consume() throws InterruptedException {
        full.acquire();
        mutex.acquire();
        int item = buffer[out];
        out = (out + 1) % size;
        mutex.release();
        empty.release();
        return item;
    }
}
class Producer extends Thread {
    private SharedBuffer buffer;

    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                buffer.produce(i);
                System.out.println("Produced: " + i);
                Thread.sleep(100);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Consumer extends Thread {
    private SharedBuffer buffer;
    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                int item = buffer.consume();
                System.out.println("Consumed: " +item);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class ProducerConsumer {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer(5);
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        producer.start();
        consumer.start();
    }
}