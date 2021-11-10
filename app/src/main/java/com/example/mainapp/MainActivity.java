package com.example.mainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bfr.buddy.network.shared.InternetState;
import com.bfr.buddy.network.shared.NetworkStatus;
import com.bfr.buddy.ui.shared.FacialEvent;
import com.bfr.buddy.usb.shared.IUsbCommadRsp;
import com.bfr.buddy.utils.events.EventItem;
import com.bfr.buddy.utils.events.EventState;
import com.bfr.buddy.utils.events.EventType;
import com.bfr.buddysdk.BuddyActivity;
import com.bfr.buddysdk.BuddySDK;
import com.bfr.testapplication.R;

public class MainActivity extends BuddyActivity {
    TextView mText1;
    float angle = 0;
    EditText angle_Yes; //Editable text to insert an angle value for "Yes" move
    private Switch Enable_switch_yes; //switch to enable motor for "yes" move
    private Switch Enable_switch_no; //switch to enable motor for "no" move

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Different button to launch the different actions of Buddy
        findViewById(R.id.button_yes).setOnClickListener(v -> onButtonYes());
        findViewById(R.id.button_no).setOnClickListener(v -> onButtonNo());
        findViewById(R.id.button_straightYes).setOnClickListener(v -> onButtonStraightYes());
        findViewById(R.id.button_straightNo).setOnClickListener(v -> onButtonStraightNo());//The button allowing Buddy to do a "yes" move
        Enable_switch_yes = findViewById(R.id.Enable_yes); //Linking between xml switch and Enable_switch_no variable
        Enable_switch_no = findViewById(R.id.Enable_no); //Linking between xml switch and Enable_switch_no variable

        angle_Yes = findViewById(R.id.angle_yes);//Linking between xml edit_text for "yes" move angle and angle_Yes variable
        mText1 = (TextView) findViewById(R.id.textView1);
        //Switch to enable or disable the motor
        Enable_switch_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The motor for "no" move is enable
                    BuddySDK.USB.enableNoMove(1, new IUsbCommadRsp.Stub() {
                        @Override
                        //if the motor succeeded to be enabled,we display motor is enabled
                        public void onSuccess(String success) throws RemoteException {
                            Log.i("Motor Yes", "Yes motor Enabled");
                        }

                        @Override
                        //if the motor did not succeed to be enabled,we display motor failed to be enabled
                        public void onFailed(String error) throws RemoteException {
                            Log.i("Motor Yes", "Yes motor Enabled Failed");
                        }
                    });
                } else    // The motor for "no" move is enable
                {
                    BuddySDK.USB.enableNoMove(0, new IUsbCommadRsp.Stub() {
                        @Override
                        //if the motor succeeded to be disabled,we display motor is disabled
                        public void onSuccess(String success) throws RemoteException {
                            Log.i("Motor Yes", "Yes motor Disabled");
                        }

                        @Override
                        //if the motor did not succeed to be disabled,we display motor failed to be disabled
                        public void onFailed(String error) throws RemoteException {
                            Log.i("Motor Yes", "Yes motor Disable Failed");
                        }
                    });
                } // end if checked
            } // end Onchecked callback
        });


        // The motor for "yes" move is enable
        Enable_switch_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The motor for "no" move is enable
                    BuddySDK.USB.enableNoMove(1, new IUsbCommadRsp.Stub() {
                        @Override
                        //if the motor succeeded to be enabled,we display motor is enabled
                        public void onSuccess(String success) throws RemoteException {
                            Log.i("Motor No", "No motor Enabled");
                        }

                        @Override
                        //if the motor did not succeed to be enabled,we display motor failed to be enabled
                        public void onFailed(String error) throws RemoteException {
                            Log.i("Motor No", "No motor Enabled Failed");
                        }
                    });
                } else    // The motor for "no" move is enable
                {
                    BuddySDK.USB.enableNoMove(0, new IUsbCommadRsp.Stub() {
                        @Override
                        //if the motor succeeded to be disabled,we display motor is disabled
                        public void onSuccess(String success) throws RemoteException {
                            Log.i("Motor No", "No motor Disabled");
                        }

                        @Override
                        //if the motor did not succeed to be disabled,we display motor failed to be disabled
                        public void onFailed(String error) throws RemoteException {
                            Log.i("Motor No", "No motor Disable Failed");
                        }
                    });
                } // end if checked
            } // end Onchecked callback
        });


    }




    //button for yes move
    private void onButtonYes() {
        BuddySDK.USB.buddySayYes(10, 20, new IUsbCommadRsp.Stub() { //function with speed, angle and stub callback
            @Override
            public void onSuccess(String success) {
                if (success.equals("YES_MOVE_FINISHED")) { //if function executed well YES_MOVE_FINISHED is sent

                }
            }
            @Override public void onFailed(String error) {}
        });

    }


    //button for no move
    private void onButtonNo() {
        BuddySDK.USB.buddySayNo(10, 20, new IUsbCommadRsp.Stub() { //function with speed, angle and stub callback
            @Override
            public void onSuccess(String success) {
                if (success.equals("NO_MOVE_FINISHED")) {  //if function executed well NO_MOVE_FINISHED is sent

                }
            }
            @Override public void onFailed(String error) {}
        });

    }


    //button for Straight yes move
    private void onButtonStraightYes() {
     BuddySDK.USB.buddySayYesStraight(10, new IUsbCommadRsp.Stub() { //function with speed, angle and stub callback
         @Override
         public void onSuccess(String s) throws RemoteException {
             if (s.equals("YES_MOVE_FINISHED")) {  //if function executed well YES_MOVE_FINISHED is sent

             }

         }

         @Override
         public void onFailed(String s) throws RemoteException {

         }
     });

    }
    //button for no straight move move
    private void onButtonStraightNo() {
        BuddySDK.USB.buddySayNoStraight(10, new IUsbCommadRsp.Stub() { //function with speed, angle and stub callback
            @Override
            public void onSuccess(String s) throws RemoteException {
                if (s.equals("NO_MOVE_FINISHED")) {  //if function executed well NO_MOVE_FINISHED is sent

                }

            }

            @Override
            public void onFailed(String s) throws RemoteException {

            }
        });


    }

    @Override
    public void onSDKReady() {
        BuddySDK.UI.setViewAsFace(findViewById(R.id.view_face));
        BuddySDK.UI.playFacialEvent(FacialEvent.YAWN);

        BuddySDK.USB.enableYesMove(1, new IUsbCommadRsp.Stub() {
            @Override
            public void onSuccess(String success) throws RemoteException {

            }

            @Override
            public void onFailed(String error) throws RemoteException {

            }
        });
    }

    // Catches SPEAKING event.
    // Writes what has been spoken.
    @Override
    public void onEvent(EventItem iEvent) {
        if (iEvent.getEventType() == EventType.SPEAKING) {
            if (iEvent.getEventState() == EventState.START)
                mText1.setText("I am speaking\n\nText: " + iEvent.getMessage());
            else if (iEvent.getEventState() == EventState.END)
                mText1.setText("");
        }
    }
}