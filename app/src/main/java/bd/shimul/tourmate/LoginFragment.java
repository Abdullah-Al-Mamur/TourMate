package bd.shimul.tourmate;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {
    public Button signinBTN;
    public TextView signuppageLink;
    public EditText  emailInput, passwordInput;
    public String  email, password;
    public FirebaseAuth firebaseAuth;
    public ProgressBar progressBar;


    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        signuppageLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.master_frameLayout, new SignupFragment());
                fragmentTransaction.commit();
            }
        });

        signinBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                email = emailInput.getText().toString().trim();
                password = passwordInput.getText().toString().trim();
               if(TextUtils.isEmpty(email)){
                    emailInput.setError("Not a valid email.");
                    return;

                }else if(TextUtils.isEmpty(password)){
                    passwordInput.setError("Password empty.");
                    return;
                }else if(isValidEmail(email) != true){
                    emailInput.setError("Not a valid email.");
                    return;
                }


                AlertDialog.Builder completeAlert = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater = LayoutInflater.from(view.getContext());
                View view1 = inflater.inflate(R.layout.completing_dialouge, null);
                completeAlert.setView(view1);
                final AlertDialog dialog = completeAlert.create();
                dialog.show();

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Signin successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(view.getContext(), HomeActivity.class);
                            startActivity(intent);
                        }else{
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Sign in failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
        return view;
    }

    private void init(View view) {
        signinBTN = view.findViewById(R.id.signinBTN);
        emailInput = view.findViewById(R.id.loginemailET);
        passwordInput = view.findViewById(R.id.loginpasswordET);
        signuppageLink = view.findViewById(R.id.signupLink);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = view.findViewById(R.id.progress_circular);
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
