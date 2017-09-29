package com.lightquark.contest.upwork.elevator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Light Quark.
 */
public class UserFactory
{
    private static final String[] NAMES = { "Paul", "Anna", "John", "Mary", "Jack", "Mike", "Colin", "Ned", "Hugo", "Fred", "Owen", "Ken", "Bob" };

    public static IUser createUser()
    {
        String name = NAMES[ThreadLocalRandom.current().nextInt(NAMES.length)];

        int initialFloor = Settings.FLOOR_MIN + ThreadLocalRandom.current().nextInt(Settings.FLOOR_MAX - Settings.FLOOR_MIN);
        int desiredFloor = Settings.FLOOR_MIN + ThreadLocalRandom.current().nextInt(Settings.FLOOR_MAX - Settings.FLOOR_MIN);
        //if random generated floors is equal, we make them different manually
        if (initialFloor == desiredFloor)
            desiredFloor = initialFloor != Settings.FLOOR_MAX ? Settings.FLOOR_MAX : Settings.FLOOR_MIN;

        return new User(name, initialFloor, desiredFloor);
    }
}
