package com.example.studentattendencesystem.NetworkClass;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.transform.Result;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.graphics.YuvImage;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Log;

public class LecturerInfoNetwork extends AsyncTask<ArrayList<String>, ArrayList<String>, Integer>
{
	public String getLecturerInfo() {
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/getLecturerInfo.php");
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
	public String getLecturerBranchYearId(String lId)
	{
		ArrayList<NameValuePair> lectId = new ArrayList<NameValuePair>();
		lectId.add(new BasicNameValuePair("LecturerId", lId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/getLectYearBranch.php");
			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(lectId));
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
	public String getstudid(String brnchid,String yearid)
	{
		ArrayList<NameValuePair> studId = new ArrayList<NameValuePair>();
		studId.add(new BasicNameValuePair("BranchIDSUB", brnchid));
		studId.add(new BasicNameValuePair("YearIDSUB", yearid));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/getStudentId.php");
			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studId));
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
	
	
	public String getAllBranches() {
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/getAllBranches.php");
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
	public String getAllYear() {
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/getAllYear.php");
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
	
	
	public String getLecturersSubjectesID(String branchId,String yearId)
	{
		ArrayList<NameValuePair> lectSub = new ArrayList<NameValuePair>();
		lectSub.add(new BasicNameValuePair("BranchIDSUB",branchId));
		lectSub.add(new BasicNameValuePair("YearIDSUB",yearId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/getLecturerSubjects.php");
			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(lectSub));
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
	public String getAllSubjects()
	{
		
			try {

				HttpPost httppost;
				HttpClient httpclient = new DefaultHttpClient();
				httppost = new HttpPost(
						"http://gopajibaba.com/StudentAttendence/Lecturer/getallSubjects.php");
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
	public String getbranchname(String branchId)
	{
		ArrayList<NameValuePair> lectSub = new ArrayList<NameValuePair>();
		lectSub.add(new BasicNameValuePair("brnchId",branchId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/getbranchname.php");
			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(lectSub));
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
	public String getyearname(String yearId)
	{
		ArrayList<NameValuePair> lectSub = new ArrayList<NameValuePair>();
		lectSub.add(new BasicNameValuePair("yearId",yearId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/yearname.php");
			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(lectSub));
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
	
	public String getstudflname(String sId)
	{
		ArrayList<NameValuePair> lectSub = new ArrayList<NameValuePair>();
		lectSub.add(new BasicNameValuePair("StudID",sId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/studinfo.php");
			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(lectSub));
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
	public String getStudentIDs(String branchId,String yearId)
	{
		ArrayList<NameValuePair> lectSub = new ArrayList<NameValuePair>();
		lectSub.add(new BasicNameValuePair("BranchIDSUB",branchId));
		lectSub.add(new BasicNameValuePair("YearIDSUB",yearId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/getStudentId.php");
			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(lectSub));
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
	public String getStudentInfo() {
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/getStudentInfo.php");
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
	
	public void setStudentAttendance(String studId,String rollno,String f_name,String l_name,String branch,String year,String date,String status,String time,String subject)
	{
		ArrayList<NameValuePair> attendance = new ArrayList<NameValuePair>();
		attendance.add(new BasicNameValuePair("StudID", studId));
		attendance.add(new BasicNameValuePair("RollNo", rollno));
		attendance.add(new BasicNameValuePair("First_name", f_name));
		attendance.add(new BasicNameValuePair("Last_name", l_name));
		attendance.add(new BasicNameValuePair("Branch", branch));
		attendance.add(new BasicNameValuePair("Year", year));
		attendance.add(new BasicNameValuePair("Date", date));
		attendance.add(new BasicNameValuePair("Status", status));
		attendance.add(new BasicNameValuePair("Time", time));
		attendance.add(new BasicNameValuePair("subject",subject));
		System.out.println(attendance);
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/setAttendenceandMarks.php");
			httppost.setEntity(new UrlEncodedFormEntity(attendance));
			HttpResponse respnc = httpclient.execute(httppost);
		} catch (Exception e) {
			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
			System.out.println("Error :" + e.getMessage());
		}

	}
	public void setinsertattd(ArrayList<String> arr)
	{
		ArrayList<NameValuePair> pList = new ArrayList<NameValuePair>();
		pList.add(new BasicNameValuePair("StudID", arr.get(0)));
		pList.add(new BasicNameValuePair("RollNo", arr.get(1)));
		pList.add(new BasicNameValuePair("First_name", arr.get(2)));
		pList.add(new BasicNameValuePair("Last_name", arr.get(3)));
		pList.add(new BasicNameValuePair("Branch", arr.get(4)));
		pList.add(new BasicNameValuePair("Year", arr.get(5)));
		pList.add(new BasicNameValuePair("Date", arr.get(6)));
		pList.add(new BasicNameValuePair("Time",arr.get(7)));
		pList.add(new BasicNameValuePair("subject", arr.get(8)));
		System.out.println("attdddddddddd"+pList);
		try {

			HttpPost httpPost;
			System.out.println("1111");
			HttpClient client = new DefaultHttpClient();
			System.out.println("122222");
			httpPost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Lecturer/attendance.php");
			System.out.println("13333");
			System.out.println("on netcheck :" + pList);

			httpPost.setEntity(new UrlEncodedFormEntity(pList));
			System.out.println("144444");
			HttpResponse entity = client.execute(httpPost);
			System.out.println("1555");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	protected Integer doInBackground(ArrayList<String>... params) {
		setinsertattd(params[0]);
		return 0;
	}
}
	
//	public void bluetoothAttendance(String stud_id,String rollno,String f_name,String l_name,String branch,String year,String subject)
//	{
//		ArrayList<NameValuePair> blueattd=new  ArrayList<NameValuePair>();
//		blueattd.add(new BasicNameValuePair("StudID", stud_id));
//		blueattd.add(new BasicNameValuePair("RollNo", rollno));
//		//blueattd.add(new BasicNameValuePair("Date", date));
//		blueattd.add(new BasicNameValuePair("First_name", f_name));
//		blueattd.add(new BasicNameValuePair("Last_name", l_name));
//		blueattd.add(new BasicNameValuePair("Branch", branch));
//		blueattd.add(new BasicNameValuePair("Year", year));
//		//blueattd.add(new BasicNameValuePair("status", status));
//		//blueattd.add(new BasicNameValuePair("Time",time));
//		blueattd.add(new BasicNameValuePair("subject", subject));
//		try {
//			HttpPost httpPost;
//			HttpClient httpClient=new DefaultHttpClient();
//			httpPost=new HttpPost("http://apostleinfotech.com/StudentAttendence/Lecturer/attendance.php");
//		} catch (Exception e) {
//			// TODO: handle exception
//			Log.e("log_tag", "ERROR IN HTTP CON " + e.toString());
//			System.out.println("Error :" + e.getMessage());
//		}
//	}
	

