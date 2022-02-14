package com.daclink.drew.sp22.cst438_project01_starter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestDb {

//    public static final String TAG = "Junit";

    /**
     * Simple unit test to compare users that have been put into the DB. Essentially makes sure
     * users are being stored and retrieved properly
     */
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

    /**
     * Unit test to validate that inserting a user that already exists will overwrite the existing
     * data for that user. Test is done by creating a new user, inserting them into the DB, then
     * checking that the original username is the same as whats stored in the DB. Then the username
     * is changed and the values are compared again to make sure they are different to validate
     * replacement worked
     */
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

    /**
     * Test to make sure that the delete user function works. A user is made, inserted into DB,
     * checked to make sure they exist, deleted from DB, and finally checked to make sure they dont
     * exist anymore
     */
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

    @Test
    public void verifyAccount(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDao testDB = Room.inMemoryDatabaseBuilder(context,AppDatabase.class)
                .build()
                .getUserDao();

        User user = new User(1, "Test","1234",12);
        assertNull(testDB.getUserById(1));
        testDB.insert(user);
        assertNotNull(testDB.getUserById(1));
    }
}
