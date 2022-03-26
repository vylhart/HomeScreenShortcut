package com.example.homescreenshortcut;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this::addShortcut);
    }

    private void addShortcut(View view) {

        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        Intent intent = new Intent(this, getClass());
        intent.setAction(Intent.ACTION_MAIN);

        ShortcutInfo info = new ShortcutInfo.Builder(this, "pinned-shortcut")
                .setIntent(intent)
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                .setShortLabel(getString(R.string.app_name))
                .build();

        Intent cbIntent = shortcutManager.createShortcutResultIntent(info);
        PendingIntent scb = PendingIntent.getBroadcast(this, 0, cbIntent, 0);
        shortcutManager.requestPinShortcut(info, scb.getIntentSender());


    }


}