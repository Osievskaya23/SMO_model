public class Main {

    public static FIFOModel model = new FIFOModel();

    public static double k1 = -4;
    public static double k2 = -1;
    public static double k3 = -1;
    public static double k4 = 3;
    public static double k5 = 6;

    public static double x1Count(){
        return model.timeInSystem / model.solvedTasksCount;
    }

    public static double x2Count(){
        return 0;
    }

    public static double x3Count(){
        return model.timeInQueue / model.solvedTasksCount;
    }

    public static double x4Count(){
        return model.solvedTasksCount / model.amountOfTasks;
    }

    public static double x5Count(){
        return model.topicalitySum / model.amountOfTasks;
    }

    public static double countF(){
        return k1*x1Count() + k2*x2Count() + k3*x3Count() + k4*x4Count() + k5*x5Count();
    }

    public static void main(String[] args) {
        try{

            System.out.println("mu = " + model.currentTask.mu);
            System.out.println("lambda = " + model.currentTask.lambda);
            System.out.println();
            model.run();
        } catch (NullPointerException e){
            System.out.println("x1 = " + x1Count());
            System.out.println("x2 = " + x2Count());
            System.out.println("x3 = " + x3Count());
            System.out.println("x4 = " + x4Count());
            System.out.println("x5 = " + x5Count());
            System.out.println("f = " + countF());
        }
    }
}
