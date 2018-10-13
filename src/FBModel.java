import java.util.ArrayList;

public class FBModel {

    public Processor processor;
    public Queue queue;
    public Task currentTask;
    public int amountOfTasks = 100;

    public ArrayList<Task> currentTasks = new ArrayList<>();

    public double tau = 1;
    public double currentTime = 0.0;
    public double timeInSystem = 0.0;
    public double timeInQueue = 0.0;
    public double topicalitySum = 0.0;
    public double solvedTasksCount = 0;

    public FBModel() {
        processor = new Processor();
        queue = new Queue();
        createAllTasks();
        currentTask = queue.allTasks.remove(0);
        processor.setTask(currentTask);
        processor.setTaskArrive(currentTime);
        currentTask.setArriveTime(currentTime);
    }

    public ArrayList createAllTasks() {
        for (int i = 0; i < amountOfTasks; i++) {
            queue.allTasks.add(new Task());
        }
        return queue.allTasks;
    }

    public boolean isTaskSolved(Task task){
        task.setSolutionTime(task.getSolutionTime() - tau);
        if (task.getArriveTime() < 0.001){
            return true;
        }
        return false;
    }

    public void run() {
        while(true){
            if (currentTask.getnextTaskTime() + currentTime < currentTime + tau) {
                currentTime = currentTask.getnextTaskTime() + currentTime;
                Task remove = queue.allTasks.remove(0);
                remove.setArriveTime(currentTime);
                queue.tasks.add(remove);
            } else {
                currentTime = currentTime + currentTask.getSolutionTime();
                boolean flag = false;
                if (!isTaskSolved(currentTask)){
                    queue.setTaskInQueue(currentTask);
                }
                do {
                    currentTasks = queue.getNextTasks();
                    Task remove;
                    if (currentTasks.isEmpty()) {
                        try {
                            remove = queue.allTasks.remove(0);
                        } catch (IndexOutOfBoundsException e){
                            remove = null;
                        }
                    } else {
                        remove = queue.tasks.remove(0);
                    }
                    timeInSystem += currentTime - currentTask.getArriveTime();
                    solvedTasksCount++;
                    timeInQueue += processor.getTaskArrive() - currentTask.getArriveTime();
                    topicalitySum += remove.getTaskTopicality(currentTime, remove.getArriveTime());
                    if (remove.getTaskTopicality(currentTime, remove.getArriveTime()) != 0.0) {
                        processor.setTask(remove);
                        processor.setTaskArrive(currentTime);

                        flag = false;
                    }else {
                        flag = true;
                    }
                } while (flag);
            }
        }
    }
}

