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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class AdminSubjectList extends Fragment implements OnClickListener {
	View v;
	TextView branchname;
	Spinner year;
	ArrayList<String> yearArray;
	ArrayList<BranchYearInfo> yearID, subjectIDArray, subjectNameArray;
	String spYear, bname, bid, yname, yid, spSujectIDs, spSubjectNM, subid,
			chcksname = "";
	BranchYearInfo getbname;
	AdminSubjectListAdapter adapter;
	ListView list;
	Button add, delete;
	Button ok;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.admin_subject, container, false);
		yearArray = new ArrayList<String>();
		subjectIDArray = new ArrayList<BranchYearInfo>();

		AdminActivity main = (AdminActivity) getActivity();
		yearID = new ArrayList<BranchYearInfo>();
		getbname = main.getAdminBranchName();
		bname = getbname.getBranch_name();
		bid = getbname.getBranch_id();
		System.out.println("Bname=" + bname);
		branchname = (TextView) v.findViewById(R.id.adminsubject_bname);
		branchname.setText(bname);
		ok = (Button) v.findViewById(R.id.adminsubjet_okbtn);

		year = (Spinner) v.findViewById(R.id.adminsubjet_year);
		list = (ListView) v.findViewById(R.id.adminsubjet_sublist);
		setyearSpinner();
		add = (Button) v.findViewById(R.id.admin_addSubject);
		delete = (Button) v.findViewById(R.id.admin_deleteSubject);

		ok.setOnClickListener(this);
		add.setOnClickListener(this);
		delete.setOnClickListener(this);

		year.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				int id = year.getSelectedItemPosition();
				BranchYearInfo getid = new BranchYearInfo();
				getid = yearID.get(id);
				yid = getid.getYear_id();
				System.out.println("onItemSelected  YEAR ID=" + yid + "  name="
						+ getid.getYear_name());
				getSubjectIDs();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		return v;
	}

	protected void getSubjectIDs() {
		// TODO Auto-generated method stub
		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			public void run() {

				spSujectIDs = obj.getAdminSubjectID(yid, bid);
				System.out.println("spSujectIDs =" + spSujectIDs);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<BranchYearInfo> subjectInfo = parseJsonSubjectIDs(spSujectIDs);

						Iterator subjectitr = subjectInfo.iterator();
						while (subjectitr.hasNext()) {
							BranchYearInfo linfo = (BranchYearInfo) subjectitr
									.next();
							subjectIDArray.add(linfo);
							System.out.println("subjectIDArray=="
									+ subjectIDArray);

						}

					}

				});
			}
		}).start();

	}

	private void setyearSpinner() {
		// TODO Auto-generated method stub

		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			public void run() {
				spYear = obj.getAdminAllYear();
				System.out.println("spYear =" + spYear);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<BranchYearInfo> yearInfo = parseJsonYear(spYear);

						Iterator yearitr = yearInfo.iterator();
						while (yearitr.hasNext()) {
							BranchYearInfo linfo = (BranchYearInfo) yearitr
									.next();
							yearArray.add(linfo.getYear_name());
							yearID.add(linfo);
							System.out.println("yName " + linfo.getYear_id());

						}

						ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item, yearArray);
						yearAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						year.setAdapter(yearAdapter);

					}

				});
			}
		}).start();

	}

	public ArrayList<BranchYearInfo> parseJsonSubjectIDs(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setSubject_id(jsonObj.getString("Sub_id"));
				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public ArrayList<BranchYearInfo> parseJsonSubjectName(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setSubject_id(jsonObj.getString("Subject_id"));
				menu.setSubject_name(jsonObj.getString("Subject_Name"));
				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public ArrayList<BranchYearInfo> parseJsonYear(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setYear_id(jsonObj.getString("Year_id"));
				menu.setYear_name(jsonObj.getString("Year_Name"));
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
		case R.id.adminsubjet_okbtn:
			getSubjectList();

			break;

		case R.id.admin_addSubject:
			try {
				AdminAddSubjectDialog deletedialog = new AdminAddSubjectDialog(
						getActivity(), bid, yid);
				deletedialog.show();

			} finally {

				AdminActivity main = (AdminActivity) getActivity();
				main.adminSubject();
			}

			break;

		case R.id.admin_deleteSubject:
			try {
				AdminDeleteSubjectdialog deletedialog = new AdminDeleteSubjectdialog(
						getActivity());
				deletedialog.show();

			} finally {

				AdminActivity main = (AdminActivity) getActivity();
				main.adminSubject();
			}
			break;

		default:
			break;
		}

	}

	private void getSubjectList() {
		// TODO Auto-generated method stub
		subjectNameArray = new ArrayList<BranchYearInfo>();
		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			public void run() {
				System.out.println("bid" + bid + " yid=" + yid);
				spSubjectNM = obj.getAdminSubjectName(yid, bid);
				System.out.println("spSubjectNM =" + spSubjectNM);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<BranchYearInfo> SubjectNMInfo = parseJsonSubjectName(spSubjectNM);

						Iterator SubjectNMitr = SubjectNMInfo.iterator();
						while (SubjectNMitr.hasNext()) {
							BranchYearInfo linfo = (BranchYearInfo) SubjectNMitr
									.next();
							subjectNameArray.add(linfo);
							chcksname = linfo.getSubject_name();

						}
						if (chcksname.equals("")) {
							Toast.makeText(getActivity(),
									"Subject not Availabel..!!",
									Toast.LENGTH_SHORT).show();
						} else {
							adapter = new AdminSubjectListAdapter(
									getActivity(), R.layout.admin_subjectlist,
									subjectNameArray);
							list.setAdapter(adapter);
							chcksname = "";
						}

					}

				});
			}
		}).start();

	}
}
