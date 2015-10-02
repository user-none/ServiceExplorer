/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 John Schember <john@nachtimwald.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.nachtimwald.android.serviceexplorer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        if (i == null)
            return;

        String servicePackage = i.getStringExtra("package");
        String serviceClass = i.getStringExtra("name");

        try {
            ServiceInfo serviceInfo = getPackageManager().getServiceInfo(new ComponentName(servicePackage, serviceClass), PackageManager.GET_META_DATA);
            ((TextView) findViewById(R.id.serivceName)).setText(serviceInfo.name);
            ((TextView) findViewById(R.id.servicePackage)).setText(serviceInfo.packageName);
            ((TextView) findViewById(R.id.serviceEnabled)).setText(serviceInfo.enabled ? "Yes" : "No");
            ((TextView) findViewById(R.id.serviceExported)).setText(serviceInfo.exported ? "Yes" : "No");
            ((TextView) findViewById(R.id.serviceProcessName)).setText(serviceInfo.processName);

            ((TextView) findViewById(R.id.appName)).setText(serviceInfo.applicationInfo.name);
            ((TextView) findViewById(R.id.appPermission)).setText(serviceInfo.applicationInfo.permission);
            ((TextView) findViewById(R.id.appSdkVersion)).setText(Integer.toString(serviceInfo.applicationInfo.targetSdkVersion));
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
