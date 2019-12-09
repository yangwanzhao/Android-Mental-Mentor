package com.example.noahtucker.depression_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

public class evaluate_bot extends DialogFragment {

    private DialogInterface.OnClickListener positiveCallback;

    private DialogInterface.OnClickListener negativeCallback;

    private String title;

    private String message;

    public void show(String title, String message, DialogInterface.OnClickListener positiveCallback,
                     DialogInterface.OnClickListener negativeCallback, FragmentManager fragmentManager) {
        this.title = title;
        this.message = message;
        this.positiveCallback = positiveCallback;
        this.negativeCallback = negativeCallback;
        show(fragmentManager, "ButtonDialogFragment");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Yes", positiveCallback);
        builder.setNegativeButton("No", negativeCallback);
        return builder.create();
    }

}