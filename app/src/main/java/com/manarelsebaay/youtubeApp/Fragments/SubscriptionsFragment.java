package com.manarelsebaay.youtubeApp.Fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.manarelsebaay.youtubeApp.Activities.MainActivity;
import com.manarelsebaay.youtubeApp.Adapters.ChannelAdapter;
import com.manarelsebaay.youtubeApp.Adapters.VideoAdapter;
import com.manarelsebaay.youtubeApp.Model.Channel;
import com.manarelsebaay.youtubeApp.Model.Video;
import com.manarelsebaay.youtubeApp.R;

import java.util.ArrayList;

public class SubscriptionsFragment extends Fragment {
	
	private RecyclerView rvSubscriptions;
	private RecyclerView rvPosts;
	
	private ChannelAdapter subscribedChannelsAdapter;
	private VideoAdapter videoAdapter;
	
	private ArrayList<Channel> channels;
	private ArrayList<Video> videos;
	
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.subscriptions_layout, null);
	}
	
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		initialize(view);
		populateChannels();
		populateVideos();
		subscribedChannelsAdapter.notifyDataSetChanged();
		videoAdapter.notifyDataSetChanged();
		
	}
	
	
	private void initialize(View view) {
		
		channels = new ArrayList<>();
		
		ImageView imgTheme = view.findViewById(R.id.imgThemeIcon);
		
		if (MainActivity.nightMode) {
			imgTheme.setBackground(getResources().getDrawable(R.drawable.ic_day));
			imgTheme.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
		} else {
			imgTheme.setBackground(getResources().getDrawable(R.drawable.ic_night));
			imgTheme.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#606060")));
		}
		
		
		imgTheme.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//Toggle mode
				MainActivity.nightMode = !MainActivity.nightMode;
				
				//Updating setting
				if (MainActivity.nightMode) {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
				} else {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
				}
				
				getActivity().finish();
				getContext().startActivity(new Intent(getContext(), getActivity().getClass()));
				
			}
		});
		
		rvSubscriptions = view.findViewById(R.id.rvMySubscriptions);
		LinearLayoutManager subscriptionsLayoutManager = new LinearLayoutManager(getContext());
		subscriptionsLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		rvSubscriptions.setLayoutManager(subscriptionsLayoutManager);
		
		subscribedChannelsAdapter = new ChannelAdapter(getContext(), channels);
		rvSubscriptions.setAdapter(subscribedChannelsAdapter);
		
		
		videos = new ArrayList<>();
		
		rvPosts = view.findViewById(R.id.rvSubscriptionPosts);
		rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
		
		videoAdapter = new VideoAdapter(getContext(), videos);
		rvPosts.setAdapter(videoAdapter);
		
	}
	
	
	private void populateChannels() {
		
		channels.add(new Channel("Joe Rogan Experience", MainActivity.channelPics[0], false));
		channels.add(new Channel("MKBHD", MainActivity.channelPics[1], true));
		channels.add(new Channel("The Late Night Show", MainActivity.channelPics[2], true));
		channels.add(new Channel("Jimmy Kimmel", MainActivity.channelPics[3], true));
		channels.add(new Channel("MKBHD", MainActivity.channelPics[1], true));
		channels.add(new Channel("The Late Night Show", MainActivity.channelPics[2], true));
		channels.add(new Channel("Jimmy Kimmel", MainActivity.channelPics[3], true));
		
	}
	
	
	private void populateVideos() {
		videos.add(new Video(MainActivity.channelPics[0], MainActivity.thuumbnailUrls[0], "The Joe Rogan Experience", "JRE", "1m", "5 days ago", "1:10", false));
		videos.add(new Video(MainActivity.channelPics[1], MainActivity.thuumbnailUrls[1], "Elon Musk Interview", "MKBHD", "1.5m", "1 week ago", "29:10", true));
		videos.add(new Video(MainActivity.channelPics[2], MainActivity.thuumbnailUrls[2], "Late Night Show with Jimmy Fallon starring Will Smith", "Late Night Show with Jimmy Fallon", "6m", "3 days ago", "6:00", false));
		videos.add(new Video(MainActivity.channelPics[3], MainActivity.thuumbnailUrls[3], "Late Night Show with Jimmy Kimmel starring Kevin Hart", "Jimmy Kimmel", "23m", "1 day ago", "11:10", true));
	}
	
	
}
