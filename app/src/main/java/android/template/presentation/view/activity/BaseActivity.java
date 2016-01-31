package android.template.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.template.GraphObject;
import android.template.internal.di.module.ActivityModule;

/**
 * @author Tom Koptel
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GraphObject.Factory.from(this)
                .getComponent()
                .inject(this);
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
