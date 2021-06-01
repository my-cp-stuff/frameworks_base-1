package android.content.res;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.graphics.Color;
import android.util.Log;
import android.content.Context;
import android.provider.Settings;
import android.app.ActivityThread;

/** @hide */
public class RenoirColorUtils {

    private RenoirColors reno;

    private String[] accentResources = {
            "accent_device_default",
            "accent_material_light",
            "accent_material_dark",
            "accent_device_default_light",
            "accent_device_default_dark",
            "colorAccent"};

    private String[] styleBackgroundLightResources = {
            "background_material_light",
            "primary_material_light",
            "primary_material_settings_light",
            "background_device_default_light",
            "primary_device_default_light",
            "primary_device_default_settings_light",};

    private String[] stylePrimaryLightResources = {
            "background_floating_material_light",
            "primary_dark_material_light",
            "background_floating_device_default_light",
            "primary_dark_device_default_light"};

    private String[] styleBackgroundDarkResources = {
            "background_material_dark",
            "primary_material_dark",
            "primary_material_settings_dark",
            "background_device_default_dark",
            "primary_device_default_dark",
            "primary_device_default_settings_dark",};

    private String[] stylePrimaryDarkResources = {
            "background_floating_material_dark",
            "primary_dark_material_dark",
            "background_floating_device_default_dark",
            "primary_dark_device_default_dark"};


    public RenoirColorUtils() {
        this.reno = new RenoirColors(ActivityThread.currentApplication());
    }

    private final String TAG = "RenoirColorUtils";

    public int updateColorResource(@Nullable String resName, int defaultColor) {
        int colorToUpdate;
        if (resName != null) {
            if (resName.contains("rnr_accent_primary_color_accent") ||
                (reno.isRenoAccentEnabled() && reno.getCurrentRenoAccentType() == 0 &&
                    isAnyOfSystemAccent(resName))) {
                    colorToUpdate = reno.getAccentColor(0);
            } else if (resName.contains("rnr_accent_primary_color_gradient")) {
                    colorToUpdate = reno.getAccentColor(1);
            } else if (resName.contains("rnr_accent_secondary_color_accent") ||
                (reno.isRenoAccentEnabled() && reno.getCurrentRenoAccentType() == 1 &&
                    isAnyOfSystemAccent(resName))) {
                    colorToUpdate = reno.getSecAccentColor(0);
            } else if (resName.contains("rnr_accent_secondary_color_gradient")) {
                    colorToUpdate = reno.getSecAccentColor(1);
            } else if (resName.contains("rnr_accent_myterial_color_accent") ||
                (reno.isRenoAccentEnabled() && reno.getCurrentRenoAccentType() == 2 &&
                    isAnyOfSystemAccent(resName))) {
                    colorToUpdate = reno.getMyAccentColor(0);
            } else if (resName.contains("rnr_accent_myterial_color_gradient")) {
                    colorToUpdate = reno.getMyAccentColor(1);
            } else if (resName.contains("rnr_myterial_background_color_light") ||
                (reno.isRenoStyleEnabled() && reno.getCurrentRenoStyleType() == 0 &&
                    isAnyOfSystemBgLightColors(resName))) {
                    colorToUpdate = reno.getMyStyleColor(0);
            } else if (resName.contains("rnr_myterial_primary_color_light") ||
                (reno.isRenoStyleEnabled() && reno.getCurrentRenoStyleType() == 0 &&
                    isAnyOfSystemPrLightColors(resName))) {
                    colorToUpdate = reno.getMyStyleColor(1);
            } else if (resName.contains("rnr_myterial_background_color_dark") ||
                (reno.isRenoStyleEnabled() && reno.getCurrentRenoStyleType() == 0 &&
                    isAnyOfSystemBgDarkColors(resName))) {
                    colorToUpdate = reno.getMyStyleColor(2);
            } else if (resName.contains("rnr_myterial_primary_color_dark") ||
                (reno.isRenoStyleEnabled() && reno.getCurrentRenoStyleType() == 0 &&
                    isAnyOfSystemPrDarkColors(resName))) {
                    colorToUpdate = reno.getMyStyleColor(3);
            } else if (resName.contains("rnr_tinted_background_color_light") ||
                (reno.isRenoStyleEnabled() && reno.getCurrentRenoStyleType() == 1 &&
                    isAnyOfSystemBgLightColors(resName))) {
                    colorToUpdate = reno.getTTStyleColor(0);
            } else if (resName.contains("rnr_tinted_primary_color_light") ||
                (reno.isRenoStyleEnabled() && reno.getCurrentRenoStyleType() == 1 &&
                    isAnyOfSystemPrLightColors(resName))) {
                    colorToUpdate = reno.getTTStyleColor(1);
            } else if (resName.contains("rnr_tinted_background_color_dark") ||
                (reno.isRenoStyleEnabled() && reno.getCurrentRenoStyleType() == 1 &&
                    isAnyOfSystemBgDarkColors(resName))) {
                    colorToUpdate = reno.getTTStyleColor(2);
            } else if (resName.contains("rnr_tinted_primary_color_dark") ||
                (reno.isRenoStyleEnabled() && reno.getCurrentRenoStyleType() == 1 &&
                    isAnyOfSystemPrDarkColors(resName))) {
                    colorToUpdate = reno.getTTStyleColor(3);
            } else {
                return defaultColor;
            }
            try {
                return colorToUpdate == -1 ? defaultColor : colorToUpdate;
            } catch (Exception e) {
                Log.e(TAG, "couldnt set color for reno themes");
            }
        }
        return defaultColor;
    }

    private boolean isAnyOfSystemAccent(@Nullable String resName) {
        for (String ar : accentResources) {
            if (resName.contains(ar)){
                return true;
            }
        }
        return false;
    }

    private boolean isAnyOfSystemBgLightColors(@Nullable String resName) {
        for (String blr : styleBackgroundLightResources) {
            if (resName.contains(blr)){
                return true;
            }
        }
        return false;
    }

    private boolean isAnyOfSystemPrLightColors(@Nullable String resName) {
        for (String plr : stylePrimaryLightResources) {
            if (resName.contains(plr)){
                return true;
            }
        }
        return false;
    }

    private boolean isAnyOfSystemBgDarkColors(@Nullable String resName) {
        for (String bdr : styleBackgroundDarkResources) {
            if (resName.contains(bdr)){
                return true;
            }
        }
        return false;
    }

    private boolean isAnyOfSystemPrDarkColors(@Nullable String resName) {
        for (String pdr : stylePrimaryDarkResources) {
            if (resName.contains(pdr)){
                return true;
            }
        }
        return false;
    }
}
