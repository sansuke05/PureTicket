package click.kobaken.pureticket.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import click.kobaken.pureticket.R;
import click.kobaken.pureticket.model.Ticket;
import click.kobaken.pureticket.view.Navigator;
import click.kobaken.pureticket.view.adapter.TicketListAdapter;


public class LiveListFragment extends Fragment {
    public static final String TAG = LiveListFragment.class.getSimpleName();

    Navigator navigator;

    public static LiveListFragment newInstance() {
        return new LiveListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Navigator) {
            navigator = (Navigator) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Navigator");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rights, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_rights);

        TicketListAdapter ticketListAdapter = new TicketListAdapter(Ticket.createMocks(), navigator::gotoRight);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        rv.setLayoutManager(mLayoutManager);
        rv.setAdapter(ticketListAdapter);
        return rootView;
    }
}
