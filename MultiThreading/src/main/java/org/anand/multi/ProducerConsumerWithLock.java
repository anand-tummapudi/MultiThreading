package org.anand.multi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Simple Producer-Consumer using ReentrantLock and Condition.
 * Produces integer items and consumers consume them.
 */
public class ProducerConsumerWithLock {

    static class BoundedBuffer<T> {
        private final Queue<T> queue = new LinkedList<>();
        private final int capacity;
        private final Lock lock = new ReentrantLock();
        private final Condition notFull = lock.newCondition();
        private final Condition notEmpty = lock.newCondition();

        BoundedBuffer(int capacity) {
            this.capacity = capacity;
        }

        void put(T item) throws InterruptedException {
            lock.lock();
            try {
                while (queue.size() == capacity) {
                    notFull.await();
                }
                queue.add(item);
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        T take() throws InterruptedException {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    notEmpty.await();
                }
                T item = queue.remove();
                notFull.signal();
                return item;
            } finally {
                lock.unlock();
            }
        }

        int size() {
            lock.lock();
            try {
                return queue.size();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        final int PRODUCERS = 2;
        final int CONSUMERS = 2;
        final int ITEMS_PER_PRODUCER = 50;
        final int BUFFER_CAPACITY = 10;

        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(BUFFER_CAPACITY);
        CountDownLatch done = new CountDownLatch(PRODUCERS + CONSUMERS);
        AtomicInteger producedTotal = new AtomicInteger();
        AtomicInteger consumedTotal = new AtomicInteger();

        // Producers
        for (int p = 0; p < PRODUCERS; p++) {
            final int producerId = p;
            new Thread(() -> {
                try {
                    for (int i = 1; i <= ITEMS_PER_PRODUCER; i++) {
                        int item = producerId * 1000 + i; // make items identifiable
                        buffer.put(item);
                        int pt = producedTotal.incrementAndGet();
                        System.out.printf("Producer-%d produced %d (total produced=%d, buffer=%d)\n",
                                producerId, item, pt, buffer.size());
                        // simulate work
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            }, "Producer-" + p).start();
        }

        // Consumers
        for (int c = 0; c < CONSUMERS; c++) {
            final int consumerId = c;
            new Thread(() -> {
                try {
                    // each consumer consumes ITEMS_PER_PRODUCER * PRODUCERS / CONSUMERS items
                    int itemsToConsume = (ITEMS_PER_PRODUCER * PRODUCERS) / CONSUMERS;
                    for (int i = 0; i < itemsToConsume; i++) {
                        Integer item = buffer.take();
                        int ct = consumedTotal.incrementAndGet();
                        System.out.printf("Consumer-%d consumed %d (total consumed=%d, buffer=%d)\n",
                                consumerId, item, ct, buffer.size());
                        // simulate processing
                        TimeUnit.MILLISECONDS.sleep(20);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            }, "Consumer-" + c).start();
        }

        // Wait for all threads to finish
        done.await();

        System.out.println("All producers and consumers finished.");
        System.out.println("Produced total: " + producedTotal.get());
        System.out.println("Consumed total: " + consumedTotal.get());
    }
}

