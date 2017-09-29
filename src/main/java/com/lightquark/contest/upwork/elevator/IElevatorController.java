package com.lightquark.contest.upwork.elevator;

import java.util.List;

/**
 * Created by Light Quark.
 */
public interface IElevatorController
{
    void tryToCreateUser();

    void update();

    IElevator getElevator();

    List<IUser> getUsers();

    List<IUser> getElevatorUsers();
}
