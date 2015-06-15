package com.awesome.wathmal.awesomeapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by wathmal on 6/15/15.
 */
public class DialogViewEvents  extends DialogFragment {
    Context context;
    Date date;

    public DialogViewEvents() {
    }

    @SuppressLint("ValidFragment")
    public DialogViewEvents(Context context) {
        this.context = context;
    }


    @SuppressLint("ValidFragment")
    public DialogViewEvents(Context context, Date date) {
        this.context = context;
        this.date = date;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View viewEvents= inflater.inflate(R.layout.dialog_event_list, null);

        final RecyclerView eventRecyclerView= (RecyclerView)viewEvents.findViewById(R.id.my_recycler_view);
        final DatabaseHandler dh= new DatabaseHandler(this.context);
        List<Event> allEvents = dh.getAllEvents();
        List<Event> events= new ArrayList<Event>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for(int i=0; i< allEvents.size(); i++){
            try {
                if(sdf.parse(sdf.format(allEvents.get(i).get_date())).compareTo(this.date) == 0){
                    events.add(allEvents.get(i));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        ContentAdapter contentAdapter = new ContentAdapter(context, events, DatabaseHandler.TABLE_EVENT);
        eventRecyclerView.setAdapter(contentAdapter);
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(context));


        builder.setView(viewEvents)
                .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DialogViewEvents.this.getDialog().cancel();
                    }
                });



        return builder.create();
    }
}
