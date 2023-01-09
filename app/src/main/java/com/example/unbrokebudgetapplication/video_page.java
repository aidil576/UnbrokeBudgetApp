package com.example.unbrokebudgetapplication;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class video_page extends Fragment {

    public static video_page newInstance() {
        return new video_page();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //import video dalam page
        VideoView videoView = view.findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.vid_unbroke;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(getActivity());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        //navigate ke page point redemption
        ImageButton Btnback_vid = view.findViewById(R.id.BtnBack_vid);
        View.OnClickListener OCLBtnVid = new View.OnClickListener(){
            @Override
            public void onClick(View v){

                ((MainScreen) getActivity()).switchContent(point_redemption.newInstance());
            }
        };
        Btnback_vid.setOnClickListener(OCLBtnVid);

    }
}