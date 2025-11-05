# GitHubViz

**GitHubViz** is a Java Swing desktop application that visualizes software quality metrics and code structure using an interactive grid-based interface.  
It enables users to analyze and display data extracted from GitHub repositories, providing an intuitive visualization of project components, their dependencies, and health indicators.


## 🧩 Overview

GitHubViz represents source code components (classes, packages, or modules) as **interactive squares** in a grid layout.  
Each square encodes information such as:

- Dependency relationships  
- Coupling and cohesion metrics  
- Abstractness and instability indices  
- Code ownership or responsibility annotations  

The system was designed as a teaching and research tool for software design visualization, software quality analysis, and architecture comprehension.


## 🏗️ Architecture

The project follows an MVC-inspired design pattern, consisting of the following components:

| Class | Role |
|-------|------|
| **Main.java** | Entry point of the application. Initializes the UI and sets up event listeners and delegates. |
| **GridPanel.java** | Core visualization component that renders the grid of software components using `Square` objects. |
| **Square.java** | Represents a single visual unit in the grid — a module, class, or component — containing data and drawing logic. |
| **Delegate.java** | Manages communication between UI components and backend logic (e.g., user interactions and visual updates). |
| **Blackboard.java** | Central shared data structure for storing analysis results and visual configuration. |
| **TheNanny.java** | Supervises periodic updates and validation, ensuring data consistency and coordinating visualization refresh cycles. |


## 🧠 Features

- **Interactive Visualization:**  
  Displays code metrics in a grid layout with mouse-over details.

- **Metric-Based Coloring:**  
  Squares change color based on coupling, stability, or other configurable attributes.

- **Dependency Awareness:**  
  Visual links or adjacency patterns represent class or package dependencies.

- **Observer/Delegate Pattern:**  
  Uses delegation to decouple visualization and control logic.

- **Extendable Blackboard System:**  
  Centralized storage for sharing metrics or visualization parameters across components.

- **Automated Refresh (via TheNanny):**  
  Periodic UI updates ensure metrics remain synchronized with the underlying data model.


## 🚀 Getting Started

### Prerequisites
- Java 17 or later  
- Maven or any preferred Java build tool  
- (Optional) GitHub API integration for live repository metrics

### Running the Application

Compile and execute directly:
```bash
javac *.java
java Main
