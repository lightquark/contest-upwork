package com.lightquark.contest.upwork;

import com.lightquark.contest.upwork.elevator.ElevatorController;
import com.lightquark.contest.upwork.elevator.ElevatorView;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Light Quark.
 */
public class ElevatorApp
{
    public static void main(String args[]) throws Exception
    {
        JFrame w = new JFrame("Upwork Test - Elevator");
        w.setSize(620, 600);
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setLayout(new BorderLayout(1, 1));

        ElevatorController controller = new ElevatorController();

        ElevatorView elevatorView = new ElevatorView(controller);
        w.add(elevatorView);
        w.setVisible(true);

        Runnable updateTask = () ->
        {
            controller.tryToCreateUser();
            controller.update();
            elevatorView.repaint();
        };

        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(1);
        scheduledPool.scheduleWithFixedDelay(updateTask, 0, 50, TimeUnit.MILLISECONDS); //20 FPS
    }
}
