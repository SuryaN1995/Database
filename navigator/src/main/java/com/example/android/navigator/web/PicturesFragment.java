package com.example.android.navigator.web;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.android.navigator.R;
import com.example.android.navigator.main.MainActivity;


public class PicturesFragment extends Fragment {

    public WebView mWebView;

    Toolbar toolbar;

    private OnFragmentInteractionListener mListener;

    public PicturesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pictures, container, false);
        setupToolbar();
        mWebView = v.findViewById(R.id.webview);
        mWebView.loadUrl("https://www.google.co.in/search?client=ubuntu&channel=fs&dcr=0&biw=1301&bih=668&tbm=isch&sa=1&ei=6VRQWtKlE8PpvASh07HgCg&q=best+pictures&oq=best&gs_l=psy-ab.3.0.0i67k1l2j0j0i67k1l3j0l4.3739.12139.0.13638.9.7.1.1.1.0.141.633.0j5.7.0....0...1c.1.64.psy-ab..0.7.668.0...96.DxX95bQsWUA");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void setupToolbar() {
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getmMainContext().onBackPressed();
            }
        });
        toolbar.setTitle("Pictures");
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
