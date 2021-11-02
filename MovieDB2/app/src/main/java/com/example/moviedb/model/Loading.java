package com.example.moviedb.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;

import com.example.moviedb.R;

public class Loading {
//private Activity activity;
private Fragment fragmentn;
    private AlertDialog dialog;

    public Loading(Fragment myfargm){
        fragmentn = myfargm;
    }
    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(fragmentn.getContext());

        LayoutInflater inflater = fragmentn.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading, null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }
    public void dismiss(){
        dialog.dismiss();}
}
