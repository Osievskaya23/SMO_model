public class Processor {

    public Task task = null;
    public double taskArrive;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public double getTaskArrive() {
        return taskArrive;
    }

    public void setTaskArrive(double taskArrive) {
        this.taskArrive = taskArrive;
    }

    public boolean isEmpty(){
        if (task != null) return false;
        return true;
    }
}
