package sample.template.presentation.view.activity;

import android.os.Bundle;

import sample.template.R;
import sample.template.presentation.view.fragment.StubListFragment;

public class StubActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stub);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, StubListFragment.newInstance())
                    .commit();
        }
    }
}
