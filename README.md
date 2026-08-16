# 🎮 Rock Paper Scissors — Java

<p align="center">
  <strong>A modular Rock Paper Scissors game built with Java & SQLite</strong><br>
  <sub>OOP • Game Logic • DAO • SQLite • JDBC</sub>
</p>

<p align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-003B57?style=for-the-badge\&logo=sqlite\&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Database-6C63FF?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completed-2E7D32?style=for-the-badge)

</p>

---

## ✦ About

A Java-based **Rock Paper Scissors game** developed using a modular project structure.
It combines game logic, OOP, UI components, DAO architecture, and SQLite database integration.

---

## ✨ Features

* 🎮 Rock Paper Scissors gameplay
* 🧠 Separate game logic
* 🗄️ SQLite database integration
* 🔗 JDBC connectivity
* 📦 DAO-based structure
* 🖥️ Dedicated UI package
* 🧩 Organized Java packages

---

## 🛠️ Tech Stack

| Technology         | Purpose                  |
| :----------------- | :----------------------- |
| ☕ **Java**         | Application development  |
| 🗄️ **SQLite**     | Database storage         |
| 🔗 **SQLite JDBC** | Java–SQLite connectivity |
| 🧱 **OOP**         | Code structure & design  |
| 🗂️ **DAO**        | Data access organization |

**JDBC Driver:** `sqlite-jdbc-3.53.2.0.jar`

---

## 📁 Project Structure

```text
Rock-Paper-Scissors-Game-Java/
│
├── src/
│   ├── dao/
│   ├── database/
│   ├── logic/
│   ├── model/
│   ├── ui/
│   └── Main.java
│
├── lib/
│   └── sqlite-jdbc-3.53.2.0.jar
│
└── database/
    └── SQLite database file
```

---

## 🗄️ Database

The project uses **SQLite** for storing application data.

The database file is located in:

```text
database/
```

Java connects to it using the SQLite JDBC driver located in:

```text
lib/sqlite-jdbc-3.53.2.0.jar
```

---

## 🚀 How to Run

### Prerequisites

* Java JDK
* VS Code or any Java-supported IDE

### Steps

1. Clone or download the repository.
2. Open the project in your IDE.
3. Keep the `lib/` and `database/` folders in their original locations.
4. Make sure the SQLite JDBC driver is included in the project.
5. Run:

```text
src/Main.java
```

---

## 🎯 Game Rules

|    Choice   |    Beats    |
| :---------: | :---------: |
|   🪨 Rock   | ✂️ Scissors |
|   📄 Paper  |   🪨 Rock   |
| ✂️ Scissors |   📄 Paper  |

---

## 👩‍💻 Author

<p align="center">
  <strong>Tooba Hashim</strong><br>
  Software Engineering Student
</p>

<p align="center">
  ⭐ <i>Built with Java ☕ & a little competitive spirit 🎮</i>
</p>
