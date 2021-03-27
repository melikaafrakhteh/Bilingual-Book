package com.afrakhteh.book.ui.about;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.afrakhteh.book.R;
import com.afrakhteh.book.util.Constants;

public class AboutActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressDialog dialog;

    private ImageView printIm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        findViews();
    }

    private void findViews() {
        webView = findViewById(R.id.web_about);
        setUpWebView();

        printIm = findViewById(R.id.toolbar_print_im);
        onCliCk();
    }

    private void onCliCk() {
        printIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupPrint(webView);
            }
        });
    }

    private void setupPrint(WebView webView) {
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);

        // choosing document
        PrintDocumentAdapter adapter = null;

        adapter = webView.createPrintDocumentAdapter(Constants.PRINT_DOCUMENT);
        String name = getString(R.string.app_name) + "print";

        printManager.print(name, adapter, new PrintAttributes.Builder().build());
    }

    private void setUpWebView() {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getResources().getString(R.string.dialog_message));

        webView.getSettings().setJavaScriptEnabled(true); // enable javascript
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
            }
        });

        webView.loadUrl(Constants.RESOURCE_URL);

    }
}
