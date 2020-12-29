package com.chk.androidlearning.di.module;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * Created by CHK on 20-12-27.
 */
@Module
@InstallIn({ActivityComponent.class})
public class DiModuleTest {

    @Provides
    public ModuleBean provideModuleBean() {
        return new ModuleBean("GDUT",1);
    }

}
