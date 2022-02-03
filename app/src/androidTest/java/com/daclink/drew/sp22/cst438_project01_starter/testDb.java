package com.daclink.drew.sp22.cst438_project01_starter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class testDb {

//    public static final String TAG = "Junit";

    @Test
    public void compareUserTest(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDao testDB = Room.inMemoryDatabaseBuilder(context,AppDatabase.class)
                .build()
                .getUserDao();

        //Test Users
        User user = new User(1, "Test","1234",12);
        testDB.insert(user);

        User user2 = testDB.getUserById(user.getId());

        assertEquals(user,user2);

        user2.setUsername("Not Test");
        assertNotEquals(user,user2);
        testDB.insert(user2);

        User user3 = testDB.getUserById(user.getId());
        assertNotEquals(user,user3);
        assertEquals(user2,user3);

    }

    //TODO: Decide if we need the @before and @after sections
//    @After
//    public void closeDb(){
//        testDB.close();
//
//        Log.i(TAG, "closeDb: ");
//    }

    @Test
    public void userReplaceTest(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDao testDB = Room.inMemoryDatabaseBuilder(context,AppDatabase.class)
                .build()
                .getUserDao();

        User user = new User(1, "Test","1234",12);
        testDB.insert(user);

        String name = testDB.getUserById(1).getUsername();
        assertEquals(user.getUsername(), name);

        user.setUsername("New Test");
        testDB.insert(user);
        assertNotEquals(user.getUsername(), name);

    }

    @Test
    public void userDeleteTest(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDao testDB = Room.inMemoryDatabaseBuilder(context,AppDatabase.class)
                .build()
                .getUserDao();

        User user = new User(1, "Test","1234",12);
        testDB.insert(user);

        assertNotNull(testDB.getUserById(1));

        testDB.deleteUser(user);
        assertNull(testDB.getUserById(1));
    }

    @Test //Check size of user list
    public void validateSampleUserNum(){
//        mUserDao.insert(testUserOne, testUserTwo, testUserThree);
//        int count =  mUserDao.getCount();
//        Log.i(TAG, "validateSampleUserNum: Count=" + count);
//        assertEquals(SampleUsers.getUsers().size(), count);
    }

    @Test //check usernames and passwords
    public void validateSampleUserInfo(){
//        mUserDao.insertAllUsers(SampleUsers.getUsers());
//        UserEntity original = SampleUsers.getUsers().get(0);
//        UserEntity fromDB = mUserDao.getUserById(1);
//
//        assertEquals(original.getRecipeCount(), fromDB.getRecipeCount());
//        assertEquals(1, fromDB.getId());
    }
}
