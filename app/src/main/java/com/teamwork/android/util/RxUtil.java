package com.teamwork.android.util;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.teamwork.android.BaseActivity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import static android.arch.lifecycle.Lifecycle.Event.ON_DESTROY;

public class RxUtil {

    /**
     * Convenience method for handling {@link Observable}s from the UI.
     * Observes on main thread, unsubscribes when activity is destroyed.
     *
     * Use with {@link Observable#compose(Observable.Transformer)}.
     */
    public static <T> Observable.Transformer<T,T> forUi(BaseActivity activity) {

        return o -> o
            .observeOn(AndroidSchedulers.mainThread())
            .takeUntil(isDestroyed(activity));
    }

    /** Emits an item when the activity is destroyed */
    private static Observable<?> isDestroyed(BaseActivity activity) {

        return Observable.create(subscriber -> {
            activity.getLifecycle().addObserver(new LifecycleObserver() {
                @OnLifecycleEvent(ON_DESTROY)
                void onDestroy() {
                    subscriber.onNext(1); // value is not important
                }
            });
        });
    }
}
