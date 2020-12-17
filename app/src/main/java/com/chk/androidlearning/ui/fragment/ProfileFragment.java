package com.chk.androidlearning.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chk.androidlearning.R;
import com.chk.androidlearning.bean.User;
import com.chk.androidlearning.ui.viewModel.FragmentSharedViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

/**
 * Created by CHK on 20-12-11.
 */
public class ProfileFragment extends Fragment {

    private final static String TAG = ProfileFragment.class.getSimpleName();

    FragmentSharedViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate!");
        NavController navController = NavHostFragment.findNavController(this);
        NavBackStackEntry bakEntry = navController.getCurrentBackStackEntry();
        SavedStateHandle savedStateHandle = bakEntry.getSavedStateHandle();
        savedStateHandle.getLiveData(LoginFragment.LOGIN_SUCCESS)
                .observe(bakEntry, success -> {
                    Boolean booleanSuccess = (Boolean) success;
                    if (!booleanSuccess) {
                        Toast.makeText(getContext(), "登录失败！", Toast.LENGTH_SHORT).show();
                        int startDestination = navController.getGraph().getStartDestination();
                        NavOptions navOptions = new NavOptions.Builder()
                                .setPopUpTo(startDestination, true)
                                .build();
                        navController.navigate(startDestination, null, navOptions);
                    } else {
                        Toast.makeText(getContext(), "登录成功！", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"onCreateView!");
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.openLoginFragment).setOnClickListener((buttonView)-> {
            Toast.makeText(getContext(), "you click this!", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.loginFragment);
        });
        Log.i(TAG,"onViewCreated!");
        dataInit();
        actionInit(view);
    }

    void dataInit() {
        viewModel = new ViewModelProvider(requireActivity()).get(FragmentSharedViewModel.class);
    }

    void actionInit(View view) {
//        if (viewModel.getUser().getValue() == null) {
////            NavDirections navDir = ProfileFragmentDirections.actionProfileFragmentToLoginFragment();
//            Navigation.findNavController(view).navigate(R.id.loginFragment);
//        }
        Log.i(TAG,"actionInit");
        viewModel.getUser().observe(getViewLifecycleOwner(), (Observer<User>)user -> {
            Log.i(TAG,"observer user");
            if (user == null) {
                Toast.makeText(getContext(), "user  is null!", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.loginFragment);
            } else {
                Toast.makeText(getContext(), "user  is no tnull!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
