package com.ganipohan.mvvmproject.viewModel;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ganipohan.mvvmproject.BR;
import com.ganipohan.mvvmproject.model.Model;

import java.util.regex.Pattern;

public class ViewModel extends BaseObservable {
    //membuat objek model class
    private Model model;

    private String successMessage = "Berhasil login!";
    private String errorMessage = "Email dan Password tidak sesuai!";

    @Bindable
    private String toastMessage = null;

    //getter dan setter untuk toast
    public String getToastMessage(){
        return toastMessage;
    }
    private void setToastMessage(String toastMessage){
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    //getter dan setter email
    @Bindable
    public String getUserEmail(){
        return model.getEmail();
    }
    public void setUserEmail(String email){
        model.setEmail(email);
        notifyPropertyChanged(BR.userEmail);
    }

    //getter dan setter password
    @Bindable
    public String getUserPassword(){
        return model.getPassword();
    }
    public void setUserPassword(String password){
        model.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    public ViewModel(){
        //inisialisasi model
        model = new Model("", "");
    }

    public void onButtonClicked(){
        if (isValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);
    }

    //cek email dan password field
    public boolean isValid(){
        return !TextUtils.isEmpty(getUserEmail())
                && Patterns.EMAIL_ADDRESS.matcher(getUserEmail()).matches()
                && getUserPassword().length() > 5;
    }
}
