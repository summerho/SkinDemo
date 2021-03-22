package com.example.skindemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUtils {
    /**
     * 主线程任务分派
     */
    private static final MainThreadDispatcher mainThreadDispatcher = new MainThreadDispatcher();

    private static final int CORE_POOL_SIZE = 65;

    private static final int MAXIMUM_POOL_SIZE = Integer.MAX_VALUE;

    private static final int KEEP_ALIVE = 1;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "Thread #" + mCount.getAndIncrement());
        }
    };

    /**
     * 子线程任务分派
     */
    private static final Executor THREAD_POOL_EXECUTOR
            = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
            TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), sThreadFactory);

    protected final static class MainThreadDispatcher extends Handler {
        MainThreadDispatcher() {
            super(Looper.getMainLooper());
        }
    }

    public static void dispatchToMainThread(Runnable runnable) {
        mainThreadDispatcher.post(runnable);
    }

    public static void dispatchToMainThread(Runnable runnable, long delay) {
        mainThreadDispatcher.postDelayed(runnable, delay);
    }

    public static void dispatchToSubThread(Runnable runnable) {
        THREAD_POOL_EXECUTOR.execute(runnable);
    }

    public static void raiseThreadPriority() {
        try {
            Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_AUDIO);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}
