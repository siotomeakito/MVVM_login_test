package com.siotome.mvvm_login_test;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel{
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> emailError = new MutableLiveData<>();
    public MutableLiveData<String> passwordError = new MutableLiveData<>();
    public MutableLiveData<String> userName = new MutableLiveData<>();

    private MutableLiveData<UserDataInput> userMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<String> tmpA = new MutableLiveData<>();
    public MutableLiveData<String> tmpB = new MutableLiveData<>();

    public LoginViewModel(){

    }

    public void onLoginButtonClicked(){
        emailError.setValue(null);
        passwordError.setValue(null);
        UserDataInput user = new UserDataInput(email.getValue(), password.getValue());
//        userName.setValue(null);
        userMutableLiveData.setValue(user);
    }

    LiveData<UserDataInput> getUser() {
        return userMutableLiveData;
    }
}
