package se.lublin.mumla.police;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import se.lublin.mumla.R;
import se.lublin.mumla.Settings;
import se.lublin.mumla.app.MumlaActivity;
import se.lublin.mumla.db.DatabaseProvider;
import se.lublin.mumla.db.MumlaDatabase;
import se.lublin.mumla.db.PublicServer;
import se.lublin.mumla.preference.Preferences;

//added by mkharma
public class PublicServerListHelper {

    //added by mkharma
    public static List<PublicServer> getPoliceServerInfo(DatabaseProvider mDatabaseProvider) {
        List<PublicServer> serverList = new ArrayList<PublicServer>();

        String name = "PCP";
        String ca = "0";
        String continentCode = "PAL";
        String country = "Palestine";
        String countryCode = "972";
        String ip = "82.213.48.71";
        String port = "64738";
        String region = "ME";
        String url = "";

        PublicServer server = new PublicServer(name, ca, continentCode, country, countryCode, ip, Integer.parseInt(port), region, url);
        serverList.add(server);

        return serverList;
    }

    //added by mkharma
    public static void addToFavorite(DatabaseProvider mDatabaseProvider, PublicServer server) {

        //set it as favorite server
        MumlaDatabase database = mDatabaseProvider.getDatabase();
        database.addServer(server);
    }

    //added by mkharma
    public static void showPreferences(final Activity activity) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);

        final Settings settings = Settings.getInstance(activity);

        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Allow username entry
        final EditText passwordField = new EditText(activity);
        passwordField.setHint(activity.getString(R.string.password));
        passwordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        linearLayout.addView(passwordField);

        final TextView messageField = new TextView(activity);
        messageField.setVisibility(View.INVISIBLE);
        linearLayout.addView(messageField);

        alertBuilder.setView(linearLayout);

        alertBuilder.setTitle(R.string.menu_settings);

        alertBuilder.setPositiveButton(R.string.allow, null);
        alertBuilder.setNegativeButton(android.R.string.cancel, null);

        final AlertDialog dialog = alertBuilder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        messageField.setText("");

                        if (passwordField.getText().toString().equals("Epsilon@2021")) {
                            messageField.setVisibility(View.INVISIBLE);
                            Intent prefIntent = new Intent(activity, Preferences.class);
                            activity.startActivity(prefIntent);
                            dialog.dismiss();
                        } else {
                            messageField.setVisibility(View.VISIBLE);
                            messageField.setText(R.string.invalid_password);
                        }
                    }
                });
            }
        });

        dialog.show();
    }
}
