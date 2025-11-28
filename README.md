![Static Badge](https://img.shields.io/badge/author-javiergs-orange)
![GitHub repo size](https://img.shields.io/github/repo-size/CSC3100/App-GithubViz)
![Java](https://img.shields.io/badge/Java-17+-blue)
![Platform](https://img.shields.io/badge/platform-Java_Swing-orange)

![Status](https://img.shields.io/badge/status-Template_Project-yellow)
![Contributions](https://img.shields.io/badge/contributions-welcome-brightgreen)

**GitHubViz** is a Java Swing desktop application that visualizes software quality metrics and code structure using an interactive grid-based interface. It enables users to analyze and display data extracted from GitHub repositories, offering an intuitive representation of project components, dependencies, and key software engineering indicators.

> **Note:**  
> This repository serves as a **starter template for a class project**. Students are expected to extend, refactor, and enhance its features as part of the course.

## Purpose

GitHubViz was created as a teaching and research tool to help students and developers:

- Understand software architecture through visual representations  
- Explore coupling, cohesion, abstractness, instability, and related metrics  
- Experiment with different visualization techniques  
- Practice applying design patterns and improving existing software artifacts  
- Learn by doing: refactor, extend, and improve an existing codebase  

It provides a foundation for hands-on work in software analysis, design visualization, and architecture comprehension.

## Screenshot

<img width="912" height="712" alt="Screenshot 2025-11-28 at 3 50 49 PM" src="https://github.com/user-attachments/assets/4c6f310f-b1a9-4cfc-81d2-4efa8e980d57" />


## Overview

GitHubViz represents source code entities (classes, packages, or modules) as **interactive squares** arranged in a grid.  
Each square encodes information such as:

- Dependency relationships  
- Coupling/cohesion levels  
- Abstractness and instability indices  
- Code ownership annotations  
- Custom metrics added by students during development  

The system is intentionally modular, making it easy to adapt for new visualizations or metrics.

## Architecture

The project follows an MVC-inspired structure with clear separation of concerns:

| Class | Role |
|-------|------|
| **Main.java** | Application entry point. Initializes UI components, event listeners, and delegates. |
| **GridPanel.java** | Core visualization component that renders the grid using `Square` objects. |
| **Square.java** | Represents a single grid unit — a class/module/component — containing drawing logic and metric data. |
| **Delegate.java** | Mediates communication between UI elements and backend logic (input handling, updates, etc.). |
| **Blackboard.java** | Central shared data store for metrics, configuration, and visualization parameters. |
| **TheNanny.java** | Oversees periodic updates and validation, ensuring consistent data flow and triggering UI refresh cycles. |

## Getting Started

Clone the repository:

```bash
git clone https://github.com/CSC3100/App-GitHubViz.git
```

Compile and run:

```bash
javac Main.java
java Main
```

## For Students

This project is intentionally minimalistic. Expected improvements include:

- Integrating GitHub APIs  
- Adding new metrics and visual indicators  
- Enhancing the UI  
- Refactoring for clarity and maintainability  
- Improving documentation and examples  

Treat this repository as a **starting point**, not a finished product.
