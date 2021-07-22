package com.example.joya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.CDATASection;

import java.util.HashMap;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bntsub=findViewById(R.id.buttonsub);
        final EditText textname=findViewById((R.id.textname));
        final EditText textphn=findViewById((R.id.textphn));
        final EditText textloc=findViewById(R.id.textloc);
        final EditText textnamej=(findViewById(R.id.textnamej));
        final int[] maxid = new int[1];
        final Member member;
        final int[] len = new int[1];
        final int[]lenloc= new int[1];
        final int[]lennam= new int[1];
        final int[]lenjnam=new int[1];
        final int[] i = {0};
        //firebase connection
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("users");
        //

        member=new Member();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid[0] = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final TextView locat=findViewById(R.id.textloc1);
        final TextView namee=findViewById(R.id.textna);
        final TextView phonee=findViewById(R.id.textno);
        final TextView namej=findViewById(R.id.textnaj2);



        bntsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phn=textphn.getText().toString().trim();
                String name=textname.getText().toString().trim();
                String loc=textloc.getText().toString().toLowerCase().trim();
                String namejw=textnamej.getText().toString().trim();

                member.setName(name);
                //member.setPhone(phn);
                member.setLocation(loc);
                member.setJname(namejw);

                len[0] =phn.length();
                lenloc[0]=loc.length();
                lennam[0]=name.length();
                lenjnam[0]=namejw.length();


                if(len[0] !=10){
                    phonee.setAlpha(1);
                }
                else {
                    phonee.setAlpha(0);
                }
                if(lenloc[0]==0){
                    locat.setAlpha(1);
                }
                else{
                    locat.setAlpha(0);
                }
                if(lennam[0]==0){
                    namee.setAlpha(1);
                }
                else {
                    namee.setAlpha(0);
                }
                if(lenjnam[0]==0){
                    namej.setAlpha(1);
                }
                else {
                    namej.setAlpha(0);
                }
                DatabaseReference newref=myRef.child(phn);

                if((len[0] ==10)&&(lenloc[0]!=0)&&(lennam[0]!=0)&&(lenjnam[0]!=0)) {
                    myRef.child(phn).setValue(member);
                    Toast.makeText(getApplicationContext(),"Registered Successfully ",Toast.LENGTH_LONG).show();

                }
            }
        });




    }
}
