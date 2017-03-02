package com.example.studentattendencesystem.admin;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.AdminInfoNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class AdminBranchFragment extends Fragment implements OnClickListener {
	View v;
	Button add, delete,refrsh;
	ListView list;
	String allBranch;
	AdminBranchListAdapter adapter;
	ArrayList<BranchYearInfo> branchArray;
	int i = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.admin_branch, container, false);
		branchArray = new ArrayList<BranchYearInfo>();
		add = (Button) v.findViewById(R.id.admin_addbranch);
		delete = (Button) v.findViewById(R.id.admin_deletebranch);
		refrsh=(Button)v.findViewById(R.id.admin_Refreshbranch);
		list = (ListView) v.findViewById(R.id.admin_blist);
		listBranch();
		add.setOnClickListener(this);
		delete.setOnClickListener(this);
		refrsh.setOnClickListener(this);

		return v;
	}

	private void listBranch() {
		// TODO Auto-generated method stub

		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			public void run() {

				allBranch = obj.getAdminAllBranch();
				System.out.println("spBranch =" + allBranch);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						ArrayList<BranchYearInfo> branchInfo = parseJsonBranch(allBranch);
						Iterator branchitr = branchInfo.iterator();
						while (branchitr.hasNext()) {
							BranchYearInfo ainfo = (BranchYearInfo) branchitr
									.next();
							branchArray.add(ainfo);
							System.out.println("bName " + ainfo.getBranch_id());

						}
						adapter = new AdminBranchListAdapter(getActivity(),
								R.layout.admin_branchlist, branchArray);
						list.setAdapter(adapter);

					}

				});
			}
		}).start();
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				BranchYearInfo brnchid=new BranchYearInfo();
				brnchid=branchArray.get(arg2);
			AdminActivity main=(AdminActivity)getActivity();
			main.setAdminBranchName(brnchid);
			main.adminSubject();
			}
		});

	}

	public ArrayList<BranchYearInfo> parseJsonBranch(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setBranch_id(jsonObj.getString("Branch_id"));
				menu.setBranch_name(jsonObj.getString("Branch_Name"));
				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.admin_addbranch:
			try {
				AdminAddBranchDialog adddialog = new AdminAddBranchDialog(
						getActivity());
				adddialog.show();

			} finally {

				AdminActivity main = (AdminActivity) getActivity();
				main.adminBranch();
			}
			break;
		case R.id.admin_deletebranch:
				try {
					AdminDeleteBranchdialog deletedialog = new AdminDeleteBranchdialog(
							getActivity());
					deletedialog.show();

				} finally {

					AdminActivity main = (AdminActivity) getActivity();
					main.adminBranch();
				}
				break;
				
		case R.id.admin_Refreshbranch:
			AdminActivity main = (AdminActivity) getActivity();
			main.adminBranch();
			break;

		default:
			break;
		}
	}
}