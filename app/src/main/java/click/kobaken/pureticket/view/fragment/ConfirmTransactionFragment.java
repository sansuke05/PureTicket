package click.kobaken.pureticket.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import click.kobaken.pureticket.Katyusha;
import click.kobaken.pureticket.R;
import click.kobaken.pureticket.databinding.FragmentConfirmTransactionBinding;
import click.kobaken.pureticket.model.UserInfo;
import click.kobaken.pureticket.view.Navigator;
import click.kobaken.pureticket.view.dialogs.MyProgressDialog;
import click.kobaken.pureticket.view.dialogs.SuccessDialog;
import io.soramitsu.irohaandroid.Iroha;
import io.soramitsu.irohaandroid.callback.Callback;
import io.soramitsu.irohaandroid.model.Account;

public class ConfirmTransactionFragment extends Fragment {
    public static final String TAG = ConfirmTransactionFragment.class.getSimpleName();

    public static final String ARG_TARGET_NAME = "target_name";
    public static final String ARG_RECEIVER = "receiver";

    public static final String IROHA_OPERATION_ASSET = "operation_asset";

    Navigator navigator;
    FragmentConfirmTransactionBinding binding;

    public static ConfirmTransactionFragment newInstance(@NonNull String target, @NonNull String receiver) {
        ConfirmTransactionFragment fragment = new ConfirmTransactionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TARGET_NAME, target);
        bundle.putString(ARG_RECEIVER, receiver);
        fragment.setArguments(bundle);
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
        return inflater.inflate(R.layout.fragment_confirm_transaction, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);

        UserInfo userInfo = ((Katyusha) getActivity().getApplication()).getUserInfo();

        final String target = getArguments().getString(ARG_TARGET_NAME);
        final String receiver = getArguments().getString(ARG_RECEIVER);

        setConfirmInfo(userInfo, target, receiver);

        binding.cancelButton.setOnClickListener(v -> navigator.gotoTransaction());
        binding.sendButton.setOnClickListener(v -> {
            final MyProgressDialog progressDialog = new MyProgressDialog();
            progressDialog.show(getActivity(), getString(R.string.connection_progress_title), getString(R.string.send_payment_message));

            try {
                final long timestamp = System.currentTimeMillis() / 1000;

                // (iroha) Operation asset
                Iroha iroha = Iroha.getInstance();
                iroha.runAsyncTask(IROHA_OPERATION_ASSET, iroha.operateAssetFunction(
                        "assetUuid",
                        "transfer",
                        "7000",
                        Account.getUuid(getContext()),
                        "三森すずこ",
                        "signature",
                        timestamp
                ), new Callback<Boolean>() {
                    @Override
                    public void onSuccessful(Boolean result) {
                        progressDialog.hide();

                        final SuccessDialog successDialog = new SuccessDialog(getLayoutInflater(savedInstanceState));
                        successDialog.show(getActivity(), "Send successful", vv -> {
                            successDialog.hide();
                            navigator.gotoTransaction();
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        progressDialog.hide();

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                progressDialog.hide();
            }
        });
    }

    private void setConfirmInfo(UserInfo userInfo, String target, String receiver) {
        String balanceText = "$" + userInfo.assets.get(0).value + " to " + "$";
        int amount = Integer.parseInt(userInfo.assets.get(0).value);
        if (target.equals("Vodka")) {
            balanceText += (amount - 3) + "";
        } else if (target.equals("Bread")) {
            balanceText += (amount - 2) + "";
        } else {
            balanceText += (amount - Integer.parseInt(target)) + "";
        }
        binding.balance.setText(balanceText);

        String message = "Send ";
        if (target.equals("Vodka") || target.equals("Bread")) {
            message += target;
        } else {
            message += "$" + target + ".00";
        }
        message += " to " + receiver;
        binding.message.setText(message);

        if (userInfo.alias.equals("tony")) {
            binding.senderIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.tony));
        } else {
            binding.senderIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.takemiya));
        }

        if (receiver.equals("tony")) {
            binding.receiverIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.tony));
        } else {
            binding.receiverIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.takemiya));
        }
    }
}
