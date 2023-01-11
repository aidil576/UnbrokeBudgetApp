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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;



public class FeedbackFragment extends Fragment {

    private Button BtnFeedback;
    private Context context;
    private RatingBar ratingBar;
    private TextView ratedPoint;
    private EditText editTextFeedback;
    FirebaseDatabase mFeedback;
    DatabaseReference mReference;
    FeedbackForm feedbackForm;
    private FirebaseApp myApp;
    private FirebaseOptions options;
    private ProgressDialog progressDialog;


    public FeedbackFragment() {
        // Required empty public constructor
    }
    public static FeedbackFragment newInstance(){
        return new FeedbackFragment();
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//
//        }
//    }

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

        // below line is used to get the
        // instance of our FIrebase database.
        mFeedback = FirebaseDatabase.getInstance();
        // below line is used to get reference for our database.
        mReference = mFeedback.getReference("FeedbackForm");

        progressDialog = new ProgressDialog(context);
        //FirebaseDatabase.getInstance().getReference("FeedbackForm");
        // initializing our object
        // class variable.
        feedbackForm = new FeedbackForm();




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

        // adding on click listener for our button.
        BtnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Version 1
//                String message = "";
//                if(!editTextFeedback.getText().toString().isEmpty())
//                    message = message+"Please enjoy using our app";
//                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
//
//                String feedbackUser = editTextFeedback.getText().toString();
//                String ratedUser = ratedPoint.getText().toString();
//
//                if(TextUtils.isEmpty(feedbackUser)){
//                    editTextFeedback.setError("Feedback is required");
//                }
//                if(TextUtils.isEmpty(ratedUser)){
//                    ratedPoint.setError("Rating is needed");
//                }
//                else
//                    progressDialog.setMessage("feedback in progress");
//                    //progressDialog.setCanceledOnTouchOutside(false);
//                    progressDialog.show();

                //Version 2

                // getting text from our edittext fields.
                String opinion = editTextFeedback.getText().toString();
                String rating = ratedPoint.getText().toString();

                // below line is for checking whether the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(opinion) && TextUtils.isEmpty(rating)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(getActivity(), "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(opinion, rating);
                }

            }
        });
    }

    private void addDatatoFirebase(String opinion, String rating){
        // below 3 lines of code is used to set
        // data in our object class.
        feedbackForm.setOpinion(opinion);
        feedbackForm.setPoint(rating);

        // we are use add value event listener method
        // which is called with database reference.
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                mReference.setValue(feedbackForm);

                // after adding this data we are showing toast message.
                Toast.makeText(getActivity(), "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getActivity(), "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });

    }


}