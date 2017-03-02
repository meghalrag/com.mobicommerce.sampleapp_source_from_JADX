package org.apache.cordova;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.paypal.android.sdk.payments.BuildConfig;
import java.io.File;

@Deprecated
public class DirectoryManager {
    private static final String LOG_TAG = "DirectoryManager";

    public static boolean testFileExists(String name) {
        if (!testSaveLocationExists() || name.equals(BuildConfig.VERSION_NAME)) {
            return false;
        }
        return constructFilePaths(Environment.getExternalStorageDirectory().toString(), name).exists();
    }

    public static long getFreeDiskSpace(boolean checkInternal) {
        long freeSpace;
        if (Environment.getExternalStorageState().equals("mounted")) {
            freeSpace = freeSpaceCalculation(Environment.getExternalStorageDirectory().getPath());
        } else if (!checkInternal) {
            return -1;
        } else {
            freeSpace = freeSpaceCalculation("/");
        }
        return freeSpace;
    }

    private static long freeSpaceCalculation(String path) {
        StatFs stat = new StatFs(path);
        return (((long) stat.getAvailableBlocks()) * ((long) stat.getBlockSize())) / 1024;
    }

    public static boolean testSaveLocationExists() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    private static File constructFilePaths(String file1, String file2) {
        if (file2.startsWith(file1)) {
            return new File(file2);
        }
        return new File(new StringBuilder(String.valueOf(file1)).append("/").append(file2).toString());
    }

    public static String getTempDirectoryPath(Context ctx) {
        File cache;
        if (Environment.getExternalStorageState().equals("mounted")) {
            cache = new File(new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath())).append("/Android/data/").append(ctx.getPackageName()).append("/cache/").toString());
        } else {
            cache = ctx.getCacheDir();
        }
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return cache.getAbsolutePath();
    }
}
