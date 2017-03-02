package com.example.studentattendencesystem;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import com.example.studentattendencesystem.NetworkClass.LecturerInfoNetwork;
import com.example.studentattendencesystem.javaclasses.LectuererInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class LecturerLoginFragment extends Fragment {
	private View v;
	private EditText id, password;
	private Button login;
	private String data;
	private ArrayList<LectuererInfo> lectinfoArray;
	private LectuererInfo info;
	private Bundle lecturerBundle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.lecturer_login, container, false);
		id = (EditText) v.findViewById(R.id.lecturerlogin_id);
		password = (EditText) v.findViewById(R.id.lecturerlogin_password);
		login = (Button) v.findViewById(R.id.lecturerlogin_button);
		lectinfoArray = new ArrayList<LectuererInfo>();
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				final LecturerInfoNetwork obj = new LecturerInfoNetwork();

				new Thread(new Runnable() {

					public void run() {

						data = obj.getLecturerInfo();
						System.out.println("Lecturer info ="+data);
						getActivity().runOnUiThread(new Runnable() {
							public void run() {
								int i = 0;
								ArrayList<LectuererInfo> mainInfo = parseJson(data);
								Iterator itr = mainInfo.iterator();
								while (itr.hasNext()) {
									LectuererInfo linfo = (LectuererInfo) itr
											.next();

									lectinfoArray.add(linfo);
									i++;
								}

								Iterator<LectuererInfo> itre = lectinfoArray
										.iterator();
								String inuser = id.getText().toString();
								String inpassword = password.getText()
										.toString();
								String dbuser = null;
								String dbpassword = null;
								if (inuser.equals("") && inpassword.equals("")) {
									Toast.makeText(getActivity(),
											"Enter Username and password ",
											Toast.LENGTH_LONG).show();
								} else {

									while (itre.hasNext()) {
										info = itre.next();
										dbuser = info.getLect_Login();
										dbpassword = info.getLect_Password();
										LecturerActivity lecturerActivity = (LecturerActivity) getActivity();

										if (inuser.equals(dbuser)
												|| inpassword
														.equals(dbpassword)) {
											if (!inuser.equals(dbuser)) {
												Toast.makeText(
														getActivity(),
														"Enter Correct UserName",
														Toast.LENGTH_SHORT)
														.show();
											} else if (!inpassword
													.equals(dbpassword)) {
												Toast.makeText(
														getActivity(),
														"Enter Correct Pasword",
														Toast.LENGTH_SHORT)
														.show();
											} else {
												Toast.makeText(getActivity(),
														"LogIn Successfull",
														Toast.LENGTH_SHORT)
														.show();

												lecturerBundle = new Bundle();
												lecturerBundle.putInt("LectID",
														info.getLect_id());
												lecturerBundle.putString(
														"FirstName",
														info.getLect_Fname());
												lecturerBundle.putString(
														"LastName",
														info.getLect_Lname());
												lecturerBundle.putString(
														"Address",
														info.getLect_address());
												lecturerBundle.putInt(
														"LectContact",
														info.getLect_contact());
												lecturerBundle.putString(
														"LectEmail",
														info.getLect_email());

												lecturerActivity
														.setbundleLecturerInfo(lecturerBundle);
												lecturerActivity
														.firstViewFragment();

											}
											break;
										}
									}
									if (!inuser.equals(dbuser)
											&& !inpassword.equals(dbpassword)) {
										Toast.makeText(
												getActivity(),
												"UserName And Password are Not Available",
												Toast.LENGTH_SHORT).show();
										id.setText("");
										password.setText("");

									}
								}

							}
						});
					}
				}).start();

			}
		});

		return v;
	}

	public ArrayList<LectuererInfo> parseJson(String result) {
		ArrayList<LectuererInfo> mainObj = new ArrayList<LectuererInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				LectuererInfo menu = new LectuererInfo();
				menu.setLect_id(jsonObj.getInt("Lecturer_id"));
				menu.setLect_Fname(jsonObj.getString("FirstName"));
				menu.setLect_Lname(jsonObj.getString("LastName"));
				menu.setLect_address(jsonObj.getString("City"));
				menu.setLect_contact(jsonObj.getInt("Contact_No"));
				menu.setLect_email(jsonObj.getString("Email_Id"));
				menu.setLect_Login(jsonObj.getString("Login_id"));
				menu.setLect_Password(jsonObj.getString("Password"));
				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

}
