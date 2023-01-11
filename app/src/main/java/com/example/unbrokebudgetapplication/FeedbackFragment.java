package com.example.unbrokebudgetapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirestoreRegistrar;



public class FeedbackFragment extends Fragment {

    private Button BtnFeedback;
    private Context context;
    private RatingBar ratingBar;
    private TextView ratedPoint;
    private EditText editTextFeedback;
    private FirebaseDatabase mFeedback;
    private DatabaseReference mReference;
    private FirebaseApp myApp;
    private FirebaseOptions options;
    private ProgressDialog progressDialog;

    public FeedbackFragment() {
        // Required empty public constructor
    }
    public static FeedbackFragment newInstance(){
        return new FeedbackFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_feedback, container, false);

       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        ratingBar = view.findViewById(R.id.ratingBar);
        ratedPoint = view.findViewById(R.id.ratedPoint);

        editTextFeedback = view.findViewById(R.id.editTextFeedback);

        BtnFeedback = view.findViewById(R.id.BtnFeedback);

//        options = new FirebaseOptions.Builder().setDatabaseUrl("https://unbroke-budget-application-default-rtdb.asia-southeast1.firebasedatabase.app/").build();
//        myApp = FirebaseApp.initializeApp(context.getApplicationContext(), options);

        //mFeedback = FirebaseDatabase.getInstance();
        //mReference = database.getReference("message");

        progressDialog = new ProgressDialog(context);
        //FirebaseDatabase.getInstance().getReference("FeedbackForm");


//        DAOFeedbackForm dao = new DAOFeedbackForm( "https://unbroke-budget-application-default-rtdb.asia-southeast1.firebasedatabase.app/");


        //to set rating
//        float rating = 3.5f;
//        ratingBar.setRating(rating);
//        to enable change
//        float rating2 = ratingBar.getRating();
//        ratingBar.setIsIndicator(false);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratedPoint.setText("You have rated " + rating);
                //change float to string
                String s =Float.toString(rating);
            }
        });

//        DAOFeedbackForm dao = new DAOFeedbackForm();
//        BtnFeedback.setOnClickListener(v->{
//            FeedbackForm emp = new FeedbackForm(editTextFeedback.getText().toString(), ratedPoint.getText().toString());
//            dao.add(emp).addOnSuccessListener(suc->{
//               Toast.makeText(getContext(),"Record Is Inserted",Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er->{
//                Toast.makeText(getContext(),""+er.getMessage(),Toast.LENGTH_SHORT).show();
//            });
//        });

        BtnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";
                if(!editTextFeedback.getText().toString().isEmpty())
                    message = message+"Please enjoy using our app";
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

                String feedbackUser = editTextFeedback.getText().toString();
                String ratedUser = ratedPoint.getText().toString();

                if(TextUtils.isEmpty(feedbackUser)){
                    editTextFeedback.setError("Feedback is required");
                }
                if(TextUtils.isEmpty(ratedUser)){
                    ratedPoint.setError("Rating is needed");
                }
                else
                    progressDialog.setMessage("feedback in progress");
                    //progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
            }
        });
    }


}