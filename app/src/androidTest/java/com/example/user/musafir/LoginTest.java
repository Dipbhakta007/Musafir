package com.example.user.musafir;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by USER on 10/29/2019.
 */
public class LoginTest {
    @Rule
    public ActivityTestRule<Login>loginActivityTestRule=new ActivityTestRule<Login>(Login.class);

    private Login login=null;

    Instrumentation.ActivityMonitor monitor= getInstrumentation().addMonitor(VerifyPhoneActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        login=loginActivityTestRule.getActivity();

    }

    @Test
    public void test(){
        assertNotNull(login.findViewById(R.id.login));
        onView(withId(R.id.loginMobile)).perform(clearText(), typeText("01716934326"));
        onView(withId(R.id.login)).perform(click());

        Activity verifyPhoneActivity=getInstrumentation().waitForMonitorWithTimeout(monitor,60000);
        assertNotNull(verifyPhoneActivity);
        verifyPhoneActivity.finish();

    }

    @After
    public void tearDown() throws Exception {

    }

}