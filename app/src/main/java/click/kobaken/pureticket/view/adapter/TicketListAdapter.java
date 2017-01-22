package click.kobaken.pureticket.view.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import click.kobaken.pureticket.R;
import click.kobaken.pureticket.model.Ticket;


public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.RightsViewHolder> {
    private List<Ticket> ticketList;
    private OnClickItemListener listener;

    public interface OnClickItemListener {
        void onClickItem(String contentName);
    }

    public TicketListAdapter(List<Ticket> rightsList, OnClickItemListener listener) {
        this.ticketList = rightsList;
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public TicketListAdapter.RightsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_right, parent, false);
        return new TicketListAdapter.RightsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TicketListAdapter.RightsViewHolder holder, int position) {
        holder.rightNameTextView.setText(ticketList.get(position).name);
        holder.rightAmountTextView.setText(ticketList.get(position).amount);
        holder.rightPriceTextView.setText(ticketList.get(position).price);
        holder.rightImageTextView.setImageResource(ticketList.get(position).imageId);
        holder.cardView.setOnClickListener(v -> listener.onClickItem(holder.rightNameTextView.getText().toString()));
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    class RightsViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView rightNameTextView;
        TextView rightPriceTextView;
        TextView rightAmountTextView;
        ImageView rightImageTextView;

        RightsViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_rights);
            rightNameTextView = (TextView) itemView.findViewById(R.id.textViewRightName);
            rightPriceTextView = (TextView) itemView.findViewById(R.id.textViewThingPrices);
            rightAmountTextView = (TextView) itemView.findViewById(R.id.right_amount);
            rightImageTextView = (ImageView) itemView.findViewById(R.id.right_image);
        }
    }
}
