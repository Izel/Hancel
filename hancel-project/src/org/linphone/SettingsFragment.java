package org.linphone;

/*
SettingsFragment.java
Copyright (C) 2013  Belledonne Communications, Grenoble, France

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import org.hansel.myAlert.MainActivity;
import org.hansel.myAlert.R;
=======
import org.hancel.exceptions.NoInternetException;
import org.hancel.http.HttpUtils;
import org.hansel.myAlert.MainActivity;
import org.hansel.myAlert.R;
import org.hansel.myAlert.Utils.Util;
import org.hansel.myAlert.dataBase.FlipDAO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.linphone.InCallActivity.AcceptCallUpdateDialog;
>>>>>>> second_stage
import org.linphone.LinphoneManager.EcCalibrationListener;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCore.AdaptiveRateAlgorithm;
import org.linphone.core.LinphoneCore.EcCalibratorStatus;
import org.linphone.core.LinphoneCore.MediaEncryption;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreFactory;
import org.linphone.core.LinphoneProxyConfig;
import org.linphone.core.PayloadType;
import org.linphone.core.TunnelConfig;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.Version;
import org.linphone.mediastream.video.capture.hwconf.AndroidCameraConfiguration;
<<<<<<< HEAD
import org.linphone.setup.SetupActivity;
import org.linphone.ui.LedPreference;
import org.linphone.ui.PreferencesListFragment;

import android.content.Intent;
=======
import org.linphone.ui.LedPreference;
import org.linphone.ui.PreferencesListFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
>>>>>>> second_stage
import android.os.Bundle;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
<<<<<<< HEAD
=======
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;
>>>>>>> second_stage

/**
 * @author Sylvain Berfini
 */
public class SettingsFragment extends PreferencesListFragment implements EcCalibrationListener {
<<<<<<< HEAD
	private static final int WIZARD_INTENT = 1;
=======
	//private static final int WIZARD_INTENT = 1;
>>>>>>> second_stage
	private LinphonePreferences mPrefs;
	private Handler mHandler = new Handler();
	private TunnelConfig tunnelConfig;

<<<<<<< HEAD
	public SettingsFragment() {
		super(R.xml.preferences);
=======
	public SettingsFragment() {		
		super(R.xml.settings_preferences);		
>>>>>>> second_stage
		mPrefs = LinphonePreferences.instance();
	}

	@Override
	public void onCreate(Bundle bundle) {
<<<<<<< HEAD
		super.onCreate(bundle);

		// Init the settings page interface
		initSettings();
		setListeners();
		hideSettings();
=======
		getActivity().setTheme(R.style.myTheme_Preference);			
		super.onCreate(bundle);
		// Init the settings page interface
		initSettings();		
		setListeners();
		///hideSettings();		
>>>>>>> second_stage
	}

	// Inits the values or the listener on some settings
	private void initSettings() {
		//Init accounts on Resume instead of on Create to update the account list when coming back from wizard
<<<<<<< HEAD

		initTunnelSettings();
		initAudioSettings();
		initVideoSettings();
		initCallSettings();
		initNetworkSettings();
		initAdvancedSettings();

=======
		initFlipSettings();
		//initTunnelSettings();
		//initAudioSettings();		
		//initVideoSettings();
		//initCallSettings();
		//initNetworkSettings();
		//initAdvancedSettings();
		
>>>>>>> second_stage
		// Add action on About button
		findPreference(getString(R.string.menu_about_key)).setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				if (MainActivity.isInstanciated()) {
					MainActivity.instance().displayAbout();
					return true;
				}
				return false;
			}
		});
<<<<<<< HEAD
		findPreference(getString(R.string.setup_key)).setOnPreferenceClickListener(new OnPreferenceClickListener() {
=======
		
		//Action for Hancel logout
		findPreference(getString(R.string.pref_key_logout)).setOnPreferenceClickListener(new OnPreferenceClickListener() {
		@Override
		public boolean onPreferenceClick(Preference preference) {
			if (MainActivity.isInstanciated()) {
				FragmentManager fm = MainActivity.instance().getSupportFragmentManager();
				DialogFragment newFragment = LogoutDialogFragment.newInstance(
			            R.string.logout_desc);
			    newFragment.show(getFragmentManager(), "dialog");
				return true;
			}
			return false;
		}
	});
		/*findPreference(getString(R.string.setup_key)).setOnPreferenceClickListener(new OnPreferenceClickListener() {
>>>>>>> second_stage
			@Override
			public boolean onPreferenceClick(Preference preference) {
				Intent intent = new Intent(LinphoneService.instance(), SetupActivity.class);
	        	startActivityForResult(intent, WIZARD_INTENT);
	        	return true;
			}
<<<<<<< HEAD
		});
=======
		});*/
>>>>>>> second_stage
	}

	// Sets listener for each preference to update the matching value in linphonecore
	private void setListeners() {
<<<<<<< HEAD
		setTunnelPreferencesListener();
		setAudioPreferencesListener();
		setVideoPreferencesListener();
		setCallPreferencesListener();
		setNetworkPreferencesListener();
		setAdvancedPreferencesListener();
=======
		//setTunnelPreferencesListener();
		setFlipPreferencesListener();
		//setAudioPreferencesListener();
		//setVideoPreferencesListener();
		//setCallPreferencesListener();
		//setNetworkPreferencesListener();
		//setAdvancedPreferencesListener();
>>>>>>> second_stage
	}

	// Read the values set in resources and hides the settings accordingly
	private void hideSettings() {
		if (!getResources().getBoolean(R.bool.display_about_in_settings)) {
			hidePreference(R.string.menu_about_key);
		}

		if (getResources().getBoolean(R.bool.hide_accounts)) {
			emptyAndHidePreference(R.string.pref_sipaccounts_key);
		}

		if (getResources().getBoolean(R.bool.hide_wizard)) {
			hidePreference(R.string.setup_key);
		}

		if (getResources().getBoolean(R.bool.disable_animations)) {
			uncheckAndHidePreference(R.string.pref_animation_enable_key);
		}

		if (!getResources().getBoolean(R.bool.enable_linphone_friends)) {
			emptyAndHidePreference(R.string.pref_linphone_friend_key);
		}

		if (getResources().getBoolean(R.bool.disable_chat)) {
			findPreference(getString(R.string.pref_image_sharing_server_key)).setLayoutResource(R.layout.hidden);
		}

		if (!getResources().getBoolean(R.bool.enable_push_id)) {
			hidePreference(R.string.pref_push_notification_key);
		}

		if (!Version.isVideoCapable() || !LinphoneManager.getLcIfManagerNotDestroyedOrNull().isVideoSupported()) {
			uncheckAndHidePreference(R.string.pref_video_enable_key);
<<<<<<< HEAD
		} else {
=======
		} 
		else {
>>>>>>> second_stage
			if (!AndroidCameraConfiguration.hasFrontCamera()) {
				uncheckAndHidePreference(R.string.pref_video_use_front_camera_key);
			}
		}

		if (!LinphoneManager.getLc().isTunnelAvailable()) {
			emptyAndHidePreference(R.string.pref_tunnel_key);
		}

		if (getResources().getBoolean(R.bool.hide_camera_settings)) {
			emptyAndHidePreference(R.string.pref_video_key);
			hidePreference(R.string.pref_video_enable_key);
		}

		if (getResources().getBoolean(R.bool.disable_every_log)) {
			uncheckAndHidePreference(R.string.pref_debug_key);
		}

		if (!LinphoneManager.getLc().upnpAvailable()) {
			uncheckAndHidePreference(R.string.pref_upnp_enable_key);
		}
	}

	private void uncheckAndHidePreference(int preferenceKey) {
		Preference preference = findPreference(getString(preferenceKey));
		if (!(preference instanceof CheckBoxPreference))
			return;

		CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
		checkBoxPreference.setChecked(false);
		hidePreference(checkBoxPreference);
	}

	private void emptyAndHidePreference(int preferenceKey) {
		Preference preference = findPreference(getString(preferenceKey));
		if (preference instanceof PreferenceCategory)
			emptyAndHidePreferenceCategory(preferenceKey);
		else if (preference instanceof PreferenceScreen)
			emptyAndHidePreferenceScreen(preferenceKey);
	}

	private void emptyAndHidePreferenceCategory(int preferenceKey) {
		Preference preference = findPreference(getString(preferenceKey));
		if (!(preference instanceof PreferenceCategory))
			return;

		PreferenceCategory preferenceCategory = (PreferenceCategory) preference;
		preferenceCategory.removeAll();
		hidePreference(preferenceCategory);
	}

	private void emptyAndHidePreferenceScreen(int preferenceKey) {
		Preference preference = findPreference(getString(preferenceKey));
		if (!(preference instanceof PreferenceScreen))
			return;

		PreferenceScreen preferenceScreen = (PreferenceScreen) preference;
		preferenceScreen.removeAll();
		hidePreference(preferenceScreen);
	}

	private void hidePreference(int preferenceKey) {
		hidePreference(findPreference(getString(preferenceKey)));
	}

	private void hidePreference(Preference preference) {
		preference.setLayoutResource(R.layout.hidden);
	}

	private void setPreferenceDefaultValueAndSummary(int pref, String value) {
		if(value != null) {
			EditTextPreference etPref = (EditTextPreference) findPreference(getString(pref));
<<<<<<< HEAD
			etPref.setText(value);
			etPref.setSummary(value);
=======
			if(etPref != null){
				etPref.setText(value);
				etPref.setSummary(value);
			}
>>>>>>> second_stage
		}
	}

	private void initTunnelSettings() {
		setPreferenceDefaultValueAndSummary(R.string.pref_tunnel_host_key, mPrefs.getTunnelHost());
		setPreferenceDefaultValueAndSummary(R.string.pref_tunnel_port_key, String.valueOf(mPrefs.getTunnelPort()));
		ListPreference tunnelModePref = (ListPreference) findPreference(getString(R.string.pref_tunnel_mode_key));
		String tunnelMode = mPrefs.getTunnelMode();
<<<<<<< HEAD
		tunnelModePref.setSummary(tunnelMode);
		tunnelModePref.setValue(tunnelMode);
	}

=======
		
		if(tunnelModePref != null){
			tunnelModePref.setSummary(tunnelMode);
			tunnelModePref.setValue(tunnelMode);
		}
	}
	
	private void initFlipSettings() {
		setPreferenceDefaultValueAndSummary(R.string.pref_journalists_code_key,"");	
		/*setPreferenceDefaultValueAndSummary(R.string.pref_journalists_survey_key,
				getString(R.string.pref_journalists_survey_value));	*/
	}

	private void setFlipPreferencesListener() {
		findPreference(getString(R.string.pref_journalists_code_key))
			.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				ActivationCodeTask activationTask = new ActivationCodeTask();
				activationTask.execute(newValue.toString());				
				return true;				
			}
		});
	}
	
	

>>>>>>> second_stage
	private void setTunnelPreferencesListener() {
		findPreference(getString(R.string.pref_tunnel_host_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				String host = newValue.toString();
				mPrefs.setTunnelHost(host);
				preference.setSummary(host);
				return true;
			}
		});
		findPreference(getString(R.string.pref_tunnel_port_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				try {
					int port = Integer.parseInt(newValue.toString());
					mPrefs.setTunnelPort(port);
					preference.setSummary(String.valueOf(port));
					return true;
				} catch (NumberFormatException nfe) {
					return false;
				}
			}
		});
		findPreference(getString(R.string.pref_tunnel_mode_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				String mode = newValue.toString();
				mPrefs.setTunnelMode(mode);
				preference.setSummary(mode);
				return true;
			}
		});
	}

	private void initAccounts() {
		PreferenceCategory accounts = (PreferenceCategory) findPreference(getString(R.string.pref_sipaccounts_key));
		accounts.removeAll();

		// Get already configured extra accounts
		int defaultAccountID = mPrefs.getDefaultAccountIndex();
		int nbAccounts = mPrefs.getAccountCount();
		for (int i = 0; i < nbAccounts; i++) {
			final int accountId = i;
			// For each, add menus to configure it
			String username = mPrefs.getAccountUsername(accountId);
			String domain = mPrefs.getAccountDomain(accountId);
			LedPreference account = new LedPreference(LinphoneService.instance());

			if (username == null) {
				account.setTitle(getString(R.string.pref_sipaccount));
<<<<<<< HEAD
			} else {
=======
			} 
			else {
>>>>>>> second_stage
				account.setTitle(username + "@" + domain);
			}

			if (defaultAccountID == i) {
				account.setSummary(R.string.default_account_flag);
			}

			account.setOnPreferenceClickListener(new OnPreferenceClickListener()
			{
				public boolean onPreferenceClick(Preference preference) {
					MainActivity.instance().displayAccountSettings(accountId);
					return false;
				}
			});
			updateAccountLed(account, username, domain, mPrefs.isAccountEnabled(i));
			accounts.addPreference(account);
		}
	}

	private void updateAccountLed(final LedPreference me, final String username, final String domain, boolean enabled) {
		if (!enabled) {
			me.setLed(R.drawable.led_disconnected);
			return;
		}

		if (LinphoneManager.getLcIfManagerNotDestroyedOrNull() != null) {
			for (LinphoneProxyConfig lpc : LinphoneManager.getLc().getProxyConfigList()) {
				LinphoneAddress addr = null;
				try {
					addr = LinphoneCoreFactory.instance().createLinphoneAddress(lpc.getIdentity());
				} catch (LinphoneCoreException e) {
					me.setLed(R.drawable.led_disconnected);
					return;
				}
				if (addr.getUserName().equals(username) && addr.getDomain().equals(domain)) {
					if (lpc.getState() == LinphoneCore.RegistrationState.RegistrationOk) {
						me.setLed(R.drawable.led_connected);
					} else if (lpc.getState() == LinphoneCore.RegistrationState.RegistrationFailed) {
						me.setLed(R.drawable.led_error);
					} else if (lpc.getState() == LinphoneCore.RegistrationState.RegistrationProgress) {
						me.setLed(R.drawable.led_inprogress);
						mHandler.postDelayed(new Runnable() {
							@Override
							public void run() {
								updateAccountLed(me, username, domain, true);
							}
						}, 500);
					} else {
						me.setLed(R.drawable.led_disconnected);
					}
					break;
				}
			}
		}
	}

	private void initMediaEncryptionPreference(ListPreference pref) {
		List<CharSequence> entries = new ArrayList<CharSequence>();
		List<CharSequence> values = new ArrayList<CharSequence>();
		entries.add(getString(R.string.media_encryption_none));
		values.add(getString(R.string.pref_media_encryption_key_none));

		LinphoneCore lc = LinphoneManager.getLcIfManagerNotDestroyedOrNull();
		if (lc == null || getResources().getBoolean(R.bool.disable_all_security_features_for_markets)) {
			setListPreferenceValues(pref, entries, values);
			return;
		}

		boolean hasZrtp = lc.mediaEncryptionSupported(MediaEncryption.ZRTP);
		boolean hasSrtp = lc.mediaEncryptionSupported(MediaEncryption.SRTP);
		if (!hasSrtp && !hasZrtp) {
			pref.setEnabled(false);
<<<<<<< HEAD
		} else {
=======
		} 
		else {
>>>>>>> second_stage
			if (hasSrtp){
				entries.add(getString(R.string.media_encryption_srtp));
				values.add(getString(R.string.pref_media_encryption_key_srtp));
			}
			if (hasZrtp){
				entries.add(getString(R.string.media_encryption_zrtp));
				values.add(getString(R.string.pref_media_encryption_key_zrtp));
			}
			setListPreferenceValues(pref, entries, values);
		}

		MediaEncryption value = mPrefs.getMediaEncryption();
		pref.setSummary(value.toString());

		String key = getString(R.string.pref_media_encryption_key_none);
		if (value.toString().equals(getString(R.string.media_encryption_srtp)))
			key = getString(R.string.pref_media_encryption_key_srtp);
		else if (value.toString().equals(getString(R.string.media_encryption_zrtp)))
			key = getString(R.string.pref_media_encryption_key_zrtp);
		pref.setValue(key);
	}

	private void initializePreferredVideoSizePreferences(ListPreference pref) {
		List<CharSequence> entries = new ArrayList<CharSequence>();
		List<CharSequence> values = new ArrayList<CharSequence>();
		for (String name : LinphoneManager.getLc().getSupportedVideoSizes()) {
			entries.add(name);
			values.add(name);
		}

		setListPreferenceValues(pref, entries, values);

		String value = mPrefs.getPreferredVideoSize();
<<<<<<< HEAD
		pref.setSummary(value);
		pref.setValue(value);
=======
		if(pref != null){
			pref.setSummary(value);
			pref.setValue(value);
		}
>>>>>>> second_stage
	}

	private static void setListPreferenceValues(ListPreference pref, List<CharSequence> entries, List<CharSequence> values) {
		CharSequence[] contents = new CharSequence[entries.size()];
		entries.toArray(contents);
<<<<<<< HEAD
		pref.setEntries(contents);
		contents = new CharSequence[values.size()];
		values.toArray(contents);
		pref.setEntryValues(contents);
=======
		if(pref != null){
			pref.setEntries(contents);
			contents = new CharSequence[values.size()];
			values.toArray(contents);
			pref.setEntryValues(contents);
		}
>>>>>>> second_stage
	}

	private void initAudioSettings() {
		PreferenceCategory codecs = (PreferenceCategory) findPreference(getString(R.string.pref_codecs_key));
<<<<<<< HEAD
=======
		if(codecs == null)
			return;
		
>>>>>>> second_stage
		codecs.removeAll();

		LinphoneCore lc = LinphoneManager.getLcIfManagerNotDestroyedOrNull();
		for (final PayloadType pt : lc.getAudioCodecs()) {
			CheckBoxPreference codec = new CheckBoxPreference(LinphoneService.instance());
			codec.setTitle(pt.getMime());
			codec.setSummary(pt.getRate() + " Hz");
			codec.setChecked(lc.isPayloadTypeEnabled(pt));

			codec.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					boolean enable = (Boolean) newValue;
					try {
						LinphoneManager.getLcIfManagerNotDestroyedOrNull().enablePayloadType(pt, enable);
					} catch (LinphoneCoreException e) {
						e.printStackTrace();
					}
					return true;
				}
			});

			codecs.addPreference(codec);
		}

		CheckBoxPreference echoCancellation = (CheckBoxPreference) findPreference(getString(R.string.pref_echo_cancellation_key));
		echoCancellation.setChecked(mPrefs.isEchoCancellationEnabled());

		if (mPrefs.isEchoCancellationEnabled()) {
			Preference echoCalibration = findPreference(getString(R.string.pref_echo_canceller_calibration_key));
			echoCalibration.setSummary(String.format(getString(R.string.ec_calibrated), mPrefs.getEchoCalibration()));
		}

		CheckBoxPreference adaptiveRateControl = (CheckBoxPreference) findPreference(getString(R.string.pref_adaptive_rate_control_key));
		adaptiveRateControl.setChecked(mPrefs.isAdaptiveRateControlEnabled());

		ListPreference adaptiveRateAlgorithm = (ListPreference) findPreference(getString(R.string.pref_adaptive_rate_algorithm_key));
		adaptiveRateAlgorithm.setSummary(String.valueOf(mPrefs.getAdaptiveRateAlgorithm()));
		adaptiveRateAlgorithm.setValue(String.valueOf(mPrefs.getAdaptiveRateAlgorithm()));

		ListPreference bitrateLimit = (ListPreference) findPreference(getString(R.string.pref_codec_bitrate_limit_key));
		bitrateLimit.setSummary(String.valueOf(mPrefs.getCodecBitrateLimit()));
		bitrateLimit.setValue(String.valueOf(mPrefs.getCodecBitrateLimit()));
	}
<<<<<<< HEAD

=======
	
	
>>>>>>> second_stage
	private void setAudioPreferencesListener() {
		findPreference(getString(R.string.pref_echo_cancellation_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean enabled = (Boolean) newValue;
				mPrefs.setEchoCancellation(enabled);
				return true;
			}
		});

		findPreference(getString(R.string.pref_adaptive_rate_control_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean enabled = (Boolean) newValue;
				mPrefs.enableAdaptiveRateControl(enabled);
				return true;
			}
		});

		findPreference(getString(R.string.pref_adaptive_rate_algorithm_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				ListPreference listPreference = (ListPreference) preference;
				mPrefs.setAdaptiveRateAlgorithm(AdaptiveRateAlgorithm.fromString((String)newValue));
				preference.setSummary(String.valueOf(mPrefs.getAdaptiveRateAlgorithm()));
				return true;
			}
		});


		findPreference(getString(R.string.pref_codec_bitrate_limit_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				mPrefs.setCodecBitrateLimit(Integer.parseInt(newValue.toString()));
				LinphoneCore lc = LinphoneManager.getLcIfManagerNotDestroyedOrNull();
				int bitrate=Integer.parseInt(newValue.toString());

				for (final PayloadType pt : lc.getAudioCodecs()) {
					if(lc.payloadTypeIsVbr(pt)){
						lc.setPayloadTypeBitrate(pt, bitrate);
					}
				}

				preference.setSummary(String.valueOf(mPrefs.getCodecBitrateLimit()));
				return true;
			}
		});

		findPreference(getString(R.string.pref_echo_canceller_calibration_key)).setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				synchronized (SettingsFragment.this) {
					try {
						LinphoneManager.getInstance().startEcCalibration(SettingsFragment.this);
						preference.setSummary(R.string.ec_calibrating);
					} catch (LinphoneCoreException e) {
						Log.w(e, "Cannot calibrate EC");
					}
				}
				return true;
			}
		});
	}

	private void initVideoSettings() {
		initializePreferredVideoSizePreferences((ListPreference) findPreference(getString(R.string.pref_preferred_video_size_key)));

		PreferenceCategory codecs = (PreferenceCategory) findPreference(getString(R.string.pref_video_codecs_key));
<<<<<<< HEAD
=======
		
		if(codecs == null)
			return;
		
>>>>>>> second_stage
		codecs.removeAll();

		LinphoneCore lc = LinphoneManager.getLcIfManagerNotDestroyedOrNull();
		for (final PayloadType pt : lc.getVideoCodecs()) {
			CheckBoxPreference codec = new CheckBoxPreference(LinphoneService.instance());
			codec.setTitle(pt.getMime());

			if (!pt.getMime().equals("VP8")) {
				if (getResources().getBoolean(R.bool.disable_all_patented_codecs_for_markets)) {
					continue;
				} else {
					if (!Version.hasFastCpuWithAsmOptim() && pt.getMime().equals("H264"))
					{
						// Android without neon doesn't support H264
						Log.w("CPU does not have asm optimisations available, disabling H264");
						continue;
					}
				}
			}
			codec.setChecked(lc.isPayloadTypeEnabled(pt));

			codec.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					boolean enable = (Boolean) newValue;
					try {
						LinphoneManager.getLcIfManagerNotDestroyedOrNull().enablePayloadType(pt, enable);
					} catch (LinphoneCoreException e) {
						e.printStackTrace();
					}
					return true;
				}
			});

			codecs.addPreference(codec);
		}

		((CheckBoxPreference) findPreference(getString(R.string.pref_video_enable_key))).setChecked(mPrefs.isVideoEnabled());
		((CheckBoxPreference) findPreference(getString(R.string.pref_video_use_front_camera_key))).setChecked(mPrefs.useFrontCam());
		((CheckBoxPreference) findPreference(getString(R.string.pref_video_initiate_call_with_video_key))).setChecked(mPrefs.shouldInitiateVideoCall());
		//((CheckBoxPreference) findPreference(getString(R.string.pref_video_automatically_share_my_video_key))).setChecked(mPrefs.shouldAutomaticallyShareMyVideo());
		((CheckBoxPreference) findPreference(getString(R.string.pref_video_automatically_accept_video_key))).setChecked(mPrefs.shouldAutomaticallyAcceptVideoRequests());
	}

	private void setVideoPreferencesListener() {
		findPreference(getString(R.string.pref_video_enable_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean enable = (Boolean) newValue;
				mPrefs.enableVideo(enable);
				return true;
			}
		});

		findPreference(getString(R.string.pref_video_use_front_camera_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean enable = (Boolean) newValue;
				mPrefs.setFrontCamAsDefault(enable);
				return true;
			}
		});

		findPreference(getString(R.string.pref_video_initiate_call_with_video_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean enable = (Boolean) newValue;
				mPrefs.setInitiateVideoCall(enable);
				return true;
			}
		});

		/*
		findPreference(getString(R.string.pref_video_automatically_share_my_video_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean enable = (Boolean) newValue;
				mPrefs.setAutomaticallyShareMyVideo(enable);
				return true;
			}
		});
		*/

		findPreference(getString(R.string.pref_video_automatically_accept_video_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean enable = (Boolean) newValue;
				mPrefs.setAutomaticallyAcceptVideoRequests(enable);
				return true;
			}
		});

		findPreference(getString(R.string.pref_preferred_video_size_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				mPrefs.setPreferredVideoSize(newValue.toString());
				preference.setSummary(mPrefs.getPreferredVideoSize());
				return true;
			}
		});
	}

	private void initCallSettings() {
		CheckBoxPreference rfc2833 = (CheckBoxPreference) findPreference(getString(R.string.pref_rfc2833_dtmf_key));
		CheckBoxPreference sipInfo = (CheckBoxPreference) findPreference(getString(R.string.pref_sipinfo_dtmf_key));
		if (mPrefs.useRfc2833Dtmfs()) {
			rfc2833.setChecked(true);
			sipInfo.setChecked(false);
			sipInfo.setEnabled(false);
		} else if (mPrefs.useSipInfoDtmfs()) {
			sipInfo.setChecked(true);
			rfc2833.setChecked(false);
			rfc2833.setEnabled(false);
		}
	}

	private void setCallPreferencesListener() {
		findPreference(getString(R.string.pref_rfc2833_dtmf_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean use = (Boolean) newValue;
				CheckBoxPreference sipInfo = (CheckBoxPreference) findPreference(getString(R.string.pref_sipinfo_dtmf_key));
				sipInfo.setEnabled(!use);
				sipInfo.setChecked(false);
				mPrefs.sendDtmfsAsRfc2833(use);
				return true;
			}
		});

		findPreference(getString(R.string.pref_sipinfo_dtmf_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean use = (Boolean) newValue;
				CheckBoxPreference rfc2833 = (CheckBoxPreference) findPreference(getString(R.string.pref_rfc2833_dtmf_key));
				rfc2833.setEnabled(!use);
				rfc2833.setChecked(false);
				mPrefs.sendDTMFsAsSipInfo(use);
				return true;
			}
		});
	}

	private void initNetworkSettings() {
		initMediaEncryptionPreference((ListPreference) findPreference(getString(R.string.pref_media_encryption_key)));

		((CheckBoxPreference) findPreference(getString(R.string.pref_wifi_only_key))).setChecked(mPrefs.isWifiOnlyEnabled());

		// Disable UPnP if ICE si enabled, or disable ICE if UPnP is enabled
		CheckBoxPreference ice = (CheckBoxPreference) findPreference(getString(R.string.pref_ice_enable_key));
		CheckBoxPreference upnp = (CheckBoxPreference) findPreference(getString(R.string.pref_upnp_enable_key));
		ice.setChecked(mPrefs.isIceEnabled());
		if (mPrefs.isIceEnabled()) {
			upnp.setEnabled(false);
		} else {
			upnp.setChecked(mPrefs.isUpnpEnabled());
			if (mPrefs.isUpnpEnabled()) {
				ice.setEnabled(false);
			}
		}

		CheckBoxPreference randomPort = (CheckBoxPreference) findPreference(getString(R.string.pref_transport_use_random_ports_key));
		randomPort.setChecked(mPrefs.isUsingRandomPort());

		// Disable sip port choice if port is random
		EditTextPreference sipPort = (EditTextPreference) findPreference(getString(R.string.pref_sip_port_key));
		sipPort.setEnabled(!randomPort.isChecked());
		sipPort.setSummary(mPrefs.getSipPort());
		sipPort.setText(mPrefs.getSipPort());

		EditTextPreference stun = (EditTextPreference) findPreference(getString(R.string.pref_stun_server_key));
		stun.setSummary(mPrefs.getStunServer());
		stun.setText(mPrefs.getStunServer());

		((CheckBoxPreference) findPreference(getString(R.string.pref_push_notification_key))).setChecked(mPrefs.isPushNotificationEnabled());
		((CheckBoxPreference) findPreference(getString(R.string.pref_ipv6_key))).setChecked(mPrefs.isUsingIpv6());
	}

	private void setNetworkPreferencesListener() {
		findPreference(getString(R.string.pref_wifi_only_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				mPrefs.setWifiOnlyEnabled((Boolean) newValue);
				return true;
			}
		});

		findPreference(getString(R.string.pref_stun_server_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				mPrefs.setStunServer(newValue.toString());
				preference.setSummary(newValue.toString());
				return true;
			}
		});

		findPreference(getString(R.string.pref_ice_enable_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				CheckBoxPreference upnp = (CheckBoxPreference) findPreference(getString(R.string.pref_upnp_enable_key));
				boolean value = (Boolean) newValue;
				upnp.setChecked(false);
				upnp.setEnabled(!value);
				mPrefs.setIceEnabled((Boolean) newValue);
				return true;
			}
		});

		findPreference(getString(R.string.pref_upnp_enable_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				CheckBoxPreference ice = (CheckBoxPreference) findPreference(getString(R.string.pref_ice_enable_key));
				boolean value = (Boolean) newValue;
				ice.setChecked(false);
				ice.setEnabled(!value);
				mPrefs.setUpnpEnabled(value);
				return true;
			}
		});

		findPreference(getString(R.string.pref_transport_use_random_ports_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean randomPort = (Boolean) newValue;
				mPrefs.useRandomPort((Boolean) newValue);
				findPreference(getString(R.string.pref_sip_port_key)).setEnabled(!randomPort);
				return true;
			}
		});

		findPreference(getString(R.string.pref_sip_port_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				int port = -1;
				try {
					port = Integer.parseInt(newValue.toString());
				} catch (NumberFormatException nfe) { }

				mPrefs.setSipPort(port);
				preference.setSummary(newValue.toString());
				return true;
			}
		});

		findPreference(getString(R.string.pref_media_encryption_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				String value = newValue.toString();
				MediaEncryption menc = MediaEncryption.None;
				if (value.equals(getString(R.string.pref_media_encryption_key_srtp)))
					menc = MediaEncryption.SRTP;
				else if (value.equals(getString(R.string.pref_media_encryption_key_zrtp)))
					menc = MediaEncryption.ZRTP;
				mPrefs.setMediaEncryption(menc);

				preference.setSummary(mPrefs.getMediaEncryption().toString());
				return true;
			}
		});

		findPreference(getString(R.string.pref_push_notification_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				mPrefs.setPushNotificationEnabled((Boolean) newValue);
				return true;
			}
		});

		findPreference(getString(R.string.pref_ipv6_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				mPrefs.useIpv6((Boolean) newValue);
				return true;
			}
		});
	}

	private void initAdvancedSettings() {
		((CheckBoxPreference)findPreference(getString(R.string.pref_debug_key))).setChecked(mPrefs.isDebugEnabled());
		((CheckBoxPreference)findPreference(getString(R.string.pref_background_mode_key))).setChecked(mPrefs.isBackgroundModeEnabled());
		((CheckBoxPreference)findPreference(getString(R.string.pref_animation_enable_key))).setChecked(mPrefs.areAnimationsEnabled());
		((CheckBoxPreference)findPreference(getString(R.string.pref_autostart_key))).setChecked(mPrefs.isAutoStartEnabled());
		setPreferenceDefaultValueAndSummary(R.string.pref_image_sharing_server_key, mPrefs.getSharingPictureServerUrl());
		setPreferenceDefaultValueAndSummary(R.string.pref_remote_provisioning_key, mPrefs.getRemoteProvisioningUrl());
		setPreferenceDefaultValueAndSummary(R.string.pref_display_name_key, mPrefs.getDefaultDisplayName());
		setPreferenceDefaultValueAndSummary(R.string.pref_user_name_key, mPrefs.getDefaultUsername());
	}

	private void setAdvancedPreferencesListener() {
		findPreference(getString(R.string.pref_debug_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean value = (Boolean) newValue;
				mPrefs.setDebugEnabled(value);
				return true;
			}
		});

		findPreference(getString(R.string.pref_background_mode_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean value = (Boolean) newValue;
				mPrefs.setBackgroundModeEnabled(value);
				return true;
			}
		});

		findPreference(getString(R.string.pref_animation_enable_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean value = (Boolean) newValue;
				mPrefs.setAnimationsEnabled(value);
				return true;
			}
		});

		findPreference(getString(R.string.pref_autostart_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				boolean value = (Boolean) newValue;
				mPrefs.setAutoStart(value);
				return true;
			}
		});

		findPreference(getString(R.string.pref_image_sharing_server_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				String value = (String) newValue;
				mPrefs.setSharingPictureServerUrl(value);
				preference.setSummary(value);
				return true;
			}
		});

		findPreference(getString(R.string.pref_remote_provisioning_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				String value = (String) newValue;
				mPrefs.setRemoteProvisioningUrl(value);
				preference.setSummary(value);
				return true;
			}
		});

		findPreference(getString(R.string.pref_display_name_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				String value = (String) newValue;
				mPrefs.setDefaultDisplayName(value);
				preference.setSummary(value);
				return true;
			}
		});

		findPreference(getString(R.string.pref_user_name_key)).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				String value = (String) newValue;
				mPrefs.setDefaultUsername(value);
				preference.setSummary(value);
				return true;
			}
		});
	}

	@Override
	public void onEcCalibrationStatus(final EcCalibratorStatus status, final int delayMs) {
<<<<<<< HEAD
=======
		//getActivity().setTheme(R.style.Holo_Theme_Light);
>>>>>>> second_stage
		mHandler.post(new Runnable() {
			public void run() {
				CheckBoxPreference echoCancellation = (CheckBoxPreference) findPreference(getString(R.string.pref_echo_cancellation_key));
				Preference echoCancellerCalibration = findPreference(getString(R.string.pref_echo_canceller_calibration_key));

				if (status == EcCalibratorStatus.DoneNoEcho) {
					echoCancellerCalibration.setSummary(R.string.no_echo);
					echoCancellation.setChecked(false);
					LinphonePreferences.instance().setEchoCancellation(false);
				} else if (status == EcCalibratorStatus.Done) {
					echoCancellerCalibration.setSummary(String.format(getString(R.string.ec_calibrated), delayMs));
					echoCancellation.setChecked(true);
					LinphonePreferences.instance().setEchoCancellation(true);
				} else if (status == EcCalibratorStatus.Failed) {
					echoCancellerCalibration.setSummary(R.string.failed);
					echoCancellation.setChecked(true);
					LinphonePreferences.instance().setEchoCancellation(true);
				}
			}
		});
	}

	@Override
	public void onResume() {
<<<<<<< HEAD
		super.onResume();

		initAccounts();
=======
		//getActivity().setTheme(R.style.Holo_Theme);
		super.onResume();

		//initAccounts();
>>>>>>> second_stage

		if (MainActivity.isInstanciated()) {
			MainActivity.instance().selectMenu(FragmentsAvailable.SETTINGS);

			if (getResources().getBoolean(R.bool.show_statusbar_only_on_dialer)) {
				MainActivity.instance().hideStatusBar();
			}
		}
	}
<<<<<<< HEAD
=======
	
	
	public class ActivationCodeTask extends AsyncTask<String, String, String>{
		@Override
		protected String doInBackground(String... params) {
			try{
				JSONArray numbers = null;
				JSONObject response = HttpUtils.sendActivationCode(params[0]);
				String ok = (String)response.get("resultado");
				
				if(!ok.equalsIgnoreCase("OK")){
					return getResources().getString(R.string.registration_fail);				
				}
				else{
					JSONObject desc = (JSONObject)response.get("descripcion");
					numbers = desc.getJSONArray("numbers");						
					if(numbers == null || numbers.length() == 0){	
						return getResources().getString(R.string.registration_empty);
					}
				}				
				FlipDAO flipDao = new FlipDAO(getActivity().getApplicationContext());
				flipDao.open();
				flipDao.createOrUpdateKey(getResources().getString(
					R.string.contacts_flip), numbers.join(","));
				
				return getResources().getString(R.string.registration_ok);				
			}
			catch(JSONException e){
				return getResources().getString(R.string.registration_fail);
			}
			catch (NoInternetException e){
				return getResources().getString(R.string.registration_fail);
			}			
		}
		
		@Override
		protected void onPostExecute(String result){
			Toast.makeText(getActivity().getApplicationContext(), result, 
					Toast.LENGTH_SHORT).show();
		}
	}
	
	public static class LogoutDialogFragment extends DialogFragment {

		public static LogoutDialogFragment newInstance(int title) {
			LogoutDialogFragment frag = new LogoutDialogFragment();
	        Bundle args = new Bundle();
	        args.putInt("title", title);
	        frag.setArguments(args);
	        return frag;
	    }

	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        int title = getArguments().getInt("title");

	        return new AlertDialog.Builder(getActivity())
	                //.setIcon(R.drawable.alert_dialog_icon)
	                .setTitle(title)
	                .setPositiveButton(R.string.logout,
	                    new DialogInterface.OnClickListener() {
	                        public void onClick(DialogInterface dialog, int whichButton) {
	                        	Log.d("Hancel sing out");
	        			    	Util.setLoginOkInPreferences(MainActivity.instance()
	        							.getApplicationContext(), false);											
	        					getActivity().finish();
	                        }
	                    }
	                )
	                .setNegativeButton(R.string.cancel,
	                    new DialogInterface.OnClickListener() {
	                        public void onClick(DialogInterface dialog, int whichButton) {
	                            
	                        }
	                    }
	                )
	                .create();
	    }
	}
/*	
	@SuppressLint("ValidFragment")
	class AcceptLogoutDialog extends DialogFragment {

	    public AcceptLogoutDialog() {
	        // Empty constructor required for DialogFragment
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        View view = inflater.inflate(R.layout.logout_dialog, container);

	        getDialog().setTitle(R.string.logout_title);
	        
	        Button yes = (Button) view.findViewById(R.id.yes);
	        yes.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
			    	Log.d("Hancel sing out");
			    	Util.setLoginOkInPreferences(MainActivity.instance()
							.getApplicationContext(), false);											
					getActivity().finish();
				}
			});
	        
	        
	        
	        Button no = (Button) view.findViewById(R.id.no);
	        no.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
			    	Log.d("No Hancel sing out");
			    	this.dismiss();
			    	
				}
			});
	        
	        return view;
	    }
	}*/
>>>>>>> second_stage
}
