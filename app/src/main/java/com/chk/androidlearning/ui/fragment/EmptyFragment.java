package com.chk.androidlearning.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chk.androidlearning.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

/**
 * Created by CHK on 20-12-10.
 */
public class EmptyFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }   

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty,container,false);
        view.findViewById(R.id.openFragmentA).setOnClickListener((buttonView)-> {
            Toast.makeText(getContext(), "you click this!", Toast.LENGTH_SHORT).show();
            NavDirections aAction = EmptyFragmentDirections.actionEmptyFragmentToAFragment();
            NavHostFragment.findNavController(this).navigate(aAction);
        });
        view.findViewById(R.id.openFragmentB).setOnClickListener((buttonView)-> {
            Toast.makeText(getContext(), "you click this!", Toast.LENGTH_SHORT).show();
            NavDirections aAction = EmptyFragmentDirections.actionEmptyFragmentToBFragment();
            NavHostFragment.findNavController(this).navigate(aAction);
        });
        return view;
    }
}
