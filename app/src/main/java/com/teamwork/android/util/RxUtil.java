package com.teamwork.android.util;

import android.app.Dialog;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.teamwork.android.R;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import static android.arch.lifecycle.Lifecycle.Event.ON_DESTROY;

public class RxUtil {

    /**
     * Convenience method for handling {@link Observable}s from the UI.
     * Observes on main thread, unsubscribes when activity is destroyed and
     * displays a loading dialog (cancelable).
     *
     * Use with {@link Observable#compose(Observable.Transformer)}.
     */
    public static <T> Observable.Transformer<T,T> forUi(FragmentActivity activity) {

        return o -> o
            .observeOn(AndroidSchedulers.mainThread())
            .takeUntil(isDestroyed(activity))
            .compose(withLoadingDialog(activity, true));
    }

    /** Emits an item when the activity is destroyed */
    private static Observable<?> isDestroyed(FragmentActivity activity) {

        return Observable.create(subscriber -> {
            activity.getLifecycle().addObserver(new LifecycleObserver() {
                @OnLifecycleEvent(ON_DESTROY)
                void onDestroy() {
                    subscriber.onNext("item");
                }
            });
        });
    }

    /**
     * Displays a loading dialog until the observable ends (completes or throws an error).
     *
     * @param cancelable  if true, the dialog can be canceled; canceling causes the observable to complete
     */
    private static <T> Observable.Transformer<T, T> withLoadingDialog(final Context ctx, boolean cancelable) {

        return new Observable.Transformer<T, T>() {

            private final Dialog dialog = new LoadingDialog(ctx);

            @Override
            public Observable<T> call(Observable<T> observable) {

                final Observable<T> result = observable
                    .doOnSubscribe(() -> dialog.show())
                    .doOnCompleted(() -> LoadingDialog.safeDismiss(dialog))
                    .doOnError(error -> LoadingDialog.safeDismiss(dialog));

                if (cancelable) {
                    return result.takeUntil(isDialogCancelled());
                } else {
                    return result;
                }
            }

            /** Emits an item when the dialog is cancelled */
            private Observable<?> isDialogCancelled() {
                return Observable.create(subscriber -> {
                    dialog.setCancelable(true);
                    dialog.setOnCancelListener(d -> {
                        subscriber.onNext("item");
                        Toast.makeText(ctx, R.string.loading_canceled, Toast.LENGTH_SHORT).show();
                    });
                });
            }
        };
    }
}
