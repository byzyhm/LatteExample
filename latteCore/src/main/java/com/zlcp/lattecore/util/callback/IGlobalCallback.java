package com.zlcp.lattecore.util.callback;


import androidx.annotation.NonNull;

public interface IGlobalCallback<T> {

    void executeCallback(@NonNull T args);

}
