import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Task_instantiatesCorrectly_true() {
    Task myTask = new Task("Mow the lawn");
    assertEquals(true, myTask instanceof Task);
  }

  @Test
  public void getDescription_taskInstantiatesWithDescription_String() {
    Task myTask = new Task("Mow the lawn");
    assertEquals("Mow the lawn", myTask.getDescription());
  }

  @Test
  public void all_emptyAtFirst_0() {
    assertEquals(0, Task.all().size());
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame_true() {
    Task firstTask = new Task("Mow the lawn");
    Task secondTask = new Task("Mow the lawn");
    assertTrue(firstTask.equals(secondTask));
  }

  @Test
  public void save_savesObjectIntoDB_true() {
    Task myTask = new Task("Mow the lawn");
    myTask.save();
    assertEquals(Task.all().get(0).equals(myTask));
  }

  @Test
  public void save_assignsIdToObject_int() {
    Task myTask = new Task("Mow the lawn");
    myTask.save();
    Task savedTask = Task.all().get(0);
    assertEquals(myTask.getId(), savedTask.getId());
  }

  @Test
  public void find_findsTaskInDatabase_true() {
    Task myTask = new Task("Mow the lawn");
    myTask.save();
    Task savedTask = Task.find(myTask.getId());
    assertTrue(myTask.equals(savedTask));
  }


  @Test
  public void update_updatesTaskDescription_true() {
    Task myTask = new Task("Mow the lawn");
    myTask.save();
    myTask.update("Take a nap");
    assertEquals("Take a nap", Task.find(myTask.getId()).getDescription());
  }

  @Test
  public void delete_deletesTask_true() {
    Task myTask = new Task("Mow the lawn");
    myTask.save();
    int myTaskId = myTask.getId();
    myTask.delete();
    assertEquals(null, Task.find(myTaskId));
  }

}
