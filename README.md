# Ways to Create Threads in Java (Java 17 & Java 21)
This README provides **7 different ways** to create and run threads in Java, including modern approaches like **CompletableFuture**, **ExecutorService**, **Fork/Join Framework**, and **Virtual Threads** (Java 21).
---
## 1. Creating a Thread by Extending `Thread` Class
```java
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread running using Thread class!");
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
```
---
## 2. Creating a Thread by Implementing `Runnable`

```java
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread running using Runnable!");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }
}
```
---
## 3. Creating a Thread using Lambda Expression
```java
public class LambdaThread {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("Thread running using Lambda expression!");
        });
        t.start();
    }
}
```
---
## 4. Creating a Thread using `CompletableFuture`
```java
import java.util.concurrent.CompletableFuture;
public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> {
            System.out.println("Thread running using CompletableFuture!");
        });
    }
}
```
---
## 5. Creating Threads using `ExecutorService`

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(() -> System.out.println("Thread running using ExecutorService"));
        service.shutdown();
    }
}
```
---
## 6. Creating Threads using Fork/Join Framework

```java
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class MyForkJoinTask extends RecursiveAction {

    @Override
    protected void compute() {
        System.out.println("Thread running using ForkJoin Framework!");
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new MyForkJoinTask());
    }
}
```
---
## 7. Creating Virtual Threads (Java 21+)
```java
public class VirtualThreadExample {
    public static void main(String[] args) throws Exception {
        Thread vt = Thread.startVirtualThread(() -> {
            System.out.println("Thread running using Virtual Thread!");
        });

        vt.join();
    }
}
```
---
## Different Ways for Creating thread Summary

| # | Method | Java Version | Notes |
|---|--------|--------------|--------|
| 1 | Extend Thread | All | Basic approach |
| 2 | Implement Runnable | All | Most common classic approach |
| 3 | Lambda Runnable | 8+ | Clean & modern |
| 4 | CompletableFuture | 8+ | Async & functional |
| 5 | ExecutorService | 5+ | Thread pools |
| 6 | Fork/Join | 7+ | Parallelism framework |
| 7 | Virtual Threads | 21+ | Lightweight millions of threads |

## Concurrency Vs Parallelism
 **Concurrency** - executing multiple tasks at a time - Perceived or Fake Parallelism.
 **Parallelism** - Tasks are executed simultaniously  - Is about toexecuting multiple tasks in parallel.
 
 ## Thread Life Cycle
 **New State** - Every thread is in this state until we start it.
 **Active** - Every thread is in this state after we can start() on it.
 **Blocked** - Every thread is in this state, when it is waiting for some thread to finish.
 **Terminated** - Every thread is in this state after it is done doing its assigned work.
 
 ## Join Operation
  Main therad is the parent Thread  
  join() method on a certain thread means, once you are done executing your task, join my flow of execution. It is like the parent thread waits for the completion of the child thread and then continues with its execution.
  
 ## Deamon Threads and User Threads
Deamon threads run in the background and they are helper threads.  
Any thread is not deamon by default. We can make a thread deamon by calling **thread.setDeamon(true)** method on the thread.  
JVM does not wait for the deamon threads to completes its execution, onces user thread execution is completed the JVM terminates even though the deamon thread is still running.  

## Thread Priority
Lets say there are 10 threads in runnable state and there is only one CPU. So only one thread can run at a time and others have to wait. So who decides which thread can get to run on the cpu, this component is called Thread Scheduler.  
Each thread has cerain priority and under normal circumstances, the thread with the highest priority gets to run on the CPU.  
Priority value 1 - 10 can be assigned to any thread. 1 is the MIN_PRIORITY and 10 is the MAX_PRIORITY. By default the priority of a thread is 5 which is NORM_PRIORITY.  
Threads of the same priority are executed in FIFO manner. The thread scheduler stores the threads in a queue.  
Main thread gets executed first irrespective of the priority whether it is lower or higher.  

## Thread Synchronization
Shared Resources, we want to make use of among the multiple threads. So at a given point of time only one thread can make use of the resource. We can achieve this byusing synchronization.  
Synchronization can be done at method level or block level. Code inside the synchorization block is called critical section.  

**Problem with Synchronization** Each object in java is associated with a monitor. That is mutual exclusion mechanism used in synchronization. When a thread is encountered synchrinization, the monitor lock is assigned to the thread. So when we apply Synchronized at method level, the entire method is locked with the monitor. This leads to reduced concurrency and performance losing.  
When we apply synchronize keyword at block level, we need to pass something to the synchronize method, so we need to create the lock objects explicitly and pass. So JVM will not lock entire class. 

## Wait and Notify
Wait() is used for synchronization and stop() is used for just sleeping. When wait() ic called, execution of the thread will be suspended.  
**Wait** method is with different variants one with wit and wait(time), when we call wait with time thread will be suspended until the time and it automatically awakes after the time.  
**Notify** Notify will notify one waiting theread and NirifyAll will notify all the waiting threads.  

## ReentrantLock
ReentrantLock is a mutual exclusion lock implementation in java.util.concurrent.locks. 
It provides the same basic mutual-exclusion behavior as the synchronized keyword (only one thread holds the lock at a time), but with more features and explicit control.
"Reentrant" means the thread that currently holds the lock can acquire it again without deadlocking itself. The lock maintains a hold count: each lock() increments it, each unlock() decrements it; the lock is released only when the hold count reaches zero.  
TryLock helps avoid deadlocks or implement non-blocking attempts to enter critical sections.

## Producer and Consumer Problem
 Producer consumer problem can be implemented using wait and notify methods. It can also be implemented using Reentrant Lock. Just the way of synchronizing and accessing the shared components.

## Executor Services
Let us say if we want to create huge number of threads to perform a task. A thread is an expensive task, so if we create 1000s of threads  it would be difficult to manage and it consumes cpu. So we can create a pool of threads and reuse the threads, that will optimize the performance. Executors helps for the same. Here threads are not killed once they are done with the performing the task. Threads will be reused to perform another task. So that we can save the time of creating threads also.  

There are 4 types of executors.  

SingleThreadExecutor  
FixedThreadPoolExecutor  
CachedThreadPool  
ScheduledExecutor  

Executors have 2 components thread pool and priority queue. There will be a pool of readily avaiulable threads created. Each time a task is assiged to a thread that will be added to a blocking queue. Then the tasks will be executed based on their priority and availability of threads.  
Fixed thread pool, have a fixed no of threads to be pooled and execute tasks. CacheThreadPool, does not have a size of the pool but it maintains a synchronous queue. If there are 10 threads created and all are busy executing tasks, it automatically creates a new thread to execute the task. If a thread is completing its task and being idle in the pool for maximum of 60 secods, then it kills or terminates the thread. So it is like self managed pool of threads execution.  
Scheduled executors services are created to run the tasks at scheuled time intervals, it can be scheduled with intial time delay and delay between each iteration and the time units can be passed to the executor service.  

```java

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {

    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new ProbeTask(),0,1, TimeUnit.SECONDS);

        try {
            if (!service.awaitTermination(30, TimeUnit.SECONDS)) {
                service.shutdown();
            }
        } catch (InterruptedException e) {
            service.shutdown();
            e.printStackTrace();
        }
    }
}

class ProbeTask implements Runnable{
     @Override
    public void run(){
        System.out.println("Probe Task executed by Thread: " + Thread.currentThread().getName());
    }

}


```

There are 2 shutdown methods, shutdown() and shutdownNow(). First one waits for the currently executing thereads to be completed and terminate. Second one terminates immediately without waiting for the currently executing threads to be terminated.







   






 
  
  
 
 




