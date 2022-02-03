package com.daclink.drew.sp22.cst438_project01_starter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    public static final String TAG = "Junit";
//    private AppDatabase testDB;
    private UserDao mUserDao;

    private User testUserOne;
    private User testUserTwo;
    private User testUserThree;

    //@Before
    @Test
    public void compareUserTest(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDao testDB = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build().getUserDao();

        //Test Users
        User user = new User("Nacho","1234",12);
        testDB.insert(user);

        User user2 = testDB.getUserById(user.getId());

        assertEquals(user,user2);

        user2.setUsername("Not Nacho");
        assertNotEquals(user,user2);

        testDB.insert(user2);

        User user3 = testDB.getUserById(user.getId());
        assertNotEquals(user,user3);
        assertEquals(user2,user3);

//        mUserDao = mDb.us
//        Log.i(TAG, "DB Up");
    }

//    @After
//    public void closeDb(){
//        testDB.close();
//
//        Log.i(TAG, "closeDb: ");
//    }

    @Test
    public void userInsertTest(){

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
