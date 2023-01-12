package com.example.unbrokebudgetapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_account_setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_account_setting extends Fragment {

   private Context context;
   private FirebaseUser user;
   private DatabaseReference reference;
   private String userID;

   private TextView TVUserName;

    public fragment_account_setting() {
        // Required empty public constructor
    }
    public static fragment_account_setting newInstance(){
        return new fragment_account_setting();
    }


    // TODO: Rename and change types and number of parameters
    public static fragment_account_setting newInstance(String param1, String param2) {
        fragment_account_setting fragment = new fragment_account_setting();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        ImageButton BtnNext = view.findViewById(R.id.BtnNextFeedback);
        View.OnClickListener BtnNextFeedback = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainScreen)getActivity()).switchContent(FeedbackFragment.newInstance());
            }
        };
        BtnNext.setOnClickListener(BtnNextFeedback);

        ImageButton BtnSignOut = view.findViewById(R.id.BtnSignOut);

        BtnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        ImageButton BtnTermCond = view.findViewById(R.id.BtnTermCond);

        BtnTermCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TncActivity.class);
                startActivity(intent);
            }
        });

        ImageButton BtnPrivacyPolicy = view.findViewById(R.id.BtnPrivacyPolicy);
        BtnPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PrivacyPolicyActivity.class);
                startActivity(intent);
            }
        });

        ImageButton BtnAboutUs = view.findViewById(R.id.BtnAboutUs);
        BtnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityAboutUs.class);
                startActivity(intent);
            }
        });

        ImageView IVProfilePic = view.findViewById(R.id.IVProfilePic);
        IVProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView TVUsername = view.findViewById(R.id.TVUserName);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String userName = userProfile.username;

                    TVUsername.setText(userName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri uri = data.getData();
//            // Do something with the URI, such as setting it as the source of the ImageView
//            profilePicture.setImageURI(uri);
//        }
//    }

}