package cst.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity {
    private File imageFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//This part below saves the image
    public void process(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageFile=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test.jpg");
       Uri FirstU=Uri.fromFile(imageFile);//Here is my problem I dont know how to get ride of it.
        intent.putExtra(MediaStore.EXTRA_OUTPUT,FirstU);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, 0);
    }
    @Override//checks where to save the image.
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        if(requestCode==0)
        {
            switch (resultCode){// A check to see if the image file exists
                case Activity.RESULT_OK:
                    if(imageFile.exists())
                    {
                        Toast.makeText(this,"The file was saved at "+imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    }
                    else
                {
                    Toast.makeText(this,"No Save Data", Toast.LENGTH_LONG).show();//Just incase there is no save
                }
                    break;
                case Activity.RESULT_CANCELED:    // this is used just incase the user doesnt want the picture
                 break;
                default:
                    break;
            }
        }
    }
}