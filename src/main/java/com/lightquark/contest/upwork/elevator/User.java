package com.lightquark.contest.upwork.elevator;

/**
 * Created by Light Quark.
 */
public class User implements IUser
{
    private String name;
    private int floor;
    private int desiredFloor;

    public User(String name, int initialFloor, int desiredFloor)
    {
        this.name = name;
        this.floor = initialFloor;
        this.desiredFloor = desiredFloor;
    }

    @Override
    public String getName()
    {
        return name;
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
}
