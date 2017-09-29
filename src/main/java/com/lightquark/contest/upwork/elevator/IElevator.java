package com.lightquark.contest.upwork.elevator;

import java.util.List;

/**
 * Created by Light Quark.
 */
public interface IElevator
{
    boolean addUser(IUser u);

    boolean moveToFloor();

    void letOutUsers();

    void callUp(int floor);

    boolean isFull();

    int getFloor();

    int getDesiredFloor();

    float getFloorPosition();

    List<IUser> getUsers();
}
