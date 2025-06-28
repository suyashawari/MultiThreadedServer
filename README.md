# Web-Servers: A Concurrency Model Comparison 🚀

![Language](https://img.shields.io/badge/Language-Java-blue.svg)
![IDE](https://img.shields.io/badge/IDE-IntelliJ%20IDEA-purple.svg)
![Testing](https://img.shields.io/badge/Testing-JMeter-red.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)

A practical Java project demonstrating and comparing three fundamental server-side concurrency models: Single-Threaded, Multi-Threaded (Thread-per-Request), and a fixed-size Thread Pool.

This repository serves as an educational tool to understand the performance, scalability, and resource-management trade-offs of different threading architectures in server-side programming.

---

## ✨ Key Features

- **Three Server Models**:
  - **`SingleThreadedServer`**: Handles only one client at a time.
  - **`MultiThreadedServer`**: Creates a new thread for every client connection.
  - **`ThreadPool`**: Manages a fixed pool of reusable worker threads.
- **Performance-Oriented**: Includes a JMeter test plan to load-test and analyze each model.
- **Simple & Focused**: The code is kept minimal to clearly illustrate the core concepts.

---

## 🏛️ Project Structure

The project is divided into three primary modules, each representing a distinct server architecture:

- **`SingleThreadedServer/`**: A basic, iterative server that blocks on each request.
- **`MultiThreadedServer/`**: An improved model that handles concurrent clients by spawning a new thread for each one.
- **`ThreadPool/`**: The most robust model, using Java's `ExecutorService` to efficiently manage a pool of threads.

---

## 💡 How It Works

Each server listens on port `8010` but handles incoming connections differently.

| Model                  | Concurrency Strategy                                                                    | Key Characteristic                                                                      |
| ---------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| **Single-Threaded**    | **None (Iterative)**                                                                    | Processes one request completely before accepting the next. Simple but not scalable.    |
| **Multi-Threaded**     | **Thread-per-Request**                                                                  | Spawns a `new Thread()` for each client. Concurrent, but resource-intensive under load. |
| **Thread Pool**        | **`ExecutorService`**                                                                   | Submits tasks to a fixed-size pool of reusable threads. Efficient and stable under load. |

The `ThreadPool` server also demonstrates a more realistic task by performing a file I/O operation (reading `readerOperation`) instead of just returning a static string.

---

## 📈 Performance Analysis

Load tests were designed using the included JMeter plan (`Thread Group.jmx`), which simulates **2000 concurrent users**. The predicted results highlight the clear advantages of the Thread Pool model.

| Metric             | Single-Threaded | Multi-Threaded                   | Thread Pool                      |
| ------------------ | --------------- | -------------------------------- | -------------------------------- |
| **Throughput**     | Very Low        | Medium                           | **Excellent & Stable**           |
| **Latency**        | Very High       | High (under heavy load)          | **Low & Consistent**             |
| **Error Rate (%)** | High            | Medium (can crash the JVM)       | **Very Low**                     |
| **Resource Usage** | Low (idle)      | **Very High** (thread overhead)  | Optimal & Controlled             |

### Conclusion

- The **Single-Threaded** model fails immediately under any significant load.
- The **Multi-Threaded** model handles concurrency but becomes unstable and slow as the number of clients grows, risking `OutOfMemoryError` due to excessive thread creation.
- The **Thread Pool** model is the clear winner, providing high throughput and low latency while maintaining system stability by limiting and reusing threads.

---

## 🔧 Getting Started

Follow these instructions to get the project running on your local machine.

### Prerequisites

1.  **Git**: To clone the repository.
2.  **Java Development Kit (JDK)**: Version 11 or higher.

### Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/suyashawari/MultiThreadedServer.git
    cd Web-Servers
    ```

2.  **OS-Specific JDK & Git Installation:**
    - **macOS (using Homebrew):**
      ```bash
      brew install openjdk@11 git
      ```
    - **Debian/Ubuntu:**
      ```bash
      sudo apt-get update
      sudo apt-get install openjdk-11-jdk git -y
      ```
    - **Windows (using Chocolatey):**
      ```powershell
      choco install openjdk11 git -y
      ```

### Compiling and Running the Servers

Open separate terminal windows for each command to run the server and client simultaneously.

#### 1. Single-Threaded Server

```bash
# Navigate to the directory
cd SingleThreadedServer

# Compile the Java files
javac *.java

# Run the Server
java Server1

# In a separate terminal, run the Client
java Client1
```

#### 2. Multi-Threaded Server

```bash
# Navigate to the directory
cd MultiThreadedServer

# Compile the Java files
javac *.java

# Run the Server
java Server

# In a separate terminal, run the Client (this will spawn 100 client threads)
java Client
```

#### 3. Thread Pool Server

```bash
# Navigate to the directory
cd ThreadPool

# Compile the Java files
javac *.java

# Run the Server
java Server3

# In a separate terminal, use the multi-threaded client to test
cd ../MultiThreadedServer/
java Client
```








---

## 🚦 How to Run the Load Test

You can use the provided JMeter test plan to see the performance differences for yourself.

1.  **Install Apache JMeter:** Download it from the [official website](https://jmeter.apache.org/download_jmeter.cgi).
2.  **Start a Server:** Run one of the servers (e.g., `java ThreadPool/Server3`).
3.  **Launch JMeter.**
4.  In JMeter, go to **File > Open** and select the `SingleThreadedServer/Thread Group.jmx` file.
5.  Click the green **Start** button (▶) to run the test.
6.  Observe the results in the **View Results Tree** and **Summary Report** listeners to see how your chosen server performs under pressure.
