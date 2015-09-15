package org.hansel.myAlert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

<<<<<<< HEAD
import org.hansel.myAlert.MainActivity;
import org.hansel.myAlert.R;
import org.hansel.myAlert.Utils.ContactRing;
=======
//import org.hansel.myAlert.Utils.ContactRing;
>>>>>>> second_stage
import org.hansel.myAlert.dataBase.RingDAO;
import org.linphone.Contact;
import org.linphone.LinphoneManager;
import org.linphone.LinphoneService;
import org.linphone.LinphoneUtils;
import org.linphone.compatibility.Compatibility;
<<<<<<< HEAD
import org.linphone.core.LinphoneFriend;
import org.linphone.core.PresenceActivityType;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.Version;
import org.linphone.ui.AvatarWithShadow;

import android.content.ContentProviderOperation;
=======
import org.linphone.mediastream.Log;
import org.linphone.mediastream.Version;

>>>>>>> second_stage
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
<<<<<<< HEAD
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
=======
import android.support.v4.app.Fragment;
import android.text.Editable;
>>>>>>> second_stage
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
<<<<<<< HEAD
=======
import android.widget.AdapterView.OnItemClickListener;
>>>>>>> second_stage
import android.widget.AlphabetIndexer;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
<<<<<<< HEAD
import android.widget.AdapterView.OnItemClickListener;

public class EditRingFragment extends Fragment {
	private View view;
	private TextView ok, deleteContacts, addContacts, deleteRing;
	private EditText ringName;
	private CheckBox ringDefault;
	private LayoutInflater inflater;
	private ListView contactsList;
	private ImageView clearSearchField;
	private EditText searchField;
	private boolean isNewRing = true;
	private Ring ring;
	private long ringID;
	private RingDAO ringDao;
=======
import android.widget.Toast;

/**
 * Fragment to create or edit a ring 
 * @author izel
 */
public class EditRingFragment extends Fragment {
	private View view;
	private TextView ok,addContacts, deleteRing;
	private EditText ringName;
	private CheckBox ringNotify;
	private LayoutInflater inflater;
	private ListView contactsList;
	private boolean isNewRing = true;
	private Ring ring;
	private long ringID;	
>>>>>>> second_stage
	private Cursor searchCursor;
	private AlphabetIndexer indexer;
	private List<String> idContacts;

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;		
		ring = null;
		idContacts = new ArrayList<String>();
		
		if (getArguments() != null) {
			if (getArguments().getSerializable("Ring") != null) {
				ring = (Ring) getArguments().getSerializable("Ring");
				isNewRing = false;
				ringID = Long.parseLong(ring.getId());
				
				RingDAO ringDao = new RingDAO(LinphoneService.instance()
						.getApplicationContext());
				
				ringDao.open();
				Cursor c = ringDao.getAllContactsRing(ring.getId());
				
				if(c != null && c.getCount() > 0){
					c.moveToFirst();
<<<<<<< HEAD
					do{
						idContacts.add(c.getString(0));
						c.moveToNext();
					}while(!c.isLast());
=======
					for(int i =0; i< c.getCount(); i++){
						idContacts.add(c.getString(0));
						c.moveToNext();
					}
>>>>>>> second_stage
				}
								
				Log.d("=== Cantidad de contactos en el anillo: " + idContacts.size());
				ringDao.close();
			}			
		}
		
		view = inflater.inflate(R.layout.edit_ring, container, false);
		
		TextView cancel = (TextView) view.findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getFragmentManager().popBackStackImmediate();
			}
		});
		
		ringName = (EditText) view.findViewById(R.id.ringName);
<<<<<<< HEAD
		ringDefault = (CheckBox) view.findViewById(R.id.ringDefault);
=======
		ringNotify = (CheckBox) view.findViewById(R.id.ringNotify);
		
		final float scale = getResources().getDisplayMetrics().density;
		if(scale <= 1.5f){			
			ringNotify.setPadding(ringNotify.getPaddingLeft() + 
					(int)(24.0f * scale),
					ringNotify.getPaddingTop(),
					ringNotify.getPaddingRight(),
					ringNotify.getPaddingBottom());
		}
>>>>>>> second_stage
		
		contactsList = (ListView) view.findViewById(R.id.ringContactsList);        
                              		
		ok = (TextView) view.findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isNewRing) {
					boolean areAllFielsEmpty = true;
<<<<<<< HEAD
					if (ringName != null && ringDefault !=null && !ringName.equals("")) {
						areAllFielsEmpty = false;
					}
					if (areAllFielsEmpty) {
						getFragmentManager().popBackStackImmediate();
						return;					
					}
					createRing();
				} 
				else 
					updateRing();
									
		        //MainActivity.instance().prepareRingsInBackground();		        		       
=======
					if (ringName != null && ringNotify !=null && !ringName.equals("")) {
						areAllFielsEmpty = false;
					}
					if(idContacts == null || idContacts.size() == 0){
						areAllFielsEmpty = true;
					}
						
					if (areAllFielsEmpty) {
						Toast.makeText(getActivity(), R.string.ring_contacts_error, 
								Toast.LENGTH_SHORT).show();
						return;					
					}
					else{
						createRing();
					}
				} 
				else 
					updateRing();											        	        		     
>>>>>>> second_stage
				getFragmentManager().popBackStackImmediate();
			}
		});
				
		// Hack to display keyboard when touching focused edittext on Nexus One
		if (Version.sdkStrictlyBelow(Version.API11_HONEYCOMB_30)) {
			ringName.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					InputMethodManager imm = (InputMethodManager) MainActivity.instance().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
				}
			});
		}
		
		ringName.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, 
					int count) {
				if (ringName.getText().length() > 0 || ringName.getText()
						.length() > 0) {
					ok.setEnabled(true);
				} 
				else {
					ok.setEnabled(false);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, 
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
<<<<<<< HEAD
			
		if (!isNewRing) {
			String rn = ring.getName();			
			Long rd = ring.getAlways();
=======
		
		addContacts = (TextView) view.findViewById(R.id.searchContacts);
		addContacts.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Log.d("=== Contactos actuales: " + idContacts.toString());
				searchNotIncludedContacts();					
			}
		} );
			
		if (!isNewRing) {
			String rn = ring.getName();			
			Long rd = ring.getNotify();
>>>>>>> second_stage
			
			if (rn != null) 
				ringName.setText(rn);
			
			if (rd != null && rd == 1)
<<<<<<< HEAD
				ringDefault.setChecked(true);
			else 
				ringDefault.setChecked(false);
	
			deleteContacts = (TextView) view.findViewById(R.id.deleteContact);
			deleteContacts.setVisibility(View.VISIBLE);
			deleteContacts.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Log.d("=== Eliminar contactos seleccionados");
				}
			} );
			addContacts = (TextView) view.findViewById(R.id.newContact);
			addContacts.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Log.d("=== Adicionar contactos seleccionados");
				}
			} );
=======
				ringNotify.setChecked(true);
			else 
				ringNotify.setChecked(false);
							
>>>>>>> second_stage
			deleteRing = (TextView) view.findViewById(R.id.deleteRing);
			deleteRing.setVisibility(View.VISIBLE);
			deleteRing.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
<<<<<<< HEAD
					Log.d("=== Eliminando anillo");
				}
			} );
		}
		else{
			clearSearchField = (ImageView) view.findViewById(R.id.clearSearchField);
			//clearSearchField.setOnClickListener(this);
			clearSearchField.setVisibility(View.VISIBLE);
			searchField = (EditText) view.findViewById(R.id.searchField);
			searchField.setVisibility(View.VISIBLE);
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
					searchContacts(searchField.getText().toString());
				}
			});
		}
		
		searchContacts("");
		
		contactsList.setOnItemClickListener(new OnItemClickListener() {
			   public void onItemClick(AdapterView<?> adapter, View view,
					     int position, long id) {
				   Log.d("=== Selecciono/deselecciono Contacto");
				   ContactRing contactRing = (ContactRing) adapter.getItemAtPosition(position);		
					if(contactRing.isSelected())
						idContacts.add(contactRing.getContactId());
					else
						idContacts.remove(contactRing.getContactId());			
				}
			 });
		
=======
					Log.d("=== Eliminando anillo " + ring.getName());
					deleteRing();
					getFragmentManager().popBackStackImmediate();
				}
			} );
			searchContacts("");
		}
		else{ 
			MainActivity.instance().prepareContactsInBackground();						
		}	
				
		contactsList.setOnItemClickListener(new OnItemClickListener() {
			 public void onItemClick(AdapterView<?> adapter, View view,
					  int position, long id) {
				 Log.d("=== Selecciono/deselecciono Contacto");
				 Contact contactRing = (Contact) adapter.getItemAtPosition(position);
				 				
				 if(contactRing.isSelected())
					idContacts.add(contactRing.getID());
				else
					idContacts.remove(contactRing.getID());				 			
			}
		});
>>>>>>> second_stage
		ringName.requestFocus();
		
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		if (MainActivity.isInstanciated()) {
			if (getResources().getBoolean(R.bool.show_statusbar_only_on_dialer)) {
				MainActivity.instance().hideStatusBar();
			}
		}
	}

	
	private void createRing() {
        ringID = 0;
<<<<<<< HEAD
        ringDao = new RingDAO(LinphoneService.instance()
        		.getApplicationContext());  
        ringDao.open();
        ringID = ringDao.addRing(ringName.getText().toString(),ringDefault
=======
        RingDAO ringDao = new RingDAO(LinphoneService.instance()
        		.getApplicationContext());  
        ringDao.open();
        ringID = ringDao.addRing(ringName.getText().toString(),ringNotify
>>>>>>> second_stage
        		.isChecked());
        Iterator<String> it = idContacts.iterator();        
        while( it.hasNext() ){        	
        	ringDao.addContactToRing(it.next(),String.valueOf(ringID));
        }
        ringDao.close();
	}
	
	private void updateRing() {
		if (ringName.getText().length() > 0) {
<<<<<<< HEAD
			ringDao = new RingDAO(LinphoneManager.getInstance()
					.getContext());
			ringDao.open();
			ringDao.updateRing(String.valueOf(ring.getId()), ringName.getText()
					.toString(), ringDefault.isChecked());
			
			ringDao.close();
		}
			
	}
	
	private void deleteRing() {
		/*String select = ContactsContract.Data.CONTACT_ID + "=?"; 
		String[] args = new String[] { String.valueOf(contactID) };   
		
        ops.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI) 
    		.withSelection(select, args) 
            .build()
        );
        
        try {
            getActivity().getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
        	e.printStackTrace();
        }*/
=======
			
			if(idContacts != null && idContacts.size() == 0){
				 Toast.makeText(getActivity(), getResources().getString(
						 R.string.no_contacts_ring),Toast.LENGTH_SHORT).show();
				 return;
			}
			
			RingDAO ringDao = new RingDAO(LinphoneManager.getInstance()
					.getContext());
			ringDao.open();
			long result = ringDao.updateRing(String.valueOf(ring.getId()), 
				ringName.getText().toString(),ringNotify.isChecked(),idContacts);
			ringDao.close();
			
			if(result == -1)
				 Toast.makeText(getActivity(), getResources().getString(
						 R.string.ring_not_modified),Toast.LENGTH_SHORT).show();
		}
		else
			Toast.makeText(getActivity(), getResources().getString(
					 R.string.ring_without_name), Toast.LENGTH_SHORT).show();			
	}
	
	private void deleteRing() {
		RingDAO ringDao = new RingDAO(LinphoneManager.getInstance()
				.getContext());
		ringDao.open();
		
		ringDao.deleteRing(ring.getId());
		ringDao.close();
>>>>>>> second_stage
	}

	
	
	private void searchContacts(String search) {
		if(searchCursor != null)
			searchCursor.close();
			
<<<<<<< HEAD
		List<ContactRing> contactsRing = new ArrayList<ContactRing>();
		
		if(isNewRing){
			searchCursor = Compatibility.getContactsCursor(getActivity()
					.getContentResolver(), search);			
		}
		else{
=======
		List<Contact> contactsRing = new ArrayList<Contact>();
		
		if(isNewRing){ //Gets all contacts
			searchCursor = MainActivity.instance().getAllContactsCursor();
		}
		else{ //Gets the ring contacts
>>>>>>> second_stage
			String in = "";
			if(idContacts.size() > 1){	
				Iterator<String> it = idContacts.iterator();
				while(it.hasNext()){
					in += it.next() + ",";
				}			
				in = in.substring(0,in.length()-1);				
			}
<<<<<<< HEAD
=======
			else{
				in = idContacts.get(0);
			}
>>>>>>> second_stage
			searchCursor = Compatibility.getContactsInCursor(getActivity()
					.getContentResolver(), in);
		}
		
		indexer = new AlphabetIndexer(searchCursor, Compatibility
				.getCursorDisplayNameColumnIndex(searchCursor), 
				" ABCDEFGHIJKLMNOPQRSTUVWXYZ");
<<<<<<< HEAD
		ringDao = new RingDAO(LinphoneManager.getInstance()
					.getContext());
		ringDao.open();		
=======
		
>>>>>>> second_stage
		searchCursor.moveToFirst();		
			
		for(int i= 0; i<searchCursor.getCount(); i++){
			Contact c = Compatibility.getContact(getActivity()
				.getContentResolver(), searchCursor, i);
<<<<<<< HEAD
			contactsRing.add(new ContactRing(c.getID(), c.getName(), 
					c.getPhotoUri(), c.getPhoto(), !isNewRing));				
		}
		ringDao.close();		
		contactsList.setAdapter(new RingsContactsListAdapter(contactsRing));
	}
	
=======
			c.setSelected(!isNewRing);
			contactsRing.add(c);					
		}
				
		contactsList.setAdapter(new RingsContactsListAdapter(contactsRing));
	}
	
	private void searchNotIncludedContacts(){
		String notIn = "";
		List<Contact> contactsRing = new ArrayList<Contact>(); 
		
		if(idContacts.size() > 1){	
			Iterator<String> it = idContacts.iterator();
			while(it.hasNext()){
				notIn += it.next() + ",";
			}			
			notIn = notIn.substring(0,notIn.length()-1);
			
			searchCursor = Compatibility.getContactsNotInCursor(getActivity()
					.getContentResolver(), notIn);
			
			searchCursor.moveToFirst();		
			
			for(int i= 0; i<searchCursor.getCount(); i++){
				Contact c = Compatibility.getContact(getActivity()
						.getContentResolver(), searchCursor, i);
				c.setSelected(false);
				contactsRing.add(c);
				/*contactsRing.add(new ContactRing(c.getID(), c.getName(), 
					c.getPhotoUri(), c.getPhoto(), false));	*/			
			}
			
		}
		else{
			searchCursor = MainActivity.instance().getAllContactsCursor();
			contactsRing = MainActivity.instance().getAllContacts();
		}
		
								
		if(searchCursor != null && searchCursor.getCount() > 0){
			indexer = new AlphabetIndexer(searchCursor, Compatibility
					.getCursorDisplayNameColumnIndex(searchCursor), 
					" ABCDEFGHIJKLMNOPQRSTUVWXYZ");						
		}
				
		contactsList.setAdapter(new RingsContactsListAdapter(contactsRing));	
	}
	
	
>>>>>>> second_stage
	
	class RingsContactsListAdapter extends BaseAdapter implements SectionIndexer {
		private int margin;
		private Bitmap bitmapUnknown;
<<<<<<< HEAD
		private List<ContactRing> contactsRing;
		//private Cursor cursor;
		
		RingsContactsListAdapter(List<ContactRing> contacts) {
=======
		private List<Contact> contactsRing;
				
		RingsContactsListAdapter(List<Contact> contacts) {
>>>>>>> second_stage
			this.contactsRing = contacts;				
			margin = LinphoneUtils.pixelsToDpi(getResources(), 10);
			bitmapUnknown = BitmapFactory.decodeResource(getResources(), R.drawable.unknown_small);
		}
		
		public int getCount() {
			//return cursor.getCount();
			if(contactsRing == null)
				return 0;
			return contactsRing.size();
		}

		public Object getItem(int position) {
			if (contactsRing == null || position >= contactsRing.size()) {
				//return Compatibility.getContact(getActivity().getContentResolver(), cursor, position);
				return null;
			} 
			else {
				return contactsRing.get(position);
			}
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
						
			if (convertView != null) {
				holder = (ViewHolder) convertView.getTag();
			} 
			else {
				convertView= inflater.inflate(R.layout.contact_ring_cell, parent, false);					
<<<<<<< HEAD
				holder = new ViewHolder();
				holder.contactName = (TextView) convertView.findViewById(R.id.name);
				holder.contactId = (TextView) convertView.findViewById(R.id.contactId);
				holder.selected = (CheckBox) convertView.findViewById(R.id.chooseFriend);
=======
				holder = new ViewHolder();	
				
				holder.selected = (CheckBox) convertView.findViewById(R.id.chooseFriend);
				holder.contactName = (TextView) convertView.findViewById(R.id.name);
				holder.contactId = (TextView) convertView.findViewById(R.id.contactId);
				
				final float scale = getResources().getDisplayMetrics().density;
				if(scale <= 1.5f){
					Log.d("=== Density : " + scale);
					holder.selected.setPadding(holder.selected.getPaddingLeft() + 
							(int)(26.0f * scale),
							holder.selected.getPaddingTop(),
							holder.selected.getPaddingRight(),
							holder.selected.getPaddingBottom());
				}
>>>>>>> second_stage
				holder.icon = (ImageView) convertView.findViewById(R.id.icon);
				convertView.setTag(holder);
			   
				holder.selected.setOnClickListener(new View.OnClickListener() { 
					public void onClick(View v) {
<<<<<<< HEAD
						Log.d("=== Seleccionado/Deseleccionado Objeto");
						CheckBox cb = (CheckBox) v ; 
						ContactRing cr = (ContactRing)cb.getTag(); 
						cr.setSelected(cb.isChecked());
				        if(cb.isChecked())
				        	idContacts.add(cr.getContactId());
				        else
				        	idContacts.remove(cr.getContactId());				        
=======
						Log.d("=== Seleccionado/Deseleccionado Objeto ===");
						CheckBox cb = (CheckBox) v ; 
						Contact cr = (Contact)cb.getTag(); 
						cr.setSelected(cb.isChecked());						
				        if(cr.isSelected())
				        	idContacts.add(cr.getID());				        
				        else{
				        	idContacts.remove(cr.getID());
				        }
				        Log.d("=== Estado de idContacts: " + idContacts.toString());
>>>>>>> second_stage
					}    
				});
			}
			
<<<<<<< HEAD
			ContactRing cr = contactsRing.get(position);			
			
			holder.contactId.setText(cr.getContactId());
			holder.contactName.setText(cr.getContactName());
=======
			Contact cr = contactsRing.get(position);			
			
			holder.contactId.setText(cr.getID());
			holder.contactName.setText(cr.getName());
>>>>>>> second_stage
			holder.selected.setChecked(cr.isSelected());
			holder.selected.setTag(cr);
			holder.icon.setImageBitmap(bitmapUnknown);
			 
			if (cr.getPhoto() != null) {
				holder.icon.setImageBitmap(cr.getPhoto());
			} 
			else if (cr.getPhotoUri() != null) {
				holder.icon.setImageURI(cr.getPhotoUri());
			} 
			else {
				holder.icon.setImageBitmap(bitmapUnknown);
			}
			
			TextView separator = (TextView) convertView.findViewById(R.id.separator);
			LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.layout);
						
			if (getPositionForSection(getSectionForPosition(position)) != position) {
				separator.setVisibility(View.GONE);
				layout.setPadding(0, margin, 0, margin);
			} 
			else {
				separator.setVisibility(View.VISIBLE);
<<<<<<< HEAD
				separator.setText(String.valueOf(cr.getContactName().charAt(0)));
=======
				separator.setText(String.valueOf(cr.getName().charAt(0)));
>>>>>>> second_stage
				layout.setPadding(0, 0, 0, margin);
			}
			
			return convertView;
		}

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
		private class ViewHolder {			   
			   TextView contactId;
			   TextView contactName;
			   CheckBox selected;
			   ImageView icon;
=======
		public List<String> getSelectedContacts() {
			List<String> selected = new ArrayList<String>();
			Iterator<Contact> it = contactsRing.iterator();
			while(it.hasNext() && it.next().isSelected()){
				selected.add(it.next().getID());
			}
			Log.d("=== Contacsts Selected : " + selected.toString());
			return selected;
		}

		
		private class ViewHolder {
			CheckBox selected;
			ImageView icon;
			TextView contactName;
			TextView contactId;
			   
>>>>>>> second_stage
		}
	}
		
}

