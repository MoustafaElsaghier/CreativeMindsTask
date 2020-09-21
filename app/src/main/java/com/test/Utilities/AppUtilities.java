package com.test.Utilities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class AppUtilities {
    public static void openURL(Context context, String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(Intent.createChooser(browserIntent, "Open with"));

    }
}
