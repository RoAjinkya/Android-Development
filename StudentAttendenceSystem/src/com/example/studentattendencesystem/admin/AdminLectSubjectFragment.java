package com.example.studentattendencesystem.admin;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.AdminInfoNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.LectuererInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({ "NewApi", "ValidFragment" })
public class AdminLectSubjectFragment extends Fragment implements
		OnClickListener {
	View v;
	TextView addSubject,refrsh;
	ListView sublist;
	ArrayList<BranchYearInfo> allids;
	ArrayList<LectuererInfo> nameArray;
	ArrayList<LectuererInfo> branchArray;
	ArrayList<LectuererInfo> yearArray;
	String getsubname = "", getbrnchname = "", getyrname = "", Subject_Name,
			Branch_Name, Year_Name;
	AdminLectrerSubjectAdapter adapter;
	String i="";

	public AdminLectSubjectFragment(ArrayList<BranchYearInfo> allids) {
		// TODO Auto-generated constructor stub
		this.allids = allids;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.admin_lectsubject, container, false);
		nameArray = new ArrayList<LectuererInfo>();
		branchArray = new ArrayList<LectuererInfo>();
		yearArray = new ArrayList<LectuererInfo>();
		addSubject = (TextView) v.findViewById(R.id.adminlectsub_btnsub);
		sublist = (ListView) v.findViewById(R.id.adminlectsub_sublist);
		setLectSubjectInfo();
		refrsh=(TextView)v.findViewById(R.id.adminlectsub_btnrefresh);
		addSubject.setOnClickListener(this);
		refrsh.setOnClickListener(this);
		
		return v;
	}

	private void setLectSubjectInfo() {
		// TODO Auto-generated method stub
		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < allids.size(); i++) {
					BranchYearInfo bysinfo = allids.get(i);
					getsubname = getsubname
							+ obj.getAdminLectSubjectNM(bysinfo.getSubject_id());
					getbrnchname = getbrnchname
							+ obj.getAdminLectBranchNM(bysinfo.getBranch_id());
					getyrname = getyrname
							+ obj.getAdminLectYearNM(bysinfo.getYear_id());

				}
				System.out.println("getsubname =" + getsubname);
				separateJsonSubjectString();
				separateJsonBranchString();
				separateJsonYearString();
				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						ArrayList<LectuererInfo> lectInfosn = parseJsonSubjectName(Subject_Name);
						Iterator subjecthitr = lectInfosn.iterator();
						while (subjecthitr.hasNext()) {
							LectuererInfo snnfo = (LectuererInfo) subjecthitr
									.next();
							nameArray.add(snnfo);

							System.out.println("sbjectnameArray  "
									+ snnfo.getLect_subject());
						}

						ArrayList<LectuererInfo> lectInfobr = parseJsonBranchName(Branch_Name);
						Iterator branchitr = lectInfobr.iterator();
						while (branchitr.hasNext()) {
							LectuererInfo brnfo = (LectuererInfo) branchitr
									.next();
							branchArray.add(brnfo);

							System.out.println("branchnameArray  "
									+ brnfo.getLect_branch());
						}

						ArrayList<LectuererInfo> lectInfoyr = parseJsonYearName(Year_Name);
						Iterator yearitr = lectInfoyr.iterator();
						while (yearitr.hasNext()) {
							LectuererInfo yrnfo = (LectuererInfo) yearitr
									.next();
							yearArray.add(yrnfo);

							System.out.println("yearnameArray  "
									+ yrnfo.getLect_year());
						}

						System.out.println("Lengthof nameArray="
								+ nameArray.size());
					 adapter = new AdminLectrerSubjectAdapter(
								getActivity(), R.layout.admin_lectlist,
								nameArray, branchArray, yearArray);
						sublist.setAdapter(adapter);
					}

				});
			}

			private void separateJsonYearString() {
				// TODO Auto-generated method stub
				int i = 0;
				StringBuilder cleanerYear = new StringBuilder(getyrname);
				StringBuilder saver = new StringBuilder(getyrname);
				StringBuilder YearNAME = null;
				int lengthStrng = cleanerYear.length();

				for (int j = 0; j < cleanerYear.length(); j++) {
					if (cleanerYear.charAt(j) == ']') {
						if (cleanerYear.charAt(j + 1) == '[') {
							YearNAME = cleanerYear.replace(j, j + 1, ",");
							cleanerYear.deleteCharAt(j + 1);
							lengthStrng = cleanerYear.length();

						}
					}

					if (lengthStrng == j + 2) {
						System.out.println("BREAK");
						Year_Name = cleanerYear.toString();
						System.out.println("Year_Name=" + Year_Name);
						break;
					}
				}

			}

			private void separateJsonBranchString() {
				// TODO Auto-generated method stub
				int i = 0;
				StringBuilder cleanerBranch = new StringBuilder(getbrnchname);
				StringBuilder saver = new StringBuilder(getbrnchname);
				StringBuilder BranchNAME = null;
				int lengthStrng = cleanerBranch.length();

				for (int j = 0; j < cleanerBranch.length(); j++) {
					if (cleanerBranch.charAt(j) == ']') {
						if (cleanerBranch.charAt(j + 1) == '[') {
							BranchNAME = cleanerBranch.replace(j, j + 1, ",");
							cleanerBranch.deleteCharAt(j + 1);
							lengthStrng = cleanerBranch.length();

						}
					}

					if (lengthStrng == j + 2) {
						System.out.println("BREAK");
						Branch_Name = cleanerBranch.toString();
						System.out.println("Branch_Name=" + Branch_Name);
						break;
					}
				}
			}

			private void separateJsonSubjectString() {
				// TODO Auto-generated method stub
				int i = 0;
				StringBuilder cleaner = new StringBuilder(getsubname);
				StringBuilder saver = new StringBuilder(getsubname);
				StringBuilder subjectNAME = null;
				int lengthStrng = cleaner.length();

				for (int j = 0; j < cleaner.length(); j++) {
					System.out.println(" J ="+j);
					System.out.println("cleaner.length() ="+cleaner.length());
					if (cleaner.charAt(j) == ']') {
						if (cleaner.charAt(j + 1) == '[') {
							subjectNAME = cleaner.replace(j, j + 1, ",");
							cleaner.deleteCharAt(j + 1);
							lengthStrng = cleaner.length();

						}
					}

					if (lengthStrng == j + 2) {
						System.out.println("BREAK");
						Subject_Name = cleaner.toString();
						break;
					}
					
				}
			}
		}).start();
		
		
		sublist.setOnItemClickListener(new OnItemClickListener() {

		@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-gene.03rated method stub
				LectuererInfo getsubid=nameArray.get(arg2);
			String sid=getsubid.getLect_subjectid();
			System.out.println("SSSSSSSSSSSSSSSS"+sid);
 			AdminLectDeleteSubject deletedialog = new AdminLectDeleteSubject(
 
						getActivity(),sid,""+arg2,""+allids.size());
				deletedialog.show();
			}
		});
	}

	public ArrayList<LectuererInfo> parseJsonSubjectName(String result) {
		ArrayList<LectuererInfo> mainObj = new ArrayList<LectuererInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				LectuererInfo menu = new LectuererInfo();
				menu.setLect_subject(jsonObj.getString("Subject_Name"));
				menu.setLect_subjectid(jsonObj.getString("Subject_id"));

				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public ArrayList<LectuererInfo> parseJsonYearName(String result) {
		ArrayList<LectuererInfo> mainObj = new ArrayList<LectuererInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				LectuererInfo menu = new LectuererInfo();
				menu.setLect_year(jsonObj.getString("Year_Name"));

				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public ArrayList<LectuererInfo> parseJsonBranchName(String result) {
		ArrayList<LectuererInfo> mainObj = new ArrayList<LectuererInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				LectuererInfo menu = new LectuererInfo();
				menu.setLect_branch(jsonObj.getString("Branch_Name"));

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
		case R.id.adminlectsub_btnsub:
			AdminActivity main = (AdminActivity) getActivity();
			main.adminLecturerAddSubject();
			break;

		case R.id.adminlectsub_btnrefresh:
		System.out.println("REFRESH");
		try{
			AdminActivity c=(AdminActivity)getActivity();
			Bundle bndl=c.getDelteLectSubject();
			 i=bndl.getString("dltSub");
			
			if(!i.equals(""+allids.size()))
			{
				allids.remove(Integer.parseInt(i));
			c.adminLecturerSubject(allids);
			}else {
			
				c.adminLecturerSubject(allids);
			}
		}catch (NullPointerException e) {
			AdminActivity c1=(AdminActivity)getActivity();

			c1.adminLecturerSubject(allids);
		}

		default:
			break;
		}

	}

}
