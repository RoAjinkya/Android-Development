package com.example.studentattendencesystem.NetworkClass;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class AdminInfoNetwork {
	public String getAdminInfo(String aId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("AdminLOGid", aId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getAdminInfoforLogin.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminAllBranch() {

		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getAdminAllBranch.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminAllYear() {

		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getAdminAllYear.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminBranchID(String bId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("BranchID", bId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getBranchID.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminYearID(String yId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("YearID", yId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getYearID.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminStudentID(String yId, String bId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("YearID", yId));
		studLId.add(new BasicNameValuePair("BranchID", bId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getStudentIds.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminStudExamInfo(String sId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("StudentID", sId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getStudExamInfo.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminStudentName(String sId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("StudentID", sId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getStudentName.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public void updateStudentyear(String yid, String sid) {
		ArrayList<NameValuePair> attendance = new ArrayList<NameValuePair>();
		attendance.add(new BasicNameValuePair("StudentID", sid));
		attendance.add(new BasicNameValuePair("YearID", yid));

		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/updateStudentYear.php");
			httppost.setEntity(new UrlEncodedFormEntity(attendance));
			HttpResponse respnc = httpclient.execute(httppost);

		} catch (Exception e) {
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());
		}

	}

	public void addAdminBranch(String bname) {
		ArrayList<NameValuePair> attendance = new ArrayList<NameValuePair>();
		attendance.add(new BasicNameValuePair("Branch", bname));

		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/addAdminBranch.php");
			httppost.setEntity(new UrlEncodedFormEntity(attendance));
			HttpResponse respnc = httpclient.execute(httppost);

		} catch (Exception e) {
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());
		}

	}

	public void deleteAdminBranch(String bid) {
		ArrayList<NameValuePair> attendance = new ArrayList<NameValuePair>();
		attendance.add(new BasicNameValuePair("BranchID", bid));

		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/deleteBranch.php");
			httppost.setEntity(new UrlEncodedFormEntity(attendance));
			HttpResponse respnc = httpclient.execute(httppost);

		} catch (Exception e) {
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());
		}

	}

	public String getAdminSubjectID(String yId, String bId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("YearID", yId));
		studLId.add(new BasicNameValuePair("BranchID", bId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getSubjectIds.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminSubjectName(String sId, String bid) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("YearID", sId));
		studLId.add(new BasicNameValuePair("BranchID", bid));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getSubjectName.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public void addAdminSubject(String sname, String bid, String yid) {
		ArrayList<NameValuePair> attendance = new ArrayList<NameValuePair>();
		attendance.add(new BasicNameValuePair("Subject", sname));
		attendance.add(new BasicNameValuePair("BranchID", bid));
		attendance.add(new BasicNameValuePair("YearID", yid));

		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/addAdminSubject.php");
			httppost.setEntity(new UrlEncodedFormEntity(attendance));
			HttpResponse respnc = httpclient.execute(httppost);

		} catch (Exception e) {
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());
		}

	}

	public void deleteAdminSubjetct(String sid) {
		ArrayList<NameValuePair> attendance = new ArrayList<NameValuePair>();
		attendance.add(new BasicNameValuePair("SubjectID", sid));

		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/deleteSubject.php");
			httppost.setEntity(new UrlEncodedFormEntity(attendance));
			HttpResponse respnc = httpclient.execute(httppost);

		} catch (Exception e) {
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());
		}

	}

	public String getAdminLecturerInfo() {

		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getLecturerInfo.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminLectSubject(String lId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("LectID", lId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getLectSubjetcName.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminSubBrnchYearIDs(String lId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("LectID", lId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getAdminLectBranchYrSubjctIDS.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminLectBranchNM(String bId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("BranchID", bId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getAdminLectBranchName.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminLectYearNM(String yId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("YearID", yId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getAdminLectYearName.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminLectSubjectNM(String sId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("SubjectID", sId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getAdminLectSubjectName.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminBranchName() {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		// studLId.add(new BasicNameValuePair("Branchname", branch));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getbranchname.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminYearName() {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		// studLId.add(new BasicNameValuePair("Branchname", branch));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getyearname.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getAdminSubName(String yId, String bId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("year", yId));
		studLId.add(new BasicNameValuePair("branch", bId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getadminsubjectname.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studLId));
			ResponseHandler<String> resHandler = new BasicResponseHandler();
			final String respnc = httpclient.execute(httppost, resHandler);
			System.out.println("resp " + respnc);

			return respnc.trim();

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());

			return "error";
		}
	}

	public String getlectbranchids(String branch) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("branch", branch));

		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getbranchids.php");
			System.out.println(hpost);

			hpost.setEntity(new UrlEncodedFormEntity(pList));

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	public String getlectsubjids(String branch) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("subj", branch));

		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getadminsubjectids.php");
			System.out.println(hpost);

			hpost.setEntity(new UrlEncodedFormEntity(pList));
			System.out.println("pList ==" + pList);
			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	public String getlectyearids(String year) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("year", year));
		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getyearids.php");
			System.out.println(hpost);

			hpost.setEntity(new UrlEncodedFormEntity(pList));

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	public void setBranchIdInsert(String lectid, String year, String branch,
			String subj) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("lectid", lectid));
		System.out.println("1");

		pList.add(new BasicNameValuePair("yearid", year));
		System.out.println("2");

		pList.add(new BasicNameValuePair("branchid", branch));

		pList.add(new BasicNameValuePair("subid", subj));

		System.out.println("3");

		try {
			System.out.println("4");

			HttpPost httpPost;
			HttpClient client = new DefaultHttpClient();
			System.out.println("5");

			httpPost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/setinsertbrnchyearids.php");
			System.out.println("on netcheck :" + pList);

			httpPost.setEntity(new UrlEncodedFormEntity(pList));
			System.out.println("6");

			HttpResponse entity = client.execute(httpPost);
			System.out.println("7");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void deleteSubjetctList(String sid) {
		ArrayList<NameValuePair> attendance = new ArrayList<NameValuePair>();
		attendance.add(new BasicNameValuePair("SubjectID", sid));

		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/deleteSubjlist.php");
			httppost.setEntity(new UrlEncodedFormEntity(attendance));
			HttpResponse respnc = httpclient.execute(httppost);

		} catch (Exception e) {
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());
		}

	}
	
	
	public String getlectAllocatedsubjids(String lid) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("LectID", lid));

		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getLectAllocatedSubject.php");
			System.out.println(hpost);

			hpost.setEntity(new UrlEncodedFormEntity(pList));
			System.out.println("pList ==" + pList);
			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}
	public String getAdminloginid() {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		try {
			System.out.println("1");
			HttpPost hpost;
			System.out.println("2");

			HttpClient hclient = new DefaultHttpClient();
			System.out.println("3");

			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getadminlogin.php");
			System.out.println(hpost);

			hpost.setEntity(new UrlEncodedFormEntity(pList));

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("4");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}
	public String getLectloginid() {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getlectloginid.php");
			System.out.println(hpost);

			hpost.setEntity(new UrlEncodedFormEntity(pList));

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	
	public String getStudloginid() {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/getstudlogin.php");
			System.out.println(hpost);

			hpost.setEntity(new UrlEncodedFormEntity(pList));

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}
	
	public String changepasswd(String old,String newid) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("OldID", old));
		pList.add(new BasicNameValuePair("NewID", newid));

		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/updateloginid.php");
			System.out.println(hpost);

			hpost.setEntity(new UrlEncodedFormEntity(pList));

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	public String changepasswdlect(String old,String newid) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("OldID", old));
		pList.add(new BasicNameValuePair("NewID", newid));

		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/updatelectlogin.php");
			System.out.println(hpost);

			hpost.setEntity(new UrlEncodedFormEntity(pList));

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	public String changepasswdstud(String old,String newid) {
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("OldID", old));
		pList.add(new BasicNameValuePair("NewID", newid));


		try {
			System.out.println("1");
			HttpPost hpost;

			HttpClient hclient = new DefaultHttpClient();
			hpost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Admin/updatestudid.php");
			System.out.println(hpost);

			hpost.setEntity(new UrlEncodedFormEntity(pList));

			ResponseHandler<String> handler = new BasicResponseHandler();
			final String respose = hclient.execute(hpost, handler);
			System.out.println("2");
			return respose.trim();

		} catch (Exception e) {
			System.out.println("ecxception" + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}
			
}

