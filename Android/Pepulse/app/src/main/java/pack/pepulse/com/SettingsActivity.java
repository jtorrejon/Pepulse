package pack.pepulse.com;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

/**
 * Created by Pepe on 10/02/2015.
 */
public class SettingsActivity extends Activity{

    private EditText phoneNumber, userName, bloodGroup;
    private static String phone = null, name = null, blood = null;
    private Button addButton;
    private static CheckBox checkSMS;
    private static boolean flagSMS = true;
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    private ImageView agenda;
    private static final int CONTACT_PICKER_RESULT = 1001;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);
        phoneNumber = (EditText) findViewById(R.id.text_phone);
        userName = (EditText) findViewById(R.id.text_name);
        bloodGroup = (EditText) findViewById(R.id.text_blood_group);
        addButton = (Button) findViewById(R.id.add_button);
        checkSMS = (CheckBox) findViewById(R.id.checkboxSMS);
        if (phone != null && name != null && blood != null){

            phoneNumber.setHint(phone);
            userName.setHint(name);
            bloodGroup.setHint(blood);
        } else{

            Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG).show();
        }

        checkSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkSMS.isChecked()){

                    flagSMS = true;
                }
            }
        });

        if(flagSMS == false) checkSMS.setChecked(false);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phone = phoneNumber.getText().toString();
                name = userName.getText().toString();
                blood = bloodGroup.getText().toString();
                String addr = DeviceListActivity.getAddress();

                if (phoneNumber.getText().toString() != null && userName.getText().toString() != null && bloodGroup.getText().toString() != null) {
                    Intent i = new Intent(SettingsActivity.this, MainActivity.class);
                    i.putExtra(EXTRA_DEVICE_ADDRESS, addr);
                    startActivity(i);
                } else Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG).show();
            }
        });

        agenda = (ImageView) findViewById(R.id.agendaButton);

        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                contactPickerIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        //Link the activity started with this method
        if(requestCode == CONTACT_PICKER_RESULT){
            //If the activity was started correctly
            if(resultCode == RESULT_OK){

                //Get every contact identification and their data
                Uri getContactUri = data.getData();

                //Only shows phone number contacts
                String [] contact = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                //Cursor to get the phone number chose
                Cursor cursor = getContentResolver().query(getContactUri, contact, null, null, null);
                cursor.moveToFirst();

                //Get the phone number chose
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column);

                //Set in the phone number field, the phone number saved
                phoneNumber.setText(number);
            }
        }
    }

    public static String getPhone(){

        return phone;
    }

    public static String getName(){

        return name;
    }

    public static String getBlood(){

        return blood;
    }

    public static void setFlag(boolean bool){

        flagSMS = bool;
    }

    public static boolean getFlag(){

        return flagSMS;
    }

    public static void changeCheckBox(){

        checkSMS.setChecked(false);
    }



}
