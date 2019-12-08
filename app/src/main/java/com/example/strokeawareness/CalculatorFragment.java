package com.example.strokeawareness;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.strokeawareness.QuestionBank1;
import com.example.strokeawareness.R;

public class CalculatorFragment extends Fragment {

    Button startButton1;

    public CalculatorFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator,container, false);

        startButton1 =view.findViewById(R.id.playBT);

        startButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), QuestionBank1.class);
                startActivity(i);
            }
        });

        return view;
    }


}
