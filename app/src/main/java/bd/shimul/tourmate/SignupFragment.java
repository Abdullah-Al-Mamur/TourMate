package bd.shimul.tourmate;


import android.app.AlertDialog;
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

import org.w3c.dom.Text;


public class SignupFragment extends Fragment {
    public Button signupBTN;
    public TextView signinpageLink;
    public EditText firstNameInput, lastNameInput, emailInput, passwordInput, confirmPasswordInput;
    public String firstname, lastname, email, password, confirmpass;
    public FirebaseAuth firebaseAuth;
    public ProgressBar progressBar;


    public SignupFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        init(view);
        signinpageLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.master_frameLayout, new LoginFragment());
                fragmentTransaction.commit();
            }
        });

        signupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                firstname = firstNameInput.getText().toString().trim();
                lastname = lastNameInput.getText().toString().trim();
                email = emailInput.getText().toString().trim();
                password = passwordInput.getText().toString().trim();
                confirmpass = confirmPasswordInput.getText().toString().trim();



                if(TextUtils.isEmpty(firstname)){
                    firstNameInput.setError("First name required.");
                    return;
                }else if(TextUtils.isEmpty(lastname)){
                    lastNameInput.setError("Last name required.");
                    return;

                }else if(TextUtils.isEmpty(email)){
                    emailInput.setError("Email required.");
                    return;

                }else if(TextUtils.isEmpty(password)){
                    passwordInput.setError("Password required.");
                    return;
                }else if(!TextUtils.equals(password, confirmpass)){
                    passwordInput.setError("Password didn't matched.");
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



                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            dialog.dismiss();
                            Toast.makeText(view.getContext(), "Registration complete.", Toast.LENGTH_SHORT).show();

                        }else{
                            dialog.dismiss();
                            Toast.makeText(view.getContext(), "Registration failed.", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });
        return view;
    }

    private void init(View view) {
        signupBTN = view.findViewById(R.id.signupBTN);
        signinpageLink = view.findViewById(R.id.signinLink);
        firstNameInput = view.findViewById(R.id.signupFirstNameET);
        lastNameInput = view.findViewById(R.id.signupLastNameET);
        emailInput = view.findViewById(R.id.signupemailET);
        passwordInput = view.findViewById(R.id.signuppasswordET);
        confirmPasswordInput = view.findViewById(R.id.signupconfpasswordET);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = view.findViewById(R.id.progress_circular);

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
