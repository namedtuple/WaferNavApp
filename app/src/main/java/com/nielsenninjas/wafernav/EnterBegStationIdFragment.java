package com.nielsenninjas.wafernav;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

/**
 A simple {@link Fragment} subclass.
 Activities that contain this fragment must implement the
 {@link EnterBegStationIdFragment.OnFragmentInteractionListener} interface
 to handle interaction events.
 Use the {@link EnterBegStationIdFragment#newInstance} factory method to
 create an instance of this fragment.
 */
public class EnterBegStationIdFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mHandlerId;
    private String mHandlerLocation;
    private OnFragmentInteractionListener mListener;
    private AutoCompleteTextView mAutoCompleteTextViewStationId;

    public EnterBegStationIdFragment() {
        // Required empty public constructor
    }

    public static EnterBegStationIdFragment newInstance(String param1, String param2) {
        EnterBegStationIdFragment fragment = new EnterBegStationIdFragment();
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
            mHandlerId = getArguments().getString(ARG_PARAM1);
            mHandlerLocation = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_beg_station_id, container, false);

        mAutoCompleteTextViewStationId = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextViewStationId);

        Button readBarcodeButton = (Button) view.findViewById(R.id.buttonReadBarcode);
        readBarcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.readStationBarcodeButtonHandler(mHandlerId, mHandlerLocation);
                }
            }
        });

        Button publishStationIdButton = (Button) view.findViewById(R.id.buttonPublishStationId);
        publishStationIdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null && mAutoCompleteTextViewStationId != null) {
                    String stationId = mAutoCompleteTextViewStationId.getText().toString();
                    if (stationId != null && !stationId.isEmpty()) {
                        mListener.publishStationIdButtonHandler(stationId);
                    }
                    // TODO - Create toast message if stationId is null or empty
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     This interface must be implemented by activities that contain this
     fragment to allow an interaction in this fragment to be communicated
     to the activity and potentially other fragments contained in that
     activity.
     See the Android Training lesson <a href=
     "http://developer.android.com/training/basics/fragments/communicating.html"
     >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void readStationBarcodeButtonHandler(String id, String loc);
        void publishStationIdButtonHandler(String stationId);
    }

    public void setStationIdText(String stationId) {
        mAutoCompleteTextViewStationId.setText(stationId);
    }
}