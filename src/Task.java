public class Task {

    public double mu = 1;
    public double lambda = 1;
    public static double t1 = 2;
    public static double t2 = 4;

    public double arriveTime;
    public double solutionTime;
    public double nextTaskTime;
    public double topicality;
    public int amountOfSolutions = 0;

    public Task() {
        this.solutionTime = - (Math.log(Math.random()) / mu);
        this.nextTaskTime = - (Math.log(Math.random()) / lambda);
    }

    public double getTaskTopicality(double currentTime, double arrivalTime){
        if (currentTime - arrivalTime < t1){
            topicality = 1;
        }
        else if (t1 < currentTime - arrivalTime && currentTime - arrivalTime < t1 + t2){
            //   ^
            //   |
            //  1|-----------
            //   |           |\
            //   |           | \
            //   |           |  \
            // ty|-----------|---\
            //   |           |   |\
            //   |------------------------>
            //  0|           t1 tx   t2
            //
            // let's make the equation of a line
            // x1 = t1 = 4; y1 = 1;
            // x2 = t1 + t2 = 6; y2 = 0;
            // 1x + 2y - 6 = 0
            // x = startsolutionTime - taskArriveTime
            // y = (6 - x)/2
            topicality = (6 - (currentTime - arrivalTime))/2;
        } else {
            topicality = 0;
        }
        return topicality;
    }

    public double getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(double arriveTime) {
        this.arriveTime = arriveTime;
    }

    public double getSolutionTime() {
        return solutionTime;
    }

    public void setSolutionTime(double solutionTime) {
        this.solutionTime = solutionTime;
    }

    public double getnextTaskTime() {
        return nextTaskTime;
    }

    public void setnextTaskTime(double nextTaskTime) {
        this.nextTaskTime = nextTaskTime;
    }

    public double getTopicality() {
        return topicality;
    }

    public void setTopicality(double topicality) {
        this.topicality = topicality;
    }
}
