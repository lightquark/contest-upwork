package com.lightquark.contest.upwork.elevator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Light Quark.
 */
public class ElevatorView extends JComponent
{
    private static final int[] USER_COORDS = { 50, 100, 150, 200, 250, 300, 350 };
    private static final int[] ELEVATOR_USER_COORDS = { 400, 450, 500, 550, 600 };

    private IElevatorController controller;

    public ElevatorView(IElevatorController controller)
    {
        this.controller = controller;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;

        //draw floors
        drawFloors(g2d);

        //draw elevator
        IElevator elevator = controller.getElevator();
        drawElevator(g2d, elevator);

        //draw elevator users
        java.util.List<IUser> elevatorUsers = controller.getElevatorUsers();
        for (int i = 0; i < elevatorUsers.size(); i++)
        {
            drawUser(g2d, elevator.getFloorPosition(), ELEVATOR_USER_COORDS[i], elevatorUsers.get(i));
        }

        //draw users
        java.util.List<IUser> users = controller.getUsers();
        for (int i = 0; i < users.size(); i++)
        {
            IUser u = users.get(i);
            drawUser(g2d, u.getFloor(), USER_COORDS[i], u);
        }

        super.repaint();
    }

    private int getFloorY(float floorPos)
    {
        return 450 - (int) (floorPos * 50);
    }

    private void drawFloors(Graphics2D g2d)
    {
        for (int i = Settings.FLOOR_MIN; i <= Settings.FLOOR_MAX; i++)
        {
            g2d.setPaint(Color.LIGHT_GRAY);
            g2d.drawLine(5, 500 - i * 50, 595, 500 - i * 50);
            g2d.setPaint(Color.BLACK);
            g2d.drawString("Floor " + i, 5, 498 - i * 50);
        }
    }

    private void drawElevator(Graphics2D g2d, IElevator elevator)
    {
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(400, getFloorY(elevator.getFloorPosition()), 150, 50);

        g2d.setPaint(Color.BLACK);
        g2d.drawString(elevator.getFloor() + " " + elevator.getDesiredFloor() + " " + elevator.getUsers().size(), 400, getFloorY(elevator.getFloorPosition()) - 10);
    }

    private void drawUser(Graphics2D g2d, float floorPosition, int x, IUser user)
    {
        g2d.setColor(Color.PINK);
        g2d.fillOval(x, getFloorY(floorPosition), 50, 50);
        g2d.setPaint(Color.BLACK);
        g2d.drawString(user.getName() + " " + user.getFloor() + " " + user.getDesiredFloor(), x + 5, getFloorY(floorPosition) + 30);
    }
}
