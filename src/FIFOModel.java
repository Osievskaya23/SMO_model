import java.util.ArrayList;

public class FIFOModel {

    public Processor processor;
    public Queue queue;
    public Task currentTask;
    public int amountOfTasks = 100;

    public double tau = Double.POSITIVE_INFINITY;
    public double currentTime = 0.0;
    public double timeInSystem = 0.0;
    public double timeInQueue = 0.0;
    public double topicalitySum = 0.0;
    public double solvedTasksCount = 0;

    public FIFOModel() {
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

    public void run() {
        while(true){
            if (currentTask.getnextTaskTime() + currentTime < currentTime + currentTask.solutionTime) {
                currentTime = currentTask.getnextTaskTime() + currentTime;
                Task remove = queue.allTasks.remove(0);
                remove.setArriveTime(currentTime);
                queue.tasks.add(remove);
            } else {
                currentTime = currentTime + currentTask.solutionTime;
                boolean flag = false;
                do {
                    Task remove;
                    if (queue.tasks.isEmpty()) {
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
