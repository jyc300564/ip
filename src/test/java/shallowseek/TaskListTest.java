package shallowseek;

import org.junit.jupiter.api.Test;
import shallowseek.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    void add_singleTask_sizeIncreases() {
        TaskList list = new TaskList();
        Task task = new ToDo("read book");

        list.addTask(task);

        assertEquals(1, list.getTaskListSize());
        assertSame(task, list.getTaskAt(0));
    }

    @Test
    void remove_validIndex_taskIsRemoved() {
        TaskList list = new TaskList();
        Task t1 = new ToDo("t1");
        Task t2 = new ToDo("t2");

        list.addTask(t1);
        list.addTask(t2);

        Task removed = list.deleteTask(0);

        assertSame(t1, removed);
        assertEquals(1, list.getTaskListSize());
        assertSame(t2, list.getTaskAt(0));
    }

    @Test
    void get_invalidIndex_throwsIndexOutOfBoundsException() {
        TaskList list = new TaskList();
        list.addTask(new ToDo("only task"));

        assertThrows(IndexOutOfBoundsException.class, () -> list.getTaskAt(1));
    }
}
