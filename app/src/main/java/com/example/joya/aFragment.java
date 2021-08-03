package com.example.joya;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class aFragment extends Fragment {


    RecyclerView subject1;
    RecyclerView subject2;
    RecyclerView subject3;
    RecyclerView subject4;

    TextView viewAll1, viewAll2, hiddenText1, viewAll3, viewAll4;


    String userFromFireBase;
    FirebaseUser UserName;
    DatabaseReference reference;


    public aFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);


        viewAll1 = view.findViewById(R.id.tvViewAll1);
        viewAll2 = view.findViewById(R.id.tvViewAll2);
        viewAll3 = view.findViewById(R.id.tvViewAll3);
        viewAll4 = view.findViewById(R.id.tvViewAll4);


        UserName = FirebaseAuth.getInstance().getCurrentUser();
        userFromFireBase = UserName.getPhoneNumber();

        reference = FirebaseDatabase.getInstance().getReference("Course1User").child(userFromFireBase).child("user");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String currentUser = dataSnapshot.getValue(String.class);

                if (currentUser != null) {
                    if (currentUser.equals(userFromFireBase)) {
                        viewAll1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getContext(), allVideos.class));
                            }
                        });

                        viewAll2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getContext(), allVideos.class));
                            }
                        });

                        viewAll3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getContext(), allVideos.class));
                            }
                        });

                        viewAll4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getContext(), allVideos.class));
                            }
                        });


                    }
                } else {
                    viewAll1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getContext(), CourseEnroll.class));
                        }
                    });

                    viewAll2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getContext(), CourseEnroll.class));
                        }
                    });

                    viewAll3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getContext(), CourseEnroll.class));
                        }
                    });

                    viewAll4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getContext(), CourseEnroll.class));
                        }
                    });


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getContext(), "Database connection Lost", Toast.LENGTH_SHORT).show();

            }
        });


//        reference = FirebaseDatabase.getInstance().getReference("Course1User");
//
//        reference.child(userFromFireBase).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
//
//                DataSnapshot dataSnapshot = task.getResult();
//                 currentUser = String.valueOf(dataSnapshot.child("user").getValue());
//
//                viewAll3.setText(currentUser);
//
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull @org.jetbrains.annotations.NotNull Exception e) {
//                Toast.makeText(getContext(), "Connection Lost", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        viewAll1.setText(userFromFireBase);

//        String hello = hiddenText1.getText().toString();

//        if (hello!=null) {
//            viewAll2.setText(hello);
//        }


//        reference = FirebaseDatabase.getInstance().getReference("Course1User");
//        reference.child(userFromFireBase).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
//
//                DataSnapshot dataSnapshot = task.getResult();
//                currentUser = String.valueOf(dataSnapshot.child("user").getValue());
//
//
//                if (userFromFireBase.equals(currentUser)) {
//
//                    viewAll1.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(getContext(), allVideos.class));
//
//
//                        }
//                    });
//
//                    viewAll2.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(getContext(), allVideos.class));
//
//
//                        }
//                    });
//
//                } else {
//
//                    Toast.makeText(getContext(), "Join A course for a better experience", Toast.LENGTH_SHORT).show();
//
//                    viewAll1.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(getContext(), CourseEnroll.class));
//                        }
//                    });
//
//                    viewAll2.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(getContext(), CourseEnroll.class));
//                        }
//                    });
//
//                }
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull @org.jetbrains.annotations.NotNull Exception e) {
//
//
//                Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//
//            }
//        });


        subject1 = view.findViewById(R.id.rvSubject1);
        subject1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        helperSubject1[] helperSubject = new helperSubject1[]{
                new helperSubject1("Course 1", "xyz", R.drawable.a),
                new helperSubject1("Course 2", "xyz", R.drawable.b),
                new helperSubject1("Course 3", "xyz", R.drawable.c),
                new helperSubject1("Course 4", "xyz", R.drawable.d)

        };

        subject1Adapter mySubject1Adapter = new subject1Adapter(helperSubject, getContext());
        subject1.setAdapter(mySubject1Adapter);


        subject2 = view.findViewById(R.id.rvSubject2);
        subject2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        helperSubject1[] helperSubject2 = new helperSubject1[]{
                new helperSubject1("Course a", "xyz", R.drawable.a),
                new helperSubject1("Course b", "xyz", R.drawable.b),
                new helperSubject1("Course c", "xyz", R.drawable.c),
                new helperSubject1("Course d", "xyz", R.drawable.d)

        };

        subject1Adapter mySubject1Adapter2 = new subject1Adapter(helperSubject2, getContext());
        subject2.setAdapter(mySubject1Adapter2);

        subject3 = view.findViewById(R.id.rvSubject3);
        subject3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        helperSubject1[] helperSubject3 = new helperSubject1[]{
                new helperSubject1("Course a", "xyz", R.drawable.a),
                new helperSubject1("Course b", "xyz", R.drawable.b),
                new helperSubject1("Course c", "xyz", R.drawable.c),
                new helperSubject1("Course d", "xyz", R.drawable.d)

        };

        subject1Adapter mySubject1Adapter3 = new subject1Adapter(helperSubject3, getContext());
        subject3.setAdapter(mySubject1Adapter3);

        subject4 = view.findViewById(R.id.rvSubject4);
        subject4.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        helperSubject1[] helperSubject4 = new helperSubject1[]{
                new helperSubject1("Course a", "xyz", R.drawable.a),
                new helperSubject1("Course b", "xyz", R.drawable.b),
                new helperSubject1("Course c", "xyz", R.drawable.c),
                new helperSubject1("Course d", "xyz", R.drawable.d)

        };

        subject1Adapter mySubject1Adapter4 = new subject1Adapter(helperSubject4, getContext());
        subject4.setAdapter(mySubject1Adapter4);


        return view;
    }
}