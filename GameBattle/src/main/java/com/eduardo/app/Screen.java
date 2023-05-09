package com.eduardo.app;

public interface Screen {

    public void OnData(String data);

    public void OnClose();

    public void OnConnect();

    public void OnResponse(boolean ok, String response, String message);

}
