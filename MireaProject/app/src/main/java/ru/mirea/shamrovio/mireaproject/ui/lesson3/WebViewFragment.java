package ru.mirea.shamrovio.mireaproject.ui.lesson3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import ru.mirea.shamrovio.mireaproject.R;
import ru.mirea.shamrovio.mireaproject.databinding.FragmentWebViewBinding;

public class WebViewFragment extends Fragment {

	private FragmentWebViewBinding _binding;
	private WebView _webView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		_binding = FragmentWebViewBinding.inflate(inflater, container, false);
		View root = _binding.getRoot();

		_webView = root.findViewById(R.id.webView);
		_webView.loadUrl("https://github.com/Breez97/MobileAppsPractices");

		return root;
	}
}