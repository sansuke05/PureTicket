package click.kobaken.pureticket.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import click.kobaken.pureticket.Katyusha;
import click.kobaken.pureticket.R;
import click.kobaken.pureticket.databinding.FragmentTransactionHistoryBinding;
import click.kobaken.pureticket.view.Navigator;
import click.kobaken.pureticket.view.adapter.TransactionListAdapter;
import io.soramitsu.irohaandroid.Iroha;
import io.soramitsu.irohaandroid.callback.Callback;
import io.soramitsu.irohaandroid.model.Account;
import io.soramitsu.irohaandroid.model.Transaction;


public class TicketHistoryFragment extends Fragment {
    public static final String TAG = TicketHistoryFragment.class.getSimpleName();

    public static final String IROHA_TICKET_TRANSACTION_HISTORY = "ticket_transaction_history";

    Navigator navigator;
    FragmentTransactionHistoryBinding binding;

    public static TicketHistoryFragment newInstance() {
        TicketHistoryFragment fragment = new TicketHistoryFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transaction_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);

        try {
            Iroha iroha = Iroha.getInstance();
            iroha.runAsyncTask(
                    IROHA_TICKET_TRANSACTION_HISTORY,
                    iroha.findTransactionHistoryFunction(Account.getUuid(getContext()), 30, 0),
                    new Callback<List<Transaction>>() {
                        @Override
                        public void onSuccessful(List<Transaction> result) {
                            Katyusha app = ((Katyusha) getActivity().getApplication());
                            String publicKey = app.getPublicKey();
                            binding.listView.setAdapter(new TransactionListAdapter(getContext(), result, publicKey));
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            Log.e(TAG, "onFailure: ", throwable);
                        }
                    }
            );
        } catch (Exception e) {
            Log.e(TAG, "onViewCreated: ", e);
            e.printStackTrace();
        }
//        List<Transaction> history = new ArrayList<Transaction>() {{
//            Transaction transaction = new Transaction();
//            transaction.assetName = "Ticket";
//            transaction.params = new Transaction.OperationParameter();
//            transaction.params.value = "三森すずこ 2nd LIVE 2015『Fun!Fun!Fantasic Funfair!』初日";
//            transaction.params.sender = "三森すずこ";
//            transaction.params.timestamp = 1465148524;
//            add(transaction);
//
//            Transaction transaction1 = new Transaction();
//            transaction1.assetName = "Ticket";
//            transaction1.params = new Transaction.OperationParameter();
//            transaction1.params.value = "Lantis presents 「深窓音楽演奏会其の参」";
//            transaction1.params.sender = "ChouCho";
//            transaction1.params.timestamp = 1465148524;
//            add(transaction1);
//
//            Transaction transaction3 = new Transaction();
//            transaction3.assetName = "Ticket";
//            transaction3.params = new Transaction.OperationParameter();
//            transaction3.params.value = "Tokyo Bunka Kaikan(with Akiko Yano)";
//            transaction3.params.sender = "上原ひろみ";
//            transaction3.params.timestamp = 1465148524;
//            add(transaction3);
//        }};
//
//        binding.listView.setAdapter(new TransactionListAdapter(getContext(), history, ""));
    }
}
