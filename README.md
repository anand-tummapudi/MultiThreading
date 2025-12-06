
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

## Summary Table

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
 
 




