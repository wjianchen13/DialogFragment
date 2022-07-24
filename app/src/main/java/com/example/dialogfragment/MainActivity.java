package com.example.dialogfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dialogfragment.bottom.BottomActivity;
import com.example.dialogfragment.progress.ProgressActivity;
import com.example.dialogfragment.top.TopActivity;


public class MainActivity extends AppCompatActivity implements LoginDialogFragment.LoginInputListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void showConfimDialog(View view)
	{
		FireMissilesDialogFragment test1 = new FireMissilesDialogFragment();
		test1.show(getFragmentManager(), "");
	}

	public void showEditDialog(View view)
	{
		EditNameDialogFragment editNameDialog = new EditNameDialogFragment();
		editNameDialog.show(getFragmentManager(), "EditNameDialog");
	}

	public void showLoginDialog(View view)
	{
		LoginDialogFragment dialog = new LoginDialogFragment();
		dialog.show(getFragmentManager(), "loginDialog");
	}

	public void showDialogInDifferentScreen(View view)
	{
		FragmentManager fragmentManager = getFragmentManager();
		EditNameDialogFragment newFragment = new EditNameDialogFragment();

		boolean mIsLargeLayout = getResources().getBoolean(R.bool.large_layout) ;
		Log.e("TAG", mIsLargeLayout+"");
		if (mIsLargeLayout )
		{
			// The device is using a large layout, so show the fragment as a
			// dialog
			newFragment.show(fragmentManager, "dialog");
		} else
		{
			// The device is smaller, so show the fragment fullscreen
			FragmentTransaction transaction = fragmentManager
					.beginTransaction();
			// For a little polish, specify a transition animation
			transaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			// To make it fullscreen, use the 'content' root view as the
			// container
			// for the fragment, which is always the root view for the activity
			transaction.replace(R.id.id_ly, newFragment)
					.commit();
		}
	}

	public void showLoginDialogWithoutFragment(View view)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Get the layout inflater
		LayoutInflater inflater = this.getLayoutInflater();

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(inflater.inflate(R.layout.fragment_login_dialog, null))
				// Add action buttons
				.setPositiveButton("Sign in",
						new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int id)
							{
								// sign in the user ...
							}
						}).setNegativeButton("Cancel", null).show();
	}


	public void showJump(View view) {
		Intent it = new Intent();
		it.setClass(this, EditTextActivity.class);
		startActivity(it);
	} 


	public void onDialogFragment(View view) {
		Intent it = new Intent();
		it.setClass(this, DialogFragemtActivity.class);
		startActivity(it);
	}
	
	public void onBottomDialogFragment(View v) {
		Intent it = new Intent();
		it.setClass(this, BottomActivity.class);
		startActivity(it);
	}

	public void onProgressDialogFragment(View v) {
		Intent it = new Intent();
		it.setClass(this, ProgressActivity.class);
		startActivity(it);
	}

	public void onTopDialogFragment(View v) {
		Intent it = new Intent();
		it.setClass(this, TopActivity.class);
		startActivity(it);
	}
	
	@Override
	public void onLoginInputComplete(String username, String password)
	{
		Toast.makeText(this, "帐号：" + username + ",  密码 :" + password,
				Toast.LENGTH_SHORT).show();
	}

}
