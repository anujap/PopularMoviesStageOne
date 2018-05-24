package com.example.anuja.popularmoviesstageone.app.activity;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.anuja.popularmoviesstageone.R;
import com.example.anuja.popularmoviesstageone.common.ConnectionStatus;
import com.example.anuja.popularmoviesstageone.model.ConnectionModel;
import com.example.anuja.popularmoviesstageone.receiver.NetworkConnectivityReceiver;

/**
 * This is the Base class for all the Activities
 */
public abstract class BaseActivity extends AppCompatActivity {

    // connection is available
    protected abstract void onConnected();

    // connection is not available
    protected abstract void onDisconnected();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleConnectivity();

    }

    /**
     * function called to handle the connectivity
     */
    private void handleConnectivity() {
        NetworkConnectivityReceiver connectivityReceiver = new NetworkConnectivityReceiver(getApplicationContext());
        connectivityReceiver.observe(this, new Observer<ConnectionModel>() {
            @Override
            public void onChanged(@Nullable ConnectionModel connectionModel) {
                if(connectionModel.getConnectionStatus() == ConnectionStatus.CONNECTED)
                    onConnected();
                else if(connectionModel.getConnectionStatus() == ConnectionStatus.NOT_CONNECTED)
                    onDisconnected();
            }
        });
    }
}
