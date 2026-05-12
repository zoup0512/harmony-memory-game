package com.cube.memorygames.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.api.local.model.LocalUser;
import com.cube.memorygames.api.network.APIService;
import com.cube.memorygames.api.network.body.BodyUpdateProfile;
import com.cube.memorygames.s3.AmazonS3Wrapper;
import com.cube.memorygames.ui.OnlineAdapter;
import com.cube.memorygames.utils.ImageHelper;
import com.memory.brain.training.games.R;
import com.mopub.volley.DefaultRetryPolicy;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCrop.Options;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ProfileActivity extends AppCompatActivity {
    private static final int CHOOSE_PHOTO = 1;
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    public static final int REQUEST_CODE_UCROP = 125;
    public static final int RESULT_CHOOSE_PHOTO = 101;
    public static final int RESULT_TAKE_PHOTO = 100;
    private static final String S3_AVATARS_FOLDER = "user-avatars";
    private static final String S3_BUCKET = "memorygames";
    public static final String STATE_PHOTO_PATH = "photoPath";
    private static final int TAKE_PHOTO = 0;
    public static final String TMP_PHOTO_FILENAME = "tmp_photo.jpg";
    private Bitmap currentChosenBitmap;
    private byte[] currentOpenedImageBytes;
    private String currentPhotoPath;
    private String mCacheDir;
    private String mPhotoFile;
    @Bind({2131624146})
    ImageView profilePicture;
    @Bind({2131624144})
    View progress;
    @Bind({2131624143})
    View save;
    @Bind({2131624145})
    EditText userName;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_profile);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState != null) {
            this.currentPhotoPath = savedInstanceState.getString(STATE_PHOTO_PATH);
        }
        LocalUser localUser = MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser();
        this.userName.setText(localUser.displayName);
        MemoryApplicationModel.getInstance().logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_PROFILE_OPENED);
        Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_PROFILE_OPENED));
        if (TextUtils.isEmpty(localUser.photoUrl)) {
            this.profilePicture.setImageResource(R.drawable.ic_user_placeholder);
        } else {
            this.profilePicture.setVisibility(0);
            int size = getResources().getDimensionPixelSize(R.dimen.avatar_size);
            Picasso.with(this).load(new File(getFilesDir(), OnlineAdapter.PROFILE_PHOTO_NAME)).noFade().into(this.profilePicture);
            Picasso.with(this).load(localUser.photoUrl).resize(size, size).noFade().centerCrop().into(this.profilePicture);
        }
        this.save.setVisibility(8);
        this.userName.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ProfileActivity.this.save.setVisibility(0);
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.mCacheDir = getExternalCacheDir() + "/";
    }

    @OnClick({2131624115})
    public void backClick() {
        finish();
    }

    private void hideProgress() {
        this.progress.setVisibility(8);
        this.userName.setEnabled(true);
        this.profilePicture.setEnabled(true);
    }

    private void showProgress() {
        this.progress.setVisibility(0);
        this.save.setVisibility(8);
        this.userName.setEnabled(false);
        this.profilePicture.setEnabled(false);
    }

    @OnClick({2131624143})
    public void saveClick() {
        showProgress();
        final LocalUser localUser = MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser();
        String oldDisplayName = localUser.displayName;
        String newDisplayName = this.userName.getText().toString().trim();
        if (!(TextUtils.isEmpty(oldDisplayName) || TextUtils.isEmpty(newDisplayName) || oldDisplayName.equals(newDisplayName))) {
            MemoryApplicationModel.getInstance().logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_NICK_CHANGED);
            Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_NICK_CHANGED));
        }
        localUser.setDisplayName(newDisplayName);
        localUser.save();
        if (this.currentOpenedImageBytes == null) {
            hideProgress();
            return;
        }
        MemoryApplicationModel.getInstance().logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_PHOTO_CHANGED);
        Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_PHOTO_CHANGED));
        AmazonS3Wrapper wrapper = new AmazonS3Wrapper(this);
        new Thread(new Runnable() {
            public void run() {
                AmazonS3Client s3Client = new AmazonS3Client(new BasicAWSCredentials(AmazonS3Wrapper.S3_ACCESS_KEY, AmazonS3Wrapper.S3_SECRET_KEY));
                PutObjectRequest por = new PutObjectRequest("memorygames", UUID.randomUUID().toString() + String.valueOf(System.currentTimeMillis()), new ByteArrayInputStream(ProfileActivity.this.currentOpenedImageBytes), new ObjectMetadata());
                por.withCannedAcl(CannedAccessControlList.PublicRead);
                try {
                    PutObjectResult putObjectResult = s3Client.putObject(por);
                    final String resourceUrl = s3Client.getResourceUrl(por.getBucketName(), por.getKey());
                    localUser.setPhotoUrl(resourceUrl);
                    localUser.save();
                    APIService service = MemoryApplicationModel.getInstance().getService();
                    BodyUpdateProfile updateProfile = new BodyUpdateProfile();
                    updateProfile.unlockedContent = localUser.unlockedContent;
                    updateProfile.userId = localUser.objectId;
                    updateProfile.displayName = localUser.displayName;
                    updateProfile.photoUrl = localUser.photoUrl;
                    if (service.updateProfile(updateProfile).execute().isSuccessful()) {
                        localUser.updated = false;
                        localUser.save();
                    }
                    ProfileActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            ProfileActivity.this.hideProgress();
                            Log.e("S3 Amazon", "Ready = " + resourceUrl);
                        }
                    });
                } catch (AmazonServiceException ase) {
                    System.out.println("Error Message:    " + ase.getMessage());
                    System.out.println("HTTP Status Code: " + ase.getStatusCode());
                    System.out.println("AWS Error Code:   " + ase.getErrorCode());
                    System.out.println("Error Type:       " + ase.getErrorType());
                    System.out.println("Request ID:       " + ase.getRequestId());
                } catch (AmazonClientException ace) {
                    System.out.println("Error Message: " + ace.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_PHOTO_PATH, this.currentPhotoPath);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS /*123*/:
                if (grantResults.length <= 0 || grantResults[0] != 0) {
                    Toast.makeText(this, "You need to allow access to internal storage", 1).show();
                    return;
                } else {
                    openTakePhoto();
                    return;
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                return;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode == -1) {
                    showPhotoCropper(Uri.fromFile(new File(this.currentPhotoPath)));
                    return;
                }
                return;
            case 101:
                if (resultCode == -1) {
                    showPhotoCropper(data.getData());
                    return;
                }
                return;
            case REQUEST_CODE_UCROP /*125*/:
                if (resultCode == -1) {
                    openImage(Uri.fromFile(new File(this.mPhotoFile)));
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void openImage(Uri uri) {
        try {
            this.currentOpenedImageBytes = ImageHelper.getCorrectRotatedBitmap(this, uri);
            Bitmap bitmap = BitmapFactory.decodeByteArray(this.currentOpenedImageBytes, 0, this.currentOpenedImageBytes.length);
            this.profilePicture.setImageBitmap(bitmap);
            this.currentChosenBitmap = bitmap;
            this.save.setVisibility(0);
        } catch (IOException e) {
            Log.e("PROFILE_ACTIVITY", "Error opening image");
        }
    }

    public void showPhotoCropper(Uri selectedImage) {
        try {
            this.mPhotoFile = this.mCacheDir + Long.valueOf(System.currentTimeMillis() / 1000) + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + TMP_PHOTO_FILENAME;
            createTmpImageFile(this.mPhotoFile, getContentResolver().openInputStream(selectedImage));
            Options options = new Options();
            options.setCompressionFormat(CompressFormat.JPEG);
            options.setCompressionQuality(95);
            options.setStatusBarColor(getResources().getColor(R.color.tab3));
            options.setToolbarColor(getResources().getColor(R.color.tab3));
            options.setHideBottomControls(true);
            options.setFreeStyleCropEnabled(false);
            options.setOvalDimmedLayer(true);
            options.setShowCropGrid(false);
            options.setShowCropFrame(false);
            options.setCropPadding(getResources().getDimensionPixelSize(R.dimen.avatar_crop_padding_size));
            options.withAspectRatio(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            options.withMaxResultSize(350, 350);
            openPhotoCropper(options);
        } catch (FileNotFoundException exception) {
            Crashlytics.log(4, "PHOTO_CROPPER", exception.getMessage());
        }
    }

    private void openPhotoCropper(Options options) {
        UCrop uCrop = UCrop.of(Uri.fromFile(new File(this.mPhotoFile)), Uri.fromFile(new File(this.mPhotoFile)));
        uCrop.withOptions(options);
        uCrop.start((Activity) this, (int) REQUEST_CODE_UCROP);
    }

    private void takePhoto() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            openTakePhoto();
            return;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, REQUEST_CODE_ASK_PERMISSIONS);
    }

    private void openTakePhoto() {
        File photoFile = null;
        try {
            photoFile = ImageHelper.createImageFile();
        } catch (IOException ex) {
            ex.printStackTrace();
            Toast.makeText(this, ex.getMessage(), 1).show();
        }
        if (photoFile != null) {
            this.currentPhotoPath = photoFile.getAbsolutePath();
            Intent intent = ImageHelper.getTakePictureIntent(photoFile);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, 100);
            }
        }
    }

    private void choosePhoto() {
        Intent intent = ImageHelper.getChoosePictureIntent();
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 101);
        }
    }

    @OnClick({2131624146})
    void showPickerDialog() {
        CharSequence[] items = new CharSequence[]{getString(R.string.take_photo), getString(R.string.choose_photo)};
        Builder adb = new Builder(this);
        adb.setItems(items, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    ProfileActivity.this.takePhoto();
                } else if (which == 1) {
                    ProfileActivity.this.choosePhoto();
                }
            }
        });
        adb.setNegativeButton(17039360, null);
        adb.show();
    }

    public static void createTmpImageFile(String filename, @Nullable InputStream is) {
        File file;
        try {
            File f = new File(filename);
            try {
                if (f.exists()) {
                    f.delete();
                }
                if (is != null) {
                    OutputStream out = new FileOutputStream(f);
                    byte[] buf = new byte[102400];
                    while (true) {
                        int len = is.read(buf);
                        if (len <= 0) {
                            break;
                        }
                        out.write(buf, 0, len);
                    }
                    out.close();
                    is.close();
                }
                file = f;
            } catch (IOException e) {
                file = f;
            }
        } catch (IOException e2) {
        }
    }
}
