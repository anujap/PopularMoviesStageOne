package com.example.anuja.popularmoviesstageone.model;

import com.example.anuja.popularmoviesstageone.common.ConnectionStatus;

public class ConnectionModel {

    /**
     * the signal strength
     */
    private ConnectionStatus connectionStatus;

    public ConnectionModel(ConnectionStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public ConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }
}
