package sample.template.test.activity;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import sample.template.R;
import sample.template.presentation.view.activity.StubActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author Tom Koptel
 */
@RunWith(AndroidJUnit4.class)
public class StubActivityTest {
    @Rule
    public IntentsTestRule<StubActivity> activityRule = new IntentsTestRule<>(
            StubActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False so we set up before activity launch

    @Test
    public void espresso_should_await_async_task() throws Exception {
        activityRule.launchActivity(null);
        onView(withId(R.id.testView)).check(matches(withText("After 3 seconds")));
    }
}
