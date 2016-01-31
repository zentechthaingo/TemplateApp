package sample.template.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import sample.template.GraphObject;
import sample.template.internal.di.component.AppComponent;
import sample.template.internal.di.module.ActivityModule;

/**
 * @author Tom Koptel
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);
    }

    protected AppComponent getAppComponent() {
        return GraphObject.Factory.from(this).getComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
