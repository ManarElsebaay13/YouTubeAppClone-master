package com.manarelsebaay.youtubeApp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manarelsebaay.youtubeApp.Activities.MainActivity;
import com.manarelsebaay.youtubeApp.Adapters.MessageAdapter;
import com.manarelsebaay.youtubeApp.Model.Message;
import com.manarelsebaay.youtubeApp.R;

import java.util.ArrayList;

public class MessagesFragment extends Fragment {
	
	private RecyclerView rvMessages;
	private MessageAdapter adapter;
	private ArrayList<Message> messages;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.messages_layout, null);
	}
	
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		initialize(view);
		populateMessages();
		adapter.notifyDataSetChanged();
		
	}
	
	
	private void initialize(View view) {
		
		messages = new ArrayList<>();
		
		rvMessages = view.findViewById(R.id.rvMessages);
		rvMessages.setLayoutManager(new LinearLayoutManager(getContext()));
		
		adapter = new MessageAdapter(getContext(), messages);
		rvMessages.setAdapter(adapter);
		
	}
	
	
	private void populateMessages() {
		
		messages.add(new Message("Rohan", "Whats up bro ?", null, "1 day ago", null));
		messages.add(new Message("Joe Rogan", "We 'll have him on the podcast after a few more days.", MainActivity.channelPics[0], "2 days ago", "you"));
		messages.add(new Message("Sam Curran", "Take care", null, "1 week ago", "Sam Curran"));
		
	}
	
}
