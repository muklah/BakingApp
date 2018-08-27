package com.example.user_pc.bakingapp.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class UpdateService extends IntentService {

    public static String INGREDIENTS_LIST ="FROM_ACTIVITY_INGREDIENTS_LIST";
    public static final String UPDATE_WIDGETS = "android.appwidget.action.APPWIDGET_UPDATE2";

    public UpdateService() {
        super("UpdateService");
    }

    public static void startService(Context context, ArrayList<String> ingredientsList) {
        Intent intent = new Intent(context, UpdateService.class);
        intent.putExtra(INGREDIENTS_LIST, ingredientsList);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ArrayList<String> ingredientsList = intent.getExtras().getStringArrayList(INGREDIENTS_LIST);
            handleActionUpdateWidgets(ingredientsList);

        }
    }

    private void handleActionUpdateWidgets(ArrayList<String> ingredientsList) {
        Intent intent = new Intent(UPDATE_WIDGETS);
        intent.setAction(UPDATE_WIDGETS);
        intent.putExtra(INGREDIENTS_LIST, ingredientsList);
        sendBroadcast(intent);
    }

}
