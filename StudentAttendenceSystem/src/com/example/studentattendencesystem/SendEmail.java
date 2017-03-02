package com.example.studentattendencesystem;

import java.io.File;
import java.io.FileOutputStream;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendEmail extends Activity
{
	Button attachment;
	Button button;
	EditText destinationAddress;
	EditText sbj;
	EditText messageBody;
	private static final int PICK_FROM_GALLERY = 101;
	protected static final File FOLDER_NAME = null;
	protected static final int SELECT_PICTURE = 1;
    int columnIndex;
    String email, subject, message, attachmentFile;
    Uri URI = null;
    String strMessageBody;
    String subjects;
    String to;
	FileOutputStream fout = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendemail_activity);

		sbj = (EditText) findViewById(R.id.subject);
		messageBody = (EditText) findViewById(R.id.messageBody);
		button = (Button) findViewById(R.id.button);
		attachment=(Button)findViewById(R.id.attach);
		destinationAddress = (EditText) findViewById(R.id.destinationAddress);
		
		//createFile();
				
		attachment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
					openGallery();

				
				
			}
			});
			

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				
					String subject = sbj.getText().toString();
					String message = messageBody.getText().toString();
					String to = destinationAddress.getText().toString();

					Intent emailActivity = new Intent(Intent.ACTION_SEND);
					
					
					emailActivity.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
					
					
					emailActivity.putExtra(Intent.EXTRA_SUBJECT, subject);
					
					
					if (URI != null) {
                        emailActivity.putExtra(Intent.EXTRA_STREAM, URI);
                 }
                 
					emailActivity.putExtra(android.content.Intent.EXTRA_TEXT, message);
                 startActivity(Intent.createChooser(emailActivity,
                               "Sending email..."));
					
					emailActivity.putExtra(Intent.EXTRA_TEXT, message);

					emailActivity.setType("message/rfc822");

					startActivity(Intent.createChooser(emailActivity, "Select your Email Provider :"));
					Toast.makeText(getApplicationContext(), "Message send sucessfully to mail_id:"+to, Toast.LENGTH_SHORT).show();
				}
				
		});
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
               /**
                * Get Path
                */
               Uri selectedImage = data.getData();
               String[] filePathColumn = { MediaStore.Images.Media.DATA };

               Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
               cursor.moveToFirst();
               columnIndex = cursor.getColumnIndex(filePathColumn[0]);
               attachmentFile = cursor.getString(columnIndex);
               Log.e("Attachment Path:", attachmentFile);
               URI = Uri.parse("file://" + attachmentFile);
               cursor.close();
        }
 }
	public void openGallery() {
		              Intent intent = new Intent();
			              intent.setType("image/*");
			              intent.setAction(Intent.ACTION_GET_CONTENT);
		              intent.putExtra("return-data", true);
		              startActivityForResult(
			                           Intent.createChooser(intent, "Complete action using"),
			                           PICK_FROM_GALLERY);
			 
}	


}
