package com.nielsenninjas.wafernav;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.nielsenninjas.wafernav.enums.Operation;

/**
 A simple {@link Fragment} subclass.
 Activities that contain this fragment must implement the
 {@link DeliveryCompleteFragment.OnFragmentInteractionListener} interface
 to handle interaction events.
 Use the {@link DeliveryCompleteFragment#newInstance} factory method to
 create an instance of this fragment.
 */
public class DeliveryCompleteFragment extends Fragment {

    private static final String ARG_PARAM0 = "param0";
    private Operation mOperation;
    private OnFragmentInteractionListener mListener;

    public DeliveryCompleteFragment() {
        // Required empty public constructor
    }

    public static DeliveryCompleteFragment newInstance(Operation operation) {
        DeliveryCompleteFragment fragment = new DeliveryCompleteFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM0, operation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_complete, container, false);

        // Set button handlers
        Button newDeliveryButton = (Button) view.findViewById(R.id.buttonNewDelivery);
        newDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mOperation = (Operation) getArguments().get(ARG_PARAM0);
                    mListener.newDeliveryButtonHandler();
                }
            }
        });

        Button exitAppButton = (Button) view.findViewById(R.id.buttonExitApp);
        exitAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.exitAppButtonHandler();
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
        void newDeliveryButtonHandler();
        void exitAppButtonHandler();
    }
}
