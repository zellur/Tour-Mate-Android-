package com.hfad.tourmate;


import android.content.Context;
import android.content.Intent;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class SignIn extends Fragment {


    public SignIn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_sign_in, container, false);

        final EditText userPhone = v.findViewById(R.id.phoneNo);
        final EditText userPassword = v.findViewById(R.id.password);
        Button signIn = v.findViewById(R.id.signin);
        Button signUp = v.findViewById(R.id.signup);
        Button forgotPass = v.findViewById(R.id.forgot);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataSource source = new UserDataSource(getContext());
                String user = userPhone.getText().toString();
                String userGivenPass = userPassword.getText().toString();
                String userpass = source.getUserPassword(user);

                if(userpass.equals(userGivenPass)){

                    Intent intent = new Intent(getContext(),CreateEvent.class);
                    startActivity(intent);

                    Toast.makeText(getContext(), "Welcome to your Events", Toast.LENGTH_SHORT).show();
                    userPhone.setText("");
                    userPassword.setText("");


                }else{

                    Toast.makeText(getContext(), "User Does Not Match!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                SignUp signUp1 = new SignUp();
                fragmentTransaction.replace(R.id.fragment,signUp1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return v;
    }

}
