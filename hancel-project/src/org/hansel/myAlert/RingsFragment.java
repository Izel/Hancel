package org.hansel.myAlert;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;

=======
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.util.Log;
import android.widget.Toast;
>>>>>>> second_stage

import org.hansel.myAlert.dataBase.RingDAO;
import org.linphone.FragmentsAvailable;
import org.linphone.LinphoneManager;
import org.linphone.LinphoneService;
import org.linphone.LinphoneUtils;
<<<<<<< HEAD
import org.linphone.compatibility.Compatibility;
import org.linphone.mediastream.Log;
=======
>>>>>>> second_stage

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
<<<<<<< HEAD
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AlphabetIndexer;
import android.widget.BaseAdapter;
import android.widget.EditText;
=======
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AlphabetIndexer;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
>>>>>>> second_stage
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
<<<<<<< HEAD
import android.widget.AdapterView.OnItemClickListener;
=======

import android.content.DialogInterface;
import android.app.AlertDialog;
/**
 * Fragment to show all rings created.
 * 
 * @author izel
 */
>>>>>>> second_stage

public class RingsFragment extends Fragment implements OnClickListener, OnItemClickListener {

private Handler mHandler = new Handler();
	
	private LayoutInflater mInflater;
	private ListView ringsList;
<<<<<<< HEAD
	private TextView allRings, newRing, noRings;
	private int lastKnownPosition;
	private AlphabetIndexer indexer;
	private boolean editOnClick = true, editConsumed = false;
	private ImageView clearSearchField;
	private EditText searchField;
	private Cursor searchCursor;
=======
	private TextView newRing, noRings, save, selectTxt;
	private int lastKnownPosition;
	private AlphabetIndexer indexer;
	private Cursor searchCursor;
	private HashMap<String,Long> editedRings;
>>>>>>> second_stage

	private static RingsFragment instance;
	
	static final boolean isInstanciated() {
		return instance != null;
	}

	public static final RingsFragment instance() {
		return instance;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
		mInflater = inflater;
        View view = inflater.inflate(R.layout.rings_list, container, false);
<<<<<<< HEAD
               
        noRings = (TextView) view.findViewById(R.id.noRings);
        
        ringsList = (ListView) view.findViewById(R.id.ringsList);        
        ringsList.setOnItemClickListener(this);
        
        allRings = (TextView) view.findViewById(R.id.allRings);
        allRings.setOnClickListener(this);
                  
        newRing = (TextView) view.findViewById(R.id.newRing);
        newRing.setOnClickListener(this);
                                    
		clearSearchField = (ImageView) view.findViewById(R.id.clearSearchField);
		clearSearchField.setOnClickListener(this);
		
		searchField = (EditText) view.findViewById(R.id.searchField);
		searchField.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				searchRings(searchField.getText().toString());
			}
		});
        
=======
        
        editedRings = new HashMap<String,Long>();
        
        noRings = (TextView) view.findViewById(R.id.noRings);  
        selectTxt= (TextView) view.findViewById(R.id.selectTxt);                        
        
        newRing = (TextView) view.findViewById(R.id.newRing);
        newRing.setOnClickListener(this);
        
        save = (TextView) view.findViewById(R.id.ok);
        save.setOnClickListener(this);
                
        ringsList = (ListView) view.findViewById(R.id.ringsList);
		ringsList.setOnItemClickListener(this);
        newRing.setOnClickListener(this);

        changeRingsAdapter();
                                           
>>>>>>> second_stage
		return view;
    }

	@Override
	public void onClick(View v) {
		int id = v.getId();
<<<<<<< HEAD
		
		if (id == R.id.allRings) {
			Log.d("=== allRings");
			if (searchField.getText().toString().length() > 0) {
				searchRings();
			} 
			else {
				//MainActivity.instance().prepareRingsInBackground();
				changeContactsAdapter();
			}
		} 		
		else if (id == R.id.newRing) {
			editConsumed = true;			
			MainActivity.instance().addRing();
		} 
		else if (id == R.id.clearSearchField) {
			searchField.setText("");
		}
	}
	
	private void searchRings() {
		searchRings(searchField.getText().toString());
	}

	
	private void searchRings(String search) {
		if (search == null || search.length() == 0) {
			changeContactsAdapter();
			return;
		}
		
		//changeContactsToggle();
		
		if (searchCursor != null) {
			searchCursor.close();
		}
		RingDAO ringDAO = new RingDAO(LinphoneService.instance().getApplicationContext());
		ringDAO.open();
		searchCursor = ringDAO.getRingsByNameCursor(search);
		//searchCursor = Compatibility.getSIPContactsCursor(getActivity().getContentResolver(), search);-
		indexer = new AlphabetIndexer(searchCursor, 1, " ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		ringsList.setAdapter(new RingsListAdapter(null,searchCursor));		
		ringDAO.close();
	}
	
	private void changeContactsAdapter() {
		//changeContactsToggle();		
=======
					
		if (id == R.id.newRing) {
			MainActivity.instance().addRing();
		} 	
		if(id == R.id.ok){
			updateRings();
            invalidate();
		}
	}
	
	private void changeRingsAdapter() {		
>>>>>>> second_stage
		if (searchCursor != null) {
			searchCursor.close();
		}
		
		RingDAO ringDAO = new RingDAO(LinphoneService.instance().getApplicationContext());
		ringDAO.open();
<<<<<<< HEAD
		Cursor allRingsCursor = ringDAO.getRigsCursor();		
		
		ringsList.setVisibility(View.VISIBLE);
		
		if (allRingsCursor == null || allRingsCursor.getCount() == 0) {
			noRings.setVisibility(View.VISIBLE);
			ringsList.setVisibility(View.GONE);
		} 
		else {
			indexer = new AlphabetIndexer(allRingsCursor, 1, 
					" ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			ringsList.setAdapter(new RingsListAdapter(MainActivity.instance()
					.getAllRings(), allRingsCursor));
			noRings.setVisibility(View.GONE);
			ringsList.setVisibility(View.VISIBLE);
		}		
		ringDAO.close();
	}
	
	/*
	private void changeContactsToggle() {
		if (onlyDisplayLinphoneContacts) {
			allRings.setEnabled(true);
			linphoneContacts.setEnabled(false);
		} else {
			allContacts.setEnabled(false);
			linphoneContacts.setEnabled(true);
		}
	}
	*/
	
	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		Ring ring = (Ring) adapter.getItemAtPosition(position);
		
		if (editOnClick) {
			editConsumed = true;
			Log.d("=== Ring a modificar: " + ring.getName());
			MainActivity.instance().editRing(ring);
		} 		
=======
		Cursor ringsCursor = ringDAO.getRigsCursor();		
		List<Ring> allRings = null;
				
		if(ringsCursor != null && ringsCursor.getCount() > 0){
			allRings = new ArrayList<Ring>();		
			ringsCursor.moveToFirst();
			
			for(int i = 0; i< ringsCursor.getCount(); i++){
				allRings.add(new Ring(ringsCursor.getString(0),ringsCursor
						.getString(1), ringsCursor.getLong(2)));
				ringsCursor.moveToNext();
			}
			
			indexer = new AlphabetIndexer(ringsCursor, 1, 
					" ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			ringsList.setAdapter(new RingsListAdapter(allRings));
			selectTxt.setVisibility(View.VISIBLE);
			noRings.setVisibility(View.GONE);
			ringsList.setVisibility(View.VISIBLE);
			
			ringDAO.close();
		}
		else {
			noRings.setVisibility(View.VISIBLE);
			selectTxt.setVisibility(View.GONE);
			ringsList.setVisibility(View.GONE);
		} 
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		Ring ring = (Ring) adapter.getItemAtPosition(position);
		MainActivity.instance().editRing(ring);
>>>>>>> second_stage
	}
	
	@Override
	public void onResume() {
		instance = this;
<<<<<<< HEAD
		super.onResume();
		
		if (editConsumed) {
			editOnClick = false;
			//sipAddressToAdd = null;
		}
		
		if (MainActivity.isInstanciated()) {
			MainActivity.instance().selectMenu(FragmentsAvailable.RINGS);
			//onlyDisplayLinphoneContacts = MainActivity.instance().isLinphoneContactsPrefered();
=======
		super.onResume();		
			
		if (MainActivity.isInstanciated()) {
			MainActivity.instance().selectMenu(FragmentsAvailable.RINGS);
>>>>>>> second_stage
			
			if (getResources().getBoolean(R.bool.show_statusbar_only_on_dialer)) {
				MainActivity.instance().hideStatusBar();
			}
		}
		
		invalidate();
	}
	
	@Override
	public void onPause() {
		instance = null;
		if (searchCursor != null) {
			searchCursor.close();
		}
		super.onPause();
	}
	
	public void invalidate() {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
<<<<<<< HEAD
				if (searchField != null && searchField.getText().toString().length() > 0) {
					searchRings(searchField.getText().toString());
				} 
				else {
					changeContactsAdapter();
				}
=======
				changeRingsAdapter();
>>>>>>> second_stage
				ringsList.setSelectionFromTop(lastKnownPosition, 0);
			}
		});
	}
<<<<<<< HEAD
=======


	private void updateRings() {
		if (editedRings == null || editedRings.isEmpty())
			return;

		RingDAO ringDao = new RingDAO(LinphoneManager.getInstance()
				.getContext());
		ringDao.open();

		Iterator<String> it = editedRings.keySet().iterator();
		String id= null;
		long result = -1;

		while(it.hasNext()){
			id = it.next();
			result = ringDao.updateNotification(id, editedRings.get(id)==1?true:false);
		}

		ringDao.close();

		if(result == -1){
			Toast.makeText(getActivity(), getResources().getString(
					R.string.ring_not_modified),Toast.LENGTH_LONG).show();
		}
		else{
			Toast.makeText(getActivity(), getResources().getString(
					R.string.rings_save_ok), Toast.LENGTH_LONG).show();
		}
	}
	
>>>>>>> second_stage
	
	class RingsListAdapter extends BaseAdapter implements SectionIndexer {
		private int margin;
		private Bitmap bitmapUnknown;
		private List<Ring> rings;
<<<<<<< HEAD
		private Cursor cursor;
		
		public RingsListAdapter(List<Ring> ringsList, Cursor c) {
			rings = ringsList;
			cursor = c;
			margin = LinphoneUtils.pixelsToDpi(getResources(), 10);
			bitmapUnknown = BitmapFactory.decodeResource(getResources(), R.drawable.unknown_small);
		}
		
		
		public int getCount() {
			return cursor.getCount();
=======
		String idDelete;
				
		public RingsListAdapter(List<Ring> ringsLst) {
			rings = ringsLst;			
			margin = LinphoneUtils.pixelsToDpi(getResources(), 10);
			bitmapUnknown = BitmapFactory.decodeResource(getResources(), 
					R.drawable.community);
			
			ArrayList<View> touchables = new ArrayList<View>();
	        touchables.add(selectTxt);	        
	        ringsList.addTouchables(touchables);
		}
				
		public int getCount() {
			if(rings != null)
				return rings.size();
			return 0;
>>>>>>> second_stage
		}

		public Object getItem(int position) {
			if (rings == null || position >= rings.size()) {
<<<<<<< HEAD
				cursor.moveToFirst();
				if(!cursor.moveToPosition(position))
					return null;
				return new Ring(cursor.getString(0),cursor.getString(1),
						cursor.getLong(2));
			} 
=======
				return null;		
			} 
			
>>>>>>> second_stage
			else {
				return rings.get(position);
			}
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			Ring ring = null;
<<<<<<< HEAD
=======
			ViewHolder holder = new ViewHolder();

>>>>>>> second_stage
			do {
				ring = (Ring) getItem(position);
			} 
			while (ring == null);
			
			if (convertView != null) {
<<<<<<< HEAD
				view = convertView;
			} 
			else {
				view = mInflater.inflate(R.layout.ring_cell, parent, false);
			}
			
=======
				holder = (ViewHolder) convertView.getTag();
			}  
			else {
				view = mInflater.inflate(R.layout.ring_cell, parent, false);
				ring = rings.get(position);

				holder.selected = (CheckBox) view.findViewById(R.id.chooseRing);
				holder.selected.setTag(ring);
				holder.ringName = (TextView) view.findViewById(R.id.name);
				holder.icon = (ImageView) view.findViewById(R.id.icon);
				holder.icon.setImageBitmap(bitmapUnknown);
				holder.iconDelete = (TextView) view.findViewById(R.id.iconDelete);
				holder.iconDelete.setText(ring.getId());
				addListeners(holder);
			}

			ring = rings.get(position);
>>>>>>> second_stage
			TextView name = (TextView) view.findViewById(R.id.name);
			name.setText(ring.getName());
			
			TextView separator = (TextView) view.findViewById(R.id.separator);
			LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);
<<<<<<< HEAD
			Log.d("=== POSITION: "  + position);
=======
			
>>>>>>> second_stage
			if (getPositionForSection(getSectionForPosition(position)) != position) {
				separator.setVisibility(View.GONE);
				layout.setPadding(0, margin, 0, margin);
			} 
			else {
				separator.setVisibility(View.VISIBLE);
				separator.setText(String.valueOf(ring.getName().charAt(0)));
				layout.setPadding(0, 0, 0, margin);
			}
<<<<<<< HEAD
			
			ImageView icon = (ImageView) view.findViewById(R.id.icon);
			icon.setImageBitmap(bitmapUnknown);
						
			return view;
		}

=======
									
			if (ring != null && ring.getNotify() == 1)
				holder.selected.setChecked(true);
			else
				holder.selected.setChecked(false);
				
			return view;
		}

		private void addListeners(ViewHolder holder){
			holder.selected.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					CheckBox cb = (CheckBox) v;
					Ring r = (Ring) cb.getTag();
					if (cb.isChecked())
						r.setNotify(1);
					else
						r.setNotify(0);
					editedRings.put(r.getId(), r.getNotify());
				}
			});

			holder.iconDelete.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					idDelete = String.valueOf(((TextView)v).getText());
					AlertDialog.Builder alt_bld = new AlertDialog.Builder(getActivity());
					alt_bld.setMessage(getResources().getString(R.string.delete_ring_message))
							.setCancelable(false)
							.setPositiveButton(getResources().getString(R.string.delete_ring_yes),
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int id) {
											RingDAO ringDao = new RingDAO(LinphoneManager.getInstance()
													.getContext());
											ringDao.open();
											ringDao.deleteRing(idDelete);
                                            invalidate();
											Toast.makeText(getActivity(), getResources().getString(R.string.ring_delete_ok),
													Toast.LENGTH_SHORT).show();
										}
									})
							.setNegativeButton(getResources().getString(R.string.delete_ring_no),
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int id) {
											dialog.cancel();
										}
									});

					AlertDialog alert = alt_bld.create();
					alert.setTitle(getResources().getString(R.string.delete_ring_dialog_title));
					alert.show();
				}
			});
		}
				
>>>>>>> second_stage
		@Override
		public int getPositionForSection(int section) {
			return indexer.getPositionForSection(section);
		}

		@Override
		public int getSectionForPosition(int position) {
			return indexer.getSectionForPosition(position);
		}

		@Override
		public Object[] getSections() {
			return indexer.getSections();
		}
<<<<<<< HEAD
=======
		
		private class ViewHolder {
			CheckBox selected;
			ImageView icon;
			TextView ringName;
			TextView iconDelete;
			   
		}
>>>>>>> second_stage
	}
}

