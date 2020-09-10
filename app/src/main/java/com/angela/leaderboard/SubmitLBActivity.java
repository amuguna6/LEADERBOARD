package com.angela.leaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SubmitLBActivity extends AppCompatActivity {
    private TextInputEditText firstNameinput;
    private TextInputEditText lastNameinput;
    private TextInputEditText emailinput;
    private TextInputEditText githubLinkinout;
    private Button buttonsubmitlb;

    private String firstname;
    private String lastname;
    private String emailaddress;
    private String githublink;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_submit_l_b);

        MaterialToolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        Objects.requireNonNull (getSupportActionBar ()).setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);

        firstNameinput = findViewById (R.id.submit_lb_first_name);
        lastNameinput = findViewById (R.id.submit_lb_last_name);
        emailinput = findViewById (R.id.submit_lb_email);
        githubLinkinout = findViewById (R.id.submit_lb_github);

        buttonsubmitlb = findViewById (R.id.submit_lb_button);
        buttonsubmitlb.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick( View view ) {
                inputValidations ();
            }

            private void inputValidations() {

                String noEmptyInput = "No Blank Input";
                String ValidEmail = "Enter User Valid Email ";
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

                firstname = Objects.requireNonNull (firstNameinput.getText ()).toString ();
                lastname = Objects.requireNonNull (lastNameinput.getText ()).toString ();
                emailaddress = Objects.requireNonNull (emailinput.getText ()).toString ();
                githublink = Objects.requireNonNull (githubLinkinout.getText ()).toString ();
                if (firstname.isEmpty ()) {
                    firstNameinput.setError (noEmptyInput);
                    return;

                }
                if (lastname.isEmpty ()) {
                    lastNameinput.setError (noEmptyInput);
                }
                if (!(emailaddress.matches ((emailPattern)) || emailaddress.matches (emailPattern2))) {
                    emailinput.setError (ValidEmail);

                }
                if (githublink.isEmpty ()) {
                    githubLinkinout.setError (noEmptyInput);
                }

                //Alert Dialog
                Rect displayRectangle = new Rect ();
                Window window = SubmitLBActivity.this.getWindow ();
                window.getDecorView ().getWindowVisibleDisplayFrame (displayRectangle);
                final AlertDialog.Builder builder = new AlertDialog.Builder (
                        SubmitLBActivity.this,R.style.CustomDialogSubmitLB);
                ViewGroup viewGroup = findViewById (android.R.id.content);
                View view = LayoutInflater.from (SubmitLBActivity.this)
                        .inflate (R.layout.dialog_confirm_submitlb,viewGroup,false);
                view.setMinimumWidth ((int) (displayRectangle.width () * 1f));
                builder.setView (view);

                final AlertDialog alertDialog = builder.create ();
                alertDialog.show ();

                Button submitButton = view.findViewById (R.id.submit_lb_button);
                AppCompatImageView cancelDialog = view.findViewById (R.id.submit_lb_button);

                submitButton.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick( View view ) {
                        postProjectDetails ();
                        alertDialog.dismiss ();
                    }
                });
                cancelDialog.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick( View view ) {
                        alertDialog.dismiss ();
                    }
                });

            }

            private void postProjectDetails() {
                RequestQueue requestQueue = Volley.newRequestQueue (SubmitLBActivity.this);
                String postUrl = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
                StringRequest stringRequest = new StringRequest (Request.Method.POST,postUrl,
                        new Response.Listener<String> () {
                            @Override
                            public void onResponse( String response ) {
                                Rect displayRectangle = new Rect ();
                                Window window = SubmitLBActivity.this.getWindow ();
                                window.getDecorView ().getWindowVisibleDisplayFrame (displayRectangle);
                                final AlertDialog.Builder builder = new AlertDialog.Builder (
                                        SubmitLBActivity.this,R.style.CustomDialogSubmitLB);
                                ViewGroup viewGroup = findViewById (android.R.id.content);
                                View view = LayoutInflater.from (SubmitLBActivity.this)
                                        .inflate (R.layout.dialog_unsuccessful_submitlb,viewGroup,false);
                                view.setMinimumWidth ((int) (displayRectangle.width () * 1f));
                                builder.setView (view);

                                final AlertDialog alertDialog = builder.create ();
                                alertDialog.show ();

                            }
                        },new Response.ErrorListener () {
                    @Override
                    public void onErrorResponse( VolleyError error ) {
                        Log.d ("Error","==" + error);

                        Rect displayRectangle = new Rect ();
                        Window window = SubmitLBActivity.this.getWindow ();
                        window.getDecorView ().getWindowVisibleDisplayFrame (displayRectangle);
                        final AlertDialog.Builder builder = new AlertDialog.Builder (
                                SubmitLBActivity.this,R.style.CustomDialogSubmitLB);
                        ViewGroup viewGroup = findViewById (android.R.id.content);
                        View view = LayoutInflater
                                .from (SubmitLBActivity.this)
                                .inflate (R.layout.dialog_unsuccessful_submitlb,viewGroup,false);
                        view.setMinimumWidth ((int) (displayRectangle.width () * 1f));
                        builder.setView (view);
                        final AlertDialog alertDialog = builder.create ();
                        alertDialog.show ();


                    }
                }) {
                    @Override
                    protected Response<String> parseNetworkResponse( NetworkResponse response ) {
                        int mStatusCode = response.statusCode;
                        Log.d ("Response Code","status" + mStatusCode);
                        return super.parseNetworkResponse (response);

                    }

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<> ();
                        params.put ("entry.1824927963",emailaddress);
                        params.put ("entry.1877115667",firstname);
                        params.put ("entry.2006916086",lastname);
                        params.put ("entry.284483984",githublink);
                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> header = new HashMap<> ();
                        header.put ("Content-Type","application/x-www-form-urlencoded");
                        return header;

                    }
                };

                requestQueue.add (stringRequest);
            }

        });


    }

}
