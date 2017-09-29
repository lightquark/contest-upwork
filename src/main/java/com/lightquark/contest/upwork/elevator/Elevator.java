package com.lightquark.contest.upwork.elevator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Light Quark.
 */
public class Elevator implements IElevator
{
    private static final int NONE = -1;

    private int capacity;
    private float speed;
    private int floor;
    private float floorPosition;
    private int desiredFloor;
    private List<IUser> users;

    public Elevator(int capacity, float speed, int initialFloor)
    {
        this.capacity = capacity;
        this.speed = speed;
        this.floor = initialFloor;
        this.floorPosition = floor;
        this.desiredFloor = NONE;
        this.users = new CopyOnWriteArrayList<>();
    }

    @Override
    public boolean addUser(IUser u)
    {
        if (isFull())
            return false;

        //TODO: sort users to achieve effective movement
        users.add(u);
        return true;
    }

    @Override
    public boolean moveToFloor()
    {
        if (desiredFloor == NONE)
            return false;

        if (desiredFloor < floor)
        {
            floorPosition -= speed;

            int x = (int) Math.ceil(floorPosition);
            if (floor != x)
            {
                floor = x;
                letOutUsers();
                return true;
            }
        }
        else if (desiredFloor > floor)
        {
            floorPosition += speed;

            int x = (int) Math.floor(floorPosition);
            if (floor != x)
            {
                floor = x;
                letOutUsers();
                return true;
            }
        }
        else if (desiredFloor == floor)
        {
            letOutUsers();
            return true;
        }
        return false;
    }

    @Override
    public void letOutUsers()
    {
        //let out users from elevator
        int i = 0;
        while (i < users.size())
        {
            if (users.get(i).getDesiredFloor() == floor)
            {
                users.remove(i);
                continue;
            }
            i++;
        }

        if (desiredFloor == floor)
            desiredFloor = users.size() > 0 ? users.get(0).getDesiredFloor() : NONE;
    }

    @Override
    public void callUp(int floor)
    {
        if (this.desiredFloor == NONE)
            this.desiredFloor = floor;
    }

    @Override
    public boolean isFull()
    {
        return users.size() >= capacity;
    }

    @Override
    public int getFloor()
    {
        return floor;
    }

    @Override
    public int getDesiredFloor()
    {
        return desiredFloor;
    }

    @Override
    public float getFloorPosition()
    {
        return floorPosition;
    }

    @Override
    public List<IUser> getUsers()
    {
        return users;
    }
}
