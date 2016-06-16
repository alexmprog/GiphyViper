package com.instinctools.giphyviper.presentation.common.di.component;

public interface ActivityComponent {

    interface Injector<T extends ActivityComponent> {
        T createActivityComponent();

        void inject();
    }
}
