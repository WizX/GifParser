package com.didikee.gifparser.ui.frag;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.didikee.gifparser.R;

/**
 * Created by didik on 2017/2/19.
 */

public class HelpInfoDialog extends DialogFragment {

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View customView;
        try {
            customView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_webview, null);
        } catch (InflateException e) {
            throw new IllegalStateException("This device does not support Web Views.");
        }
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .theme(Theme.LIGHT)
                .title(R.string.action_help)
                .customView(customView, false)
                .positiveText(android.R.string.ok)
                .build();

        final WebView webView = (WebView) customView.findViewById(R.id.webview);
        webView.loadUrl("file:///android_asset/help.html");
        return dialog;
    }

}
