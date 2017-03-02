package com.example.studentattendencesystem.admin;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.AdminInfoNetwork;
import com.example.studentattendencesystem.javaclasses.AdminInfo;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AdminlectEntries extends Fragment implements OnClickListener {

	View v;
	Context context;
	Spinner branchname;
	Spinner year;
	Spinner subjname;
	AdminInfoNetwork getdb;
	String spYear, spBranch, bid, yid, lid, sid = "", Subj_INFO, lectSubject;
	Button ok, submit;

	ArrayList<BranchYearInfo> branchArray;
	ArrayList<String> branchArraylist;
	ArrayList<BranchYearInfo> yearArray;
	ArrayList<String> yearArraylist;
	ArrayList<AdminInfo> subjectArray;
	ArrayList<String> subjectArraylist;
	ArrayList<AdminInfo> branchidArray;
	ArrayList<String> branchidArraylist;
	ArrayList<AdminInfo> yearidArray;
	ArrayList<String> yearidArraylist;
	ArrayList<String> subarraylist;
	ArrayList<AdminInfo> lectsubjectArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		v = inflater.inflate(R.layout.admin_lectaddsubject, container, false);
		branchArray = new ArrayList<BranchYearInfo>();
		branchArraylist = new ArrayList<String>();
		yearArray = new ArrayList<BranchYearInfo>();
		yearArraylist = new ArrayList<String>();
		subjectArray = new ArrayList<AdminInfo>();
		subjectArraylist = new ArrayList<String>();
		branchidArray = new ArrayList<AdminInfo>();
		branchidArraylist = new ArrayList<String>();
		yearidArray = new ArrayList<AdminInfo>();
		yearidArraylist = new ArrayList<String>();
		subarraylist = new ArrayList<String>();
		lectsubjectArray = new ArrayList<AdminInfo>();

		AdminActivity main = (AdminActivity) getActivity();
		Bundle b = main.getAdminLectid();
		System.out.println("1");
		lid = b.getString("LectID");
		setBranchYearSpinner();

		ok = (Button) v.findViewById(R.id.adminlectdt_ok);
		submit = (Button) v.findViewById(R.id.adminlectdt_submit);
		branchname = (Spinner) v.findViewById(R.id.spinner_branchname);
		year = (Spinner) v.findViewById(R.id.spinner_yearname);
		subjname = (Spinner) v.findViewById(R.id.spinner_subjname);

		submit.setOnClickListener(this);

		ok.setOnClickListener(this);
		year.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String year1 = year.getSelectedItem().toString();
				Iterator yearitr1 = yearArray.iterator();
				for (int i = 0; i < yearArray.size(); i++) {
					BranchYearInfo yainfo = (BranchYearInfo) yearitr1.next();
					if (year1.equals(yainfo.getYear_name())) {
						yid = yainfo.getYear_id();
						System.out.println("YEAR= " + yid);
						break;
					}

				}
				subjname.setAdapter(null);
				sid = "";
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		branchname.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String brnch = branchname.getSelectedItem().toString();
				Iterator branchitr1 = branchArray.iterator();
				for (int i = 0; i < branchArray.size(); i++) {
					BranchYearInfo ainfo = (BranchYearInfo) branchitr1.next();
					if (brnch.equals(ainfo.getBranch_name())) {
						bid = ainfo.getBranch_id();
						System.out.println("BID= " + bid);
						break;
					}

				}
				subjname.setAdapter(null);
				sid = "";
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		subjname.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String sub = subjname.getSelectedItem().toString();
				Iterator subhitr1 = subjectArray.iterator();
				for (int i = 0; i < subjectArray.size(); i++) {
					AdminInfo sainfo = (AdminInfo) subhitr1.next();
					if (sub.equals(sainfo.getSubjectname())) {
						sid = sainfo.getSub_id();
						System.out.println("sID= " + sid);
						break;
					}

				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		return v;

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.adminlectdt_ok:

			setSubjSpinner();
			break;
		case R.id.adminlectdt_submit:
			int i;
			if (sid.equals("")) {
				Toast.makeText(getActivity(), "Select Subject First..!!",
						Toast.LENGTH_SHORT).show();
			} else {

				Iterator subIditr = lectsubjectArray.iterator();
				for (i = 0; i < lectsubjectArray.size(); i++) {
					AdminInfo iDsainfo = (AdminInfo) subIditr.next();
					String subnmid = iDsainfo.getSub_id();
					if (sid.equals(subnmid)) {
						Toast.makeText(getActivity(),
								"Subject Allredy Exist..!!", Toast.LENGTH_SHORT)
								.show();
						break;
					}

				}
				if (i == lectsubjectArray.size()) {
					getdb = new AdminInfoNetwork();
					new Thread(new Runnable() {
						public void run() {

							System.out.println("lid=" + lid + " yid=" + yid
									+ " bid=" + bid + " sid=" + sid);
							getdb.setBranchIdInsert(lid, yid, bid, sid);

							System.out.println("i m in onclick method");

						}
					}).start();
					Toast.makeText(getActivity(), "Subject Allocated ..!!",
							Toast.LENGTH_SHORT).show();

				}
			}
			break;
		default:
			break;
		}
	}

	private void setBranchYearSpinner() {
		// TODO Auto-generated method stub

		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			public void run() {
				spYear = obj.getAdminAllYear();
				System.out.println("spYear =" + spYear);
				spBranch = obj.getAdminAllBranch();
				System.out.println("spBranch =" + spBranch);
				lectSubject = obj.getlectAllocatedsubjids(lid);
				System.out.println("lectsubjectArray= " + lectSubject);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						ArrayList<BranchYearInfo> branchInfo = parseJsonBranch(spBranch);
						Iterator branchitr = branchInfo.iterator();
						while (branchitr.hasNext()) {
							BranchYearInfo ainfo = (BranchYearInfo) branchitr
									.next();
							branchArray.add(ainfo);
							branchArraylist.add(ainfo.getBranch_name());
							System.out.println("bName " + ainfo.getBranch_id());

						}
						ArrayAdapter<String> branchAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item,
								branchArraylist);
						branchAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						branchname.setAdapter(branchAdapter);

						ArrayList<BranchYearInfo> yearInfo = parseJsonYear(spYear);

						Iterator yearitr = yearInfo.iterator();
						while (yearitr.hasNext()) {
							BranchYearInfo linfo = (BranchYearInfo) yearitr
									.next();
							yearArraylist.add(linfo.getYear_name());
							yearArray.add(linfo);
							System.out.println("yName " + linfo.getYear_id());

						}

						ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item,
								yearArraylist);
						yearAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						year.setAdapter(yearAdapter);

						ArrayList<AdminInfo> lectsubinfo = parseJsonsubid(lectSubject);

						Iterator subitr = lectsubinfo.iterator();
						while (subitr.hasNext()) {
							System.out.println("3");

							AdminInfo sainfo = (AdminInfo) subitr.next();

							lectsubjectArray.add(sainfo);
							System.out.println("lectsubjectArray  "
									+ lectsubjectArray);
							System.out.println("DD888 " + sainfo.getSub_id());
						}

					}

				});
			}
		}).start();

	}

	private void setSubjSpinner() {
		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			@SuppressLint("NewApi")
			public void run() {
				Subj_INFO = obj.getAdminSubName(bid, yid);
				System.out.println("getAllIDs =" + Subj_INFO);

				getActivity().runOnUiThread(new Runnable() {

					@SuppressLint("NewApi")
					public void run() {
						System.out.println("in run method");
						ArrayList<AdminInfo> adminsubinfo = new ArrayList<AdminInfo>();
						adminsubinfo.clear();
						subjectArraylist.clear();
						System.out.println("1");
						adminsubinfo = parseJsonSub(Subj_INFO);
						Iterator subitr = adminsubinfo.iterator();
						System.out.println("2");
						while (subitr.hasNext()) {
							System.out.println("3");

							AdminInfo sainfo = (AdminInfo) subitr.next();
							System.out.println("4");
							subjectArray.add(sainfo);
							subjectArraylist.add(sainfo.getSubjectname());

							System.out.println("idsArray00000  " + subjectArray
									+ subjectArraylist);
							System.out.println("DD " + sainfo.getSubjectname());
						}

						System.out.println("5");

						ArrayAdapter<String> subAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item,
								subjectArraylist);
						subAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						subjname.setAdapter(subAdapter);
						System.out.println("6");
					}

				});
			}
		}).start();
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

	private ArrayList<AdminInfo> parseJsonSub(String result) {
		ArrayList<AdminInfo> getbranch = new ArrayList<AdminInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				AdminInfo menu = new AdminInfo();
				menu.setSubjectname(jsonObj.getString("Subject_Name"));
				menu.setSub_id(jsonObj.getString("Subject_id"));
				getbranch.add(menu);

			}

		} catch (Exception e) {

		}
		return getbranch;
	}

	private ArrayList<AdminInfo> parseJsonsubid(String result) {
		ArrayList<AdminInfo> getbranch = new ArrayList<AdminInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				AdminInfo menu = new AdminInfo();

				menu.setSub_id(jsonObj.getString("Subject_id"));
				getbranch.add(menu);

			}

		} catch (Exception e) {

		}
		return getbranch;
	}
}
