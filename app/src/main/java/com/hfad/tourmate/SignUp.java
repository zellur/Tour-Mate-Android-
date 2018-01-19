package com.hfad.tourmate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hfad.tourmate.database.UserDataSource;
import com.hfad.tourmate.java_class.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment {


    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        final EditText name,email,phone,password;
        name = view.findViewById(R.id.fullname);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        password = view.findViewById(R.id.password);
        Button signup;
        signup = view.findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userEmail = email.getText().toString();
                String userPhone = phone.getText().toString();
                String userPassword = password.getText().toString();

                UserDataSource source = new UserDataSource(getContext());

                boolean status = source.insertUser(new User(userName,userEmail,userPhone,userPassword));

                if (status){

                    Toast.makeText(getContext(), "Account created Successfully...", Toast.LENGTH_SHORT).show();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    SignIn signIn = new SignIn();
                    ft.replace(R.id.fragment,signIn);
                    ft.addToBackStack(null);
                    ft.commit();
                }else{

                    Toast.makeText(getContext(), "Failed to Create Account ", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                    password.setText("");
                }
            }
        });
       return view;
    }

}
