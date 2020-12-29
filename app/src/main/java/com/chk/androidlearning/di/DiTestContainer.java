package com.chk.androidlearning.di;

import javax.inject.Inject;

/**
 * Created by CHK on 20-12-27.
 */
public class DiTestContainer {

    DiTest diTest;

    @Inject
    public DiTestContainer(DiTest diTest) {
        this.diTest = diTest;
    }

    public DiTest getDiTest() {
        return diTest;
    }

    public void setDiTest(DiTest diTest) {
        this.diTest = diTest;
    }
}
