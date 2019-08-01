package bd.shimul.tourmate;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment {
    TabLayout tabLayout;



    public NearbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeActivity) getActivity()).setActionBarTitle("Nearby");
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        init(view);
        final NearbyAdapter adapter = new NearbyAdapter(view.getContext(),getChildFragmentManager(), tabLayout.getTabCount());
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.tabLayoutViewPage);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }

    private void init(View view) {
        tabLayout = view.findViewById(R.id.nearByTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("ATM"));
        tabLayout.addTab(tabLayout.newTab().setText("HOSPITAL"));
        tabLayout.addTab(tabLayout.newTab().setText("RESTURANT"));
        tabLayout.addTab(tabLayout.newTab().setText("HOTEL"));
        tabLayout.addTab(tabLayout.newTab().setText("SHOPPING MALL"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);





    }

}
