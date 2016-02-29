package com.rockets12.lunchcast.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rockets12.lunchcast.CallbackInterface;
import com.rockets12.lunchcast.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    EditText mEditEmail, mEditPassword, mEditName;
    Button mButtonProceed;
    TextView mTextOption;

    private boolean mIsRegistrationActive = false;

    private CallbackInterface mCallback;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (CallbackInterface) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mEditEmail = (EditText) view.findViewById(R.id.edit_login_email);
        mEditPassword = (EditText) view.findViewById(R.id.edit_login_password);
        mEditName = (EditText) view.findViewById(R.id.edit_login_full_name);
        mButtonProceed = (Button) view.findViewById(R.id.button_login_proceed);
        mTextOption = (TextView) view.findViewById(R.id.text_login_option);
        mTextOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsRegistrationActive) {
                    mTextOption.setText(getString(R.string.login_register_new_user));
                    mEditName.setVisibility(View.GONE);
                    mIsRegistrationActive = false;
                } else {
                    mTextOption.setText(getString(R.string.login_existing_user));
                    mEditName.setVisibility(View.VISIBLE);
                    mIsRegistrationActive = true;
                }
            }
        });
        mTextOption.setText(getString(R.string.login_register_new_user));

        mButtonProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService
                        (Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mButtonProceed.getWindowToken(), 0);
                if (mIsRegistrationActive) {
                    mCallback.registerUser(mEditEmail.getText().toString(), mEditPassword.getText
                            ().toString(), mEditName.getText().toString());
                } else {
                    mCallback.loginUser(mEditEmail.getText().toString(), mEditPassword.getText()
                            .toString());
                }
            }
        });

        return view;
    }

}
