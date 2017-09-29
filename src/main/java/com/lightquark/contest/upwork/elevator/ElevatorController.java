package com.lightquark.contest.upwork.elevator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Light Quark.
 */
public class ElevatorController implements IElevatorController
{
    private IElevator elevator;
    private List<IUser> users;

    public ElevatorController()
    {
        this.elevator = new Elevator(Settings.ELEVATOR_CAPACITY_MAX, Settings.ELEVATOR_SPEED, Settings.FLOOR_MIN);
        this.users = new CopyOnWriteArrayList<>();
    }

    @Override
    public void tryToCreateUser()
    {
        //create user with probability 10%
        if (users.size() < Settings.USERS_MAX && ThreadLocalRandom.current().nextFloat() < 0.1f)
            users.add(UserFactory.createUser());
    }

    @Override
    public void update()
    {
        if (elevator.moveToFloor())
        {
            //let out users from the elevator
            elevator.letOutUsers();

            putUsersToElevator();
        }
        else
        {
            putUsersToElevator();

            if (users.size() > 0)
                elevator.callUp(users.get(0).getFloor());
        }
    }

    private void putUsersToElevator()
    {
        //add users from the floor on which stands the elevator
        if (users.size() > 0 && !elevator.isFull())
        {
            int elevatorFloor = elevator.getFloor();

            int i = 0;
            while (i < users.size())
            {
                IUser u = users.get(i);
                if (u.getFloor() == elevatorFloor)
                {
                    if (elevator.addUser(u))
                    {
                        users.remove(i);
                        continue;
                    }
                }
                i++;
            }
        }
    }

    @Override
    public IElevator getElevator()
    {
        return elevator;
    }

    @Override
    public List<IUser> getUsers()
    {
        return users;
    }

    @Override
    public List<IUser> getElevatorUsers()
    {
        return elevator.getUsers();
    }
}
