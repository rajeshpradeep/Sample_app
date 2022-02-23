package com.example.structure.support;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.structure.R;

/**
 * Created by Rajesh Pradeep G on 11-07-2019
 * this will shown once API called and hide when API finished(round circle progress)
 */
public class CustomProgressDialog extends Dialog {

    public CustomProgressDialog(Context context) {
        super(context);
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    /*comment this when using progress bar*/
   /* public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = findViewById(R.id.spinnerImageView);
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        spinner.start();
    }*/


    public static CustomProgressDialog show(Context context, boolean indeterminate, boolean cancelable) {
        CustomProgressDialog dialog = new CustomProgressDialog(context, R.style.custom_dialog);
//        dialog.getWindow().setWindowAnimations(R.style.zoom);
        dialog.setTitle("");
        dialog.setContentView(R.layout.progress_dialog_layout);

        dialog.setCancelable(cancelable);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.2f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setWindowAnimations(R.style.dialog_zoom);

        dialog.show();
        return dialog;
    }
}
