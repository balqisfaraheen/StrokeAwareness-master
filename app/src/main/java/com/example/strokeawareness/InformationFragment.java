package com.example.strokeawareness;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.strokeawareness.FAQ;
import com.example.strokeawareness.FAST;
import com.example.strokeawareness.R;
import com.example.strokeawareness.SYMPTOM;
import com.example.strokeawareness.TREATMENT;
import com.example.strokeawareness.TYPEOFSTROKE;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InformationFragment extends Fragment {

    TextView textViewtype, textViewtreatment, textViewsymptom, textViewfAQ, textViewfAST, textViewquiz;
    ImageView typeIV, treatmentIV, symptomIV, faqIV, fastIV, quizIV;

    public InformationFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_information, container, false);

        TextView textViewtype = (TextView ) view.findViewById(R.id.textViewtype);
        TextView  textViewtreatment = (TextView ) view.findViewById(R.id.textViewTreatment);
        TextView  textViewsymptom = (TextView ) view.findViewById(R.id.textViewSymptom);
        TextView  textViewfAQ = (TextView ) view.findViewById(R.id.textViewFAQ);
        TextView  textViewfAST = (TextView ) view.findViewById(R.id.textViewFAST);
        TextView  textViewquiz = (TextView ) view.findViewById(R.id.quizTV);
        //TextView  textViewcheck = (TextView ) view.findViewById(R.id.textViewCHECK);
        ImageView typeIV = (ImageView) view.findViewById(R.id.typeIV);
        ImageView treatmentIV = (ImageView) view.findViewById(R.id.treatmentIV);
        ImageView symptomIV = (ImageView) view.findViewById(R.id.symptomIV);
        ImageView faqIV = (ImageView) view.findViewById(R.id.faqIV);
        ImageView fastIV = (ImageView) view.findViewById(R.id.fastIV);
        ImageView quizIV = (ImageView) view.findViewById(R.id.quizIV);
        //ImageView checkIV = (ImageView) view.findViewById(R.id.checkIV);


        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating_action_button);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), startQuiz.class);
                startActivity(i);
            }
        });


       // TextView  Video = (TextView ) view.findViewById(R.id.VideoTV);

        typeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TYPEOFSTROKE.class);
                startActivity(i);
            }
        });

        treatmentIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TREATMENT.class);
                startActivity(i);
            }
        });

        symptomIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SYMPTOM.class);
                startActivity(i);
            }
        });


        faqIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FAQ.class);
                startActivity(i);
            }
        });

        fastIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FAST.class);
                startActivity(i);
            }
        });

        quizIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), checkreading.class);
                startActivity(i);
            }
        });

        /* checkIV.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
              Intent i = new Intent(getActivity(), check.class);
                startActivity(i);
           }
        });
         */





        textViewtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TYPEOFSTROKE.class);
                startActivity(i);
            }
        });

        textViewtreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TREATMENT.class);
                startActivity(i);
            }
        });

        textViewsymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SYMPTOM.class);
                startActivity(i);
            }
        });

        textViewfAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FAQ.class);
                startActivity(i);
            }
        });

        textViewfAST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FAST.class);
                startActivity(i);
            }
        });

        textViewquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), checkreading.class);
                startActivity(i);
            }
        });



       /*  textViewcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), check.class);
                startActivity(i);
            }
        });
        */



        return view;


    }

}




