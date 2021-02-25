package com.prankit.customermanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private EditText fname,lname,address,num,dob,joindate;
    private TextView adhar,add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = findViewById(R.id.inputNum);
        fname = findViewById(R.id.inputNum);
        lname = findViewById(R.id.inputNum);
        address = findViewById(R.id.inputAddress);
        adhar = findViewById(R.id.inputAdhar);
        add = findViewById(R.id.addCustomer);

        adhar.setOnClickListener(view -> pdfIntent());

        add.setOnClickListener(view -> {
            //addCustomer();
        });
    }

    public void pdfIntent() {
        String[] mimeTypes = {"*/*"};
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), 11);
    }

    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropImage.ActivityResult result = CropImage.getActivityResult(data);
        if (resultCode == -1) {
            if (requestCode != 11) {
                String valueOf = String.valueOf(result.getUri());
                this.imageUri = valueOf;
                if (valueOf != null) {
                    fileType = "png";
                    Log.i("datafile", result.toString() + "\n" + result.getOriginalUri() + "\n" + result.getUri());
                    this.attach_file = this.imageUri;
                }
            }
            else if (requestCode == 11) {
                //   this.tv_attachfilepdf.setText(data.getDataString());
                //  fileType = "pdf";
                Log.e("path", data.getData().getPath());


                //   Uri uri1 = FileProvider.getUriForFile(this, "com.example.worktool_new.provider",new File(data.getData().getPath()));

                //  List<MediaFile> mediaFiles = data.<MediaFile>getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);

                //  Log.e("path1",uri1.getPath());

                if (data.getData().toString().startsWith("content://com.android.providers.downloads.documents/document/")) {
                    Cursor cursor = null;
                    try {

                        Uri uri = ContentUris.withAppendedId(
                                Uri.parse("content://com.android.providers.downloads.documents/document/"), Long.valueOf(DocumentsContract.getDocumentId(data.getData())));
                        cursor = getContentResolver().query(uri, null, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            String s = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                            this.attach_file = Environment.getExternalStorageDirectory() + "/Download/" + s;
                            Log.e("paths", s);
                            fileType = getfiletype(this.attach_file);

                            //   Log.e("filetype",getfiletype(this.attach_file));

                        }
                        cursor.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.attach_file = data.getData().getPath();
                        fileType = getfiletype(this.attach_file);

                    }
                }
                if (data.getData().toString().startsWith("content://com.android.externalstorage.documents/document/")) {
                    Cursor cursor = null;
                    try {

                        Uri uri = ContentUris.withAppendedId(
                                Uri.parse("content://com.android.externalstorage.documents/document/"), Long.valueOf(DocumentsContract.getDocumentId(data.getData())));
                        cursor = getContentResolver().query(uri, null, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            String s = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                            this.attach_file = Environment.getExternalStorageDirectory() + "/Download/" + s;
                            Log.e("paths", s);
                            fileType = getfiletype(this.attach_file);

                            //   Log.e("filetype",getfiletype(this.attach_file));

                        }
                        cursor.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.attach_file = data.getData().getPath();
                        fileType = getfiletype(this.attach_file);

                    }
                } else if (data.getData().toString().startsWith("content://com.android.providers.media.documents/document/")) {
                    Cursor cursor = null;
                    try {


                        //  CropImage.activity().start(this);

                        //  CropImage.ActivityResult result = CropImage.getActivityResult(data);

                       /* String valueOf = String.valueOf(result.getUri());
                        this.imageUri = valueOf;
                        if (valueOf != null) {
                            if (this.isattach.booleanValue()) {
                                this.tv_attachfile.setText(this.imageUri);
                                Log.i("datafile", result.toString() + "\n" + result.getOriginalUri() + "\n" + result.getUri());
                                this.isattach = false;
                                this.attach_file = this.imageUri;
                                fileType=getfiletype(this.attach_file);

                            } else {
                                this.eventphoto.setImageURI(Uri.parse(this.imageUri));
                                this.upload_photo = this.imageUri;
                                this.ismageEdited = true;
                                fileType=getfiletype(this.attach_file);

                            }*/
                        // }
                       /* String string = String.valueOf(data.getData()).replace("image%3A", "");
                        Log.e("string", string);
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        String[] filePathColumn1 = {MediaStore.MediaColumns.DATA};
                        String fileId = DocumentsContract.getDocumentId(data.getData());
                        // Split at colon, use second item in the array
                        String id = fileId.split(":")[1];
                        String[] column = {MediaStore.Images.Media.DATA};
                        String selector = MediaStore.Images.Media._ID + "=?";

                        Uri uri = ContentUris.withAppendedId(
                                Uri.parse("content://com.android.providers.media.documents/document/"), Long.valueOf(DocumentsContract.getDocumentId(Uri.parse(string))));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        }
                        //  cursor = getContentResolver().query(data.getData(), filePathColumn, null, null, null);
//                        int columnIndex = cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME);
                        //  int columnIndex = cursor.getColumnIndexOrThrow(filePathColumn1[0]);
                        // int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                column, selector, new String[]{id}, null);
                        int columnIndex = cursor.getColumnIndex(column[0]);
                        if (cursor != null && cursor.moveToFirst()) {
                            String s = cursor.getString(columnIndex);
                            this.attach_file = s;
                            Log.e("paths", s);
                            fileType = getfiletype(this.attach_file);

                            Log.e("filetype", cursor.toString());

                        }
                        cursor.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.attach_file = data.getData().getPath();
                        fileType = getfiletype(this.attach_file);

                    }
                } else {
                    this.attach_file = data.getData().getPath();

                    fileType = getfiletype(this.attach_file);

                }
                String filePath = data.getData().getPath();
                String fileName = data.getData().getLastPathSegment();
                Log.e("file", filePath + "\n" + fileName + "\n" + attach_file);
            }
        }
    }*/

    /*private void addCustomer() {
        if(num.getText().toString().equals("")||fname.getText().toString().equals("")||
                lname.getText().toString().equals("")||address.getText().toString().equals("")){
            return;
        } else {
            ((Apis) new Retrofit.Builder().baseUrl("baseUrl")
                    .addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder()
                            .addInterceptor(provideHttpLoggingInterceptor()).readTimeout(120,TimeUnit.SECONDS)
                            .writeTimeout(120,TimeUnit.SECONDS).build()).build().create(Apis.class)).
                    methodName(num,fname,lname,address,dob,adhar).enqueue(new Callback<>() {
                public void onResponse(Call<WallModel> call, Response<WallModel> response) {
                }

                public void onFailure(Call<WallModel> call, Throwable t) {
                }
            });
        }
    }*/
}

/*
if (this.attach_file != null) {
                if (fileType != null && (fileType.equals("png") || fileType.equals("jpeg") || fileType.equals("jpg"))) {
                    File file2 = new File(Uri.parse(this.attach_file).getPath());
                    ImagePic = MultipartBody.Part.createFormData("file", file2.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file2));
                } else if (fileType.equals("pdf")) {
                    File file2 = null;
                    final String[] split = this.attach_file.split(":");
                    if (split[0].contains("primary")) {
                        file2 = new File(Uri.parse(Environment.getExternalStorageDirectory() + "/" + split[1]).getPath());
                    } else {
                        file2 = new File(Uri.parse(this.attach_file).getPath());
                    }
                    ImagePic = MultipartBody.Part.createFormData("file", file2.getName(), RequestBody.create(MediaType.parse("application/pdf"), file2));
                } else {
                    File file2 = null;
                    final String[] split = this.attach_file.split(":");
                    if (split[0].contains("primary")) {
                        file2 = new File(Uri.parse(Environment.getExternalStorageDirectory() + "/" + split[1]).getPath());
                    } else {
                        file2 = new File(Uri.parse(this.attach_file).getPath());
                    }
                    Uri uri = FileProvider.getUriForFile(this, "com.example.worktool_new.provider", file2);

                    List<ResolveInfo> resInfoList = this.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        this.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    ImagePic = MultipartBody.Part.createFormData("file", file2.getName(), RequestBody.create(MediaType.parse("application/vnd.ms-excel"), file2));
                }
            }
            RequestBody id = RequestBody.create(MediaType.parse("text/plain"), App.getAppPreference().getString("LoginId"));
 */

/*
@GET("articles.php")
    Call<ArticlesModel> articles(@Query("id") String str);

    @POST("post_editevent.php")
    @Multipart
    Call<ResponseBody> EditEvent(@Part("id") RequestBody requestBody, @Part("idCompteAuteur") RequestBody requestBody2,, @Part MultipartBody.Part part);
 */

/*
public class PostProfileModel {
    @SerializedName("data")
    @Expose
    private PostProfileBodItem data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("statuscode")
    @Expose
    private Integer statuscode;

    public PostProfileBodItem getData() {
        return this.data;
    }

    public void setData(PostProfileBodItem data2) {
        this.data = data2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public Integer getStatuscode() {
        return this.statuscode;
    }

    public void setStatuscode(Integer statuscode2) {
        this.statuscode = statuscode2;
    }
}
 */