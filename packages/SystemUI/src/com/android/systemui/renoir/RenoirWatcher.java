package com.android.systemui.renoir;

import android.annotation.NonNull;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.RenoirColors;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;

public class RenoirWatcher {

    public RenoirWatcher(@NonNull Context context) {
        WallpaperManager wm = WallpaperManager.getInstance(context);
        if (RenoirColors.isRenoirAvailable()) {
            wm.addOnColorsChangedListener((colors, which) -> update(context),
                new Handler(context.getMainLooper()), UserHandle.USER_CURRENT);
            update(context);
        }
    }

    private static void update(Context context) {
        if (RenoirColors.shouldForceLoad(context)) RenoirColors.update(context);
    }
}
