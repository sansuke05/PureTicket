package click.kobaken.pureticket.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import click.kobaken.pureticket.R;
import click.kobaken.pureticket.databinding.FragmentTransactionHistoryBinding;
import click.kobaken.pureticket.view.Navigator;
import click.kobaken.pureticket.view.adapter.TransactionListAdapter;
import io.soramitsu.irohaandroid.model.Transaction;

public class TransactionHistoryFragment extends Fragment {
    public final static String TAG = TransactionHistoryFragment.class.getSimpleName();

    Navigator navigator;
    FragmentTransactionHistoryBinding binding;

    public static TransactionHistoryFragment newInstance() {
        TransactionHistoryFragment fragment = new TransactionHistoryFragment();
        return fragment;
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
        return inflater.inflate(R.layout.fragment_transaction_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);

        List<Transaction> history = new ArrayList<Transaction>() {{
            Transaction transaction = new Transaction();
            transaction.assetName = "円";
            transaction.params = new Transaction.OperationParameter();
            transaction.params.value = "10000";
            transaction.params.receiver = "VI○A";
            transaction.params.timestamp = 1456940524;
            add(transaction);

            Transaction transaction1 = new Transaction();
            transaction1.assetName = "円";
            transaction1.params = new Transaction.OperationParameter();
            transaction1.params.value = "7000";
            transaction1.params.receiver = "M○st○rCa○d";
            transaction1.params.timestamp = 1465148524;
            add(transaction1);

            Transaction transaction2 = new Transaction();
            transaction2.assetName = "円";
            transaction2.params = new Transaction.OperationParameter();
            transaction2.params.value = "6000";
            transaction2.params.receiver = "J○B";
            transaction2.params.timestamp = 1478799724;
            add(transaction2);

            Transaction transaction3 = new Transaction();
            transaction3.assetName = "円";
            transaction3.params = new Transaction.OperationParameter();
            transaction3.params.value = "8000";
            transaction3.params.receiver = "アメリ○ン・エキ○プレス";
            transaction3.params.timestamp = 1479577324;
            add(transaction3);
        }};

        binding.listView.setAdapter(new TransactionListAdapter(getContext(), history, ""));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }
}
