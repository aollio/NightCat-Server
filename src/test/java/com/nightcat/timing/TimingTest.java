package com.nightcat.timing;

import com.nightcat.Application;
import com.nightcat.common.timing.Executor;
import com.nightcat.common.timing.TaskType;
import com.nightcat.common.timing.TimeUnit;
import com.nightcat.common.timing.Timer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)

public class TimingTest {


    @Autowired
    private Timer timer;

    Executor logExecutor = new Executor() {
        @Override
        public void run(String data, TaskType type) {
            System.out.println("开始执行, data: " + data + ", type: " + type.toString());
        }
    };

    Executor exceptionExecutor = new Executor() {
        @Override
        public void run(String data, TaskType type) {
            System.out.println("开始执行, data: " + data + ", type: " + type.toString());
            throw new RuntimeException();
        }
    };

    @Test
    public void test() throws InterruptedException {


        timer.register(TaskType.Test, logExecutor);
        timer.register(TaskType.Test1, exceptionExecutor);

        timer.sumbit("Hello Timer", TaskType.Test, 15, TimeUnit.SECOND);
        timer.sumbit("Hello Exception", TaskType.Test1, 20, TimeUnit.SECOND);

        timer.start();

        Thread.sleep(40 * 1000);
    }


}
