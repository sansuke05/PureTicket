package click.kobaken.pureticket.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import click.kobaken.pureticket.R;
import click.kobaken.pureticket.model.Badge;
import click.kobaken.pureticket.view.adapter.BadgeListAdapter;

public class BadgeFragment extends Fragment {
    public static final String TAG = BadgeFragment.class.getSimpleName();

    public static BadgeFragment newInstance() {
        return new BadgeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_badge, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_badge);

        BadgeListAdapter badgeListAdapter = new BadgeListAdapter(Badge.createMocks());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        rv.setLayoutManager(mLayoutManager);
        rv.setAdapter(badgeListAdapter);
        return rootView;
    }

}
