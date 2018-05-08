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
                if(connectionModel.getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    //update accordingly
                }
                else if(connectionModel.getConnectionStatus() == ConnectionStatus.NOT_CONNECTED) {
                    // update accordingly
                    Toast.makeText(BaseActivity.this, "Device is not connected", Toast.LENGTH_LONG).show();
                    showConnectionDialog();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Function called to show connection dialog, when there is no
     * connectivity available.
     */
    private void showConnectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(R.string.no_connection_title);
        builder.setMessage(R.string.no_connection_message);
        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        builder.show();
    }
}
