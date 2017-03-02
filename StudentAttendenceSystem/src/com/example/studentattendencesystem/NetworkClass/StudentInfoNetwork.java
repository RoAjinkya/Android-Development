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

public class StudentInfoNetwork {
	public String getStudentPassword(String LId) {

		ArrayList<NameValuePair> studLId = new ArrayList<NameValuePair>();
		studLId.add(new BasicNameValuePair("StudentLOGid", LId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Student/getStudentPassword.php");

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
	
	public String getStudentStatus(String sId,String date,String subjct) {

		ArrayList<NameValuePair> studsId = new ArrayList<NameValuePair>();
		studsId.add(new BasicNameValuePair("StudentLOGid", sId));
		studsId.add(new BasicNameValuePair("ATTDate", date));
		studsId.add(new BasicNameValuePair("ATTSubject", subjct));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Student/getStatusFStudent.php");

			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studsId));
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
	public String getStudentSubjectesID(String branchId,String yearId)
	{
		ArrayList<NameValuePair> lectSub = new ArrayList<NameValuePair>();
		lectSub.add(new BasicNameValuePair("BranchIDSUB",branchId));
		lectSub.add(new BasicNameValuePair("YearIDSUB",yearId));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Student/getStudentSubjects.php");
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
						"http://gopajibaba.com/StudentAttendence/Student/getallSubjects.php");
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
	
	public String getStudentMarks(String sid,String eid)
	{
		ArrayList<NameValuePair> studInfo = new ArrayList<NameValuePair>();
		studInfo.add(new BasicNameValuePair("Studentid",sid));
		studInfo.add(new BasicNameValuePair("Examid",eid));
		try {

			HttpPost httppost;
			HttpClient httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://gopajibaba.com/StudentAttendence/Student/getStudentMarks.php");
			HttpResponse httprespnc = httpclient.execute(httppost);

			httppost.setEntity(new UrlEncodedFormEntity(studInfo));
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

}
