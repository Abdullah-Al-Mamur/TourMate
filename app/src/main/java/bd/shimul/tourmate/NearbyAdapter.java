package bd.shimul.tourmate;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NearbyAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public NearbyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AtmFragment atmFragment = new AtmFragment();
                return  atmFragment;

            case 1:
                HospitalFragment hospitalFragment = new HospitalFragment();
                return hospitalFragment;

            case 2:
                ResturantFragment resturantFragment = new ResturantFragment();
                return resturantFragment;
            case 3:
                HotelFragment hotelFragment = new HotelFragment();
                return hotelFragment;
            case 4:
                ShoppingMallFragment shoppingMallFragment = new ShoppingMallFragment();
                return shoppingMallFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {

        return totalTabs;
    }
}
