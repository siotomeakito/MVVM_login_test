package com.siotome.mvvm_login_test;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.widget.Toast;

import com.siotome.mvvm_login_test.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        final LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setMyViewModel(loginViewModel);
        binding.setLifecycleOwner(this);

        loginViewModel.getUser().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object obj){
                Log.d("dt_db", "ma31");
                UserDataInput userObj = (UserDataInput)obj;
                if(userObj == null){
                    return;
                }
                if(!checkLegality(userObj, loginViewModel)){
                    return;
                }
                if(!compareEmailAndPassword(userObj, loginViewModel)){
                    return;
                }
                Toast.makeText(getApplicationContext(), "Email : " + userObj.getEmail() + " Password " + userObj.getPassword(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkLegality(UserDataInput myUser, LoginViewModel myViewModel){
        if(myUser.getEmail().isEmpty()){
            myViewModel.emailError.setValue(getString(R.string.email_empty));
            return false;
        }
        if(!myUser.isEmailValid()){
            myViewModel.emailError.setValue(getString(R.string.email_invalid));
            return false;
        }
        if(myUser.getPassword().isEmpty()){
            myViewModel.passwordError.setValue(getString(R.string.password_empty));
            return false;
        }
        return true;
    }

    private boolean compareEmailAndPassword(UserDataInput myUser, LoginViewModel myViewModel){
        if(!UserLogin.checkEmailAddress(myUser.getEmail())){
            myViewModel.emailError.setValue(getString(R.string.email_wrong));
            return false;
        }
        if(!UserLogin.checkPassword(myUser.getPassword())){
            myViewModel.passwordError.setValue(getString(R.string.password_wrong));
            return false;
        }
        return true;
    }
}