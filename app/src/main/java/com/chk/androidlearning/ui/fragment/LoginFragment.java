package com.chk.androidlearning.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chk.androidlearning.R;
import com.chk.androidlearning.ui.viewModel.FragmentSharedViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

/**
 * Created by CHK on 20-12-11.
 */
public class LoginFragment extends Fragment {

    public static String LOGIN_SUCCESS = "LOGIN_SUCCESS";

    private FragmentSharedViewModel viewModel;
    private SavedStateHandle savedStateHandle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInit();
        viewInit(view);
    }

    void dataInit() {
        viewModel = new ViewModelProvider(requireActivity()).get(FragmentSharedViewModel.class);
    }


    void viewInit(View view) {
        savedStateHandle = Navigation.findNavController(view).getPreviousBackStackEntry()
                .getSavedStateHandle();
        savedStateHandle.set(LOGIN_SUCCESS,false);  //这里设置默认状态
        Button loginButton = view.findViewById(R.id.login);
        loginButton.setOnClickListener((v)->{
            if (viewModel.genUser()) {
//                Toast.makeText(getContext(), "登录成功！", Toast.LENGTH_SHORT).show();
                savedStateHandle.set(LOGIN_SUCCESS,true);
                NavHostFragment.findNavController(this).popBackStack();
            } else {
//                Toast.makeText(getContext(),"登录失败！请重试！",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
