package click.kobaken.pureticket.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.GsonBuilder;
import com.google.zxing.WriterException;

import java.io.UnsupportedEncodingException;

import click.kobaken.pureticket.Katyusha;
import click.kobaken.pureticket.R;
import click.kobaken.pureticket.databinding.FragmentRightBinding;
import click.kobaken.pureticket.domain.entity.TransferQRParameter;
import click.kobaken.pureticket.util.QRCodeGenerator;
import click.kobaken.pureticket.view.Navigator;

public class RightFragment extends Fragment {
    public static final String TAG = RightFragment.class.getSimpleName();

    public static final String ARG_TARGET_NAME = "target_name";

    Navigator navigator;
    FragmentRightBinding binding;

    public static RightFragment newInstance(@NonNull String targetName) {
        RightFragment fragment = new RightFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TARGET_NAME, targetName);
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
        return inflater.inflate(R.layout.fragment_right, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);

        final String target = getArguments().getString(ARG_TARGET_NAME);

        try {
            Katyusha katyusha = (Katyusha) getActivity().getApplication();
            TransferQRParameter qrParams = new TransferQRParameter();
            qrParams.type = "trans";
            qrParams.account = katyusha.getPublicKey();
            final String alias = katyusha.getUserInfo().alias;
            qrParams.alias = new String(alias.getBytes("UTF-8"), "UTF-8");
            qrParams.value = target;
            String qrParamsText = new GsonBuilder()
                    .disableHtmlEscaping()
                    .create()
                    .toJson(qrParams, TransferQRParameter.class);
            Log.d(TAG, qrParamsText);
            binding.imageViewReceiptQrCode.setImageBitmap(QRCodeGenerator.generateQR(qrParamsText, 500));
        } catch (WriterException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
