package com.xylon.settings.beerbong;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

import com.xylon.settings.R;
import com.xylon.settings.SettingsPreferenceFragment;

public class AppListPreference extends SettingsPreferenceFragment {

    private PreferenceCategory mAppList;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

        addPreferencesFromResource(R.xml.dpi_group_app_list);

        final PreferenceScreen prefSet = getPreferenceScreen();

        mAppList = (PreferenceCategory) prefSet.findPreference("dpi_group_app_list");

        Applications.AppInfo[] items = Applications.getApplicationList(mContext);

        mAppList.removeAll();

        for (int i = 0; i < items.length; i++) {
            Preference pref = new Preference(mContext);
            Applications.AppInfo bAppInfo = items[i];

            pref.setKey(bAppInfo.pack);
            pref.setTitle(bAppInfo.name);
            pref.setIcon(bAppInfo.icon);
            pref.setLayoutResource(R.layout.simple_preference);

            pref.setOnPreferenceClickListener(new OnPreferenceClickListener() {

                public boolean onPreferenceClick(final Preference preference) {
                    Applications.addApplication(mContext, preference.getKey());
                    getActivity().getFragmentManager().popBackStackImmediate();
                    return false;
                }
            });
            mAppList.addPreference(pref);
        }

    }

}
