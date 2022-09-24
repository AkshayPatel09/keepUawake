package com.example.myapplication;

import static android.content.ContentValues.TAG;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import android.hardware.Camera;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cameraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cameraFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Camera mCamera;
    private CameraPreview mPreview;
    private Camera.PictureCallback mPicture;
    private MaterialButton stopTrackingBtn;

    public cameraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cameraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static cameraFragment newInstance(String param1, String param2) {
        cameraFragment fragment = new cameraFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_camera, container, false);
        stopTrackingBtn = v.findViewById(R.id.stopTracking);

        stopTrackingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCamera!=null)
                mCamera.release();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                dashboardFragment fragment = new dashboardFragment();
                transaction.replace(R.id.flMain, fragment);
                transaction.commit();
            }
        });
//       mPicture = new Camera.PictureCallback() {
//
//            @Override
//            public void onPictureTaken(byte[] data, Camera camera) {
//
//                File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
//                if (pictureFile == null){
//                    Log.d(TAG, "Error creating media file, check storage permissions");
//                    return;
//                }
//
//                try {
//                    FileOutputStream fos = new FileOutputStream(pictureFile);
//                    fos.write(data);
//                    fos.close();
//                } catch (FileNotFoundException e) {
//                    Log.d(TAG, "File not found: " + e.getMessage());
//                } catch (IOException e) {
//                    Log.d(TAG, "Error accessing file: " + e.getMessage());
//                }
//            }
//        };
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getActivity(), "Camera permission not granted!!", Toast.LENGTH_SHORT).show();
        }else {
            if (checkCameraHardware(getActivity())) {
//            Toast.makeText(getActivity(), "Camera Found!!", Toast.LENGTH_SHORT).show();
                mCamera = getCameraInstance();

                // Create our Preview view and set it as the content of our activity.
                mPreview = new CameraPreview(getActivity(), mCamera);
                FrameLayout preview = (FrameLayout) v.findViewById(R.id.camera_preview);
                preview.addView(mPreview);
            } else {
                Toast.makeText(getActivity(), "Device might not have camera!!", Toast.LENGTH_SHORT).show();
            }
        }


        return v;
    }
    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }


}