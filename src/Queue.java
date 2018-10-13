import java.util.ArrayList;

public class Queue {

    public int priority = 0;
    public Queue nextQueue;
    public int amountOfSolutions = 0;
    public ArrayList<Task> tasks = new ArrayList<>();
    public ArrayList<Task> allTasks = new ArrayList<>();

    public Queue(int priority) {
        this.priority = priority;
    }

    public Queue() {
    }

    public Queue getNextQueue() {
        return nextQueue;
    }

    public void setNextQueue(Queue nextQueue) {
        this.nextQueue = nextQueue;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getNextTasks(){
        if (tasks.size() != 0) {
            return tasks;
        } else
        if(nextQueue != null){
            nextQueue.getNextTasks();
        }
        return null;
    }

    public void setTaskInQueue(Task currentTask) {
        int count = currentTask.amountOfSolutions;
        if (priority == count)
            tasks.add(currentTask);
        else{
            if (nextQueue != null){
                nextQueue.setTaskInQueue(currentTask);
            } else {
                nextQueue = new Queue(priority + 1);
                nextQueue.setTaskInQueue(currentTask);
            }
        }
    }
}
