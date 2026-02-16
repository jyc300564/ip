# 🧠 ShallowSeek

> "Your mind is for having ideas, no holding them."
> — David Allen

**ShallowSeek** is a *light weight*, **text-based** task management chatbot written in Java.
It helps users track tasks without cluttering their mind or workflow 😄

---

## 🚀 Quick Start

1. Download the .jar file.
2. Place it in an empty folder.
3. Open a command window in that folder.
4. Run:

```bash
java -jar "shallowseek.jar"
```

Upon launch, ShallowSeek will display a greeting message and load previously saved tasks automantically.

if no `data` folder or saved file exists, they will be created automantically.

--

## 📋 Features

- Add ToDo, Deadline, and Event tasks
- Mark and unmark tasks
- Delete tasks  
- Find tasks by keyword (substring matching)  
- Automatic persistent storage  
- Duplicate task detection  
- Robust error handling  
- JavaFX graphical interface 

## 📝 Command Format

All commands follow this general format:

```
<command> <arguments>
```

Task numbering starts from **1**.

---

## 📋 Viewing All Tasks

```
list
```

Displays all tasks currently stored.

---

## ➕ Adding Tasks

### 🟢 ToDo

```
todo <description>
```

Example:

```
todo Read CS2103T notes
```

---

### 🔵 Deadline

```
deadline <description> /by <date-time>
```

Example:

```
deadline Submit iP /by 2026-02-20 23:59
```

Deadlines use `LocalDateTime`.

---

### 🟣 Event

```
event <description> /from <start-date-time> /to <end-date-time>
```

Example:

```
event Project meeting /from 2026-02-15 14:00 /to 2026-02-15 16:00
```

Events also use `LocalDateTime`.

---

## ✅ Mark Task as Done

```
mark <task number>
```

Marks the specified task as completed.

---

## ❌ Unmark Task

```
unmark <task number>
```

Marks the task as not completed.

---

## 🗑 Delete Task

```
delete <task number>
```

Removes the specified task from the list.

---

## 🔎 Find Tasks

```
find <keyword>
```

Finds tasks whose descriptions contain the given keyword.

Matching is done using **substring search**.

Example:

```
find project
```

---

## 👋 Exit Program

```
bye
```

Gracefully terminates the application.

---

## 💾 Data Storage

- Tasks are automatically saved after each modifying command.
- Data is stored locally in a save file.
- If the `data` folder or save file does not exist, they will be created automatically.
- Corrupted save file lines are detected and handled safely.

---

## ⚠ Error Handling

ShallowSeek handles the following gracefully:

- Invalid command formats  
- Missing arguments  
- Invalid task numbers  
- Duplicate task additions  
- Corrupted save file lines  

Instead of crashing, the application returns informative error messages.

---

## 🖥 Graphical Interface

ShallowSeek provides a JavaFX-based GUI:

- User messages appear on the right  
- ShallowSeek responses appear on the left  
- Message list auto-scrolls  
- Styled message bubbles  
- Custom application icon and theme  

---

## 🧑‍💻 Implementation Highlights

ShallowSeek is built using:

- Java  
- Object-Oriented Design  
- Command Pattern  
- Parser abstraction  
- `LocalDateTime` for date-time handling  
- Persistent storage  
- JavaFX GUI  
