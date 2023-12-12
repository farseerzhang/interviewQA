import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool2 {
    private static final int produceTaskSleepTime = 2;
    private static final int produceTaskMaxNumber = 10;

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool1 = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i=1; i<= produceTaskMaxNumber; i++){
            try {
                String task = "task" + i;
                System.out.println("put " + task);
                threadPool1.execute(new ThreadPoolTask(task));
                Thread.sleep(produceTaskSleepTime);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        threadPool1.shutdown();
    }
}

class ThreadPoolTask implements Runnable, Serializable{
    @Serial
    private static final long serialVersionUID = 0;
    private static final int consumeTaskSleepTime = 2000;

    private Object threadPoolTaskData;

    ThreadPoolTask(Object tasks){
        this.threadPoolTaskData = tasks;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("start .." + threadPoolTaskData);
        try {
            Thread.sleep(consumeTaskSleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolTaskData = null;
    }

    public Object getTask(){
        return this.threadPoolTaskData;
    }
}

//3、在这段程序中，main()方法相当于一个残忍的领导，他派发出许多任务，丢给一个叫 threadPool的任劳任怨的小组来做。
//这个小组里面队员至少有两个，如果他们两个忙不过来，任务就被放到任务列表里面。
//如果积压的任务过多，多到任务列表都装不下(超过3个)的时候，就雇佣新的队员来帮忙。但是基于成本的考虑，不能雇佣太多的队员，至多只能雇佣 4个。
//如果四个队员都在忙时，再有新的任务，这个小组就处理不了了，任务就会被通过一种策略来处理，我们的处理方式是不停的派发，直到接受这个任务为止(更残忍！呵呵)。
//因为队员工作是需要成本的，如果工作很闲，闲到 3SECONDS都没有新的任务了，那么有的队员就会被解雇了，但是，为了小组的正常运转，即使工作再闲，小组的队员也不能少于两个。
//4、通过调整 produceTaskSleepTime和 consumeTaskSleepTime的大小来实现对派发任务和处理任务的速度的控制，改变这两个值就可以观察不同速率下程序的工作情况。
