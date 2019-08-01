package bd.shimul.tourmate;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToursFragment extends Fragment {
    private FloatingActionButton addTourFab;


    public ToursFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeActivity) getActivity()).setActionBarTitle("Tours");
        View view = inflater.inflate(R.layout.fragment_tours, container, false);
        init(view);
        addTourFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTourBottomSheet addTourBottomSheet = new AddTourBottomSheet();
                addTourBottomSheet.show(getFragmentManager(), "Add tour");

            }
        });
        return view;
    }

    private void init(View view) {
        addTourFab = view.findViewById(R.id.addTourFab);
    }

}
