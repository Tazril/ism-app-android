package ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.provider.RetrofitClubListImpl;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.model.ClubDetails;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.presenter.PresenterImpl;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.presenter.PresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;

public class ClubsFrag extends Fragment implements ClubInterface {

    ClubAdapter clubAdapter;
    PresenterInterface presenterInterface;
    ProgressDialog progressDialog;
    SharedPrefs sharedPrefs;
    AlertDialog alertDialog;
    RecyclerView recyclerView;
    List<ClubDetails> clubDetailsArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_list,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.clubrecycler);
        clubAdapter = new ClubAdapter(getContext(),getFragmentManager(),getActivity());
        sharedPrefs = new SharedPrefs(getContext());
        alertDialog= new AlertDialog.Builder(getContext()).setView(LayoutInflater.from(getContext()).inflate(R.layout.progress_bar,null)).setCancelable(false).create();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if(clubDetailsArrayList ==null)
        {presenterInterface = new PresenterImpl(ClubsFrag.this,new RetrofitClubListImpl());
        presenterInterface.requestclublist(sharedPrefs.getAccessToken());}
        else
        {
            clubAdapter.setdata(clubDetailsArrayList);
            recyclerView.setAdapter(clubAdapter);
        }



        //clubAdapter.setData(clubDetailsArrayList);


      /*  progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);*/



        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).addTitletoBar("Clubs");
        ((MainActivity)getActivity()).addActionBar();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void getlist(List<ClubDetails> clubDetails) {
         // clubDetailsArrayList.clear();
          //Log.i("hello",clubDetails.toString());
          clubDetailsArrayList = clubDetails;
         clubAdapter.setdata(clubDetails);
//           clubAdapter.notifyDataSetChanged();

      recyclerView.setAdapter(clubAdapter);



         }

   @Override
    public void showMessage(String message) {
       //setToast
       Log.e("check", "onFailure: " +message );
    }

    @Override
    public void ShowProgressBar(boolean showw) {
     if(showw){
        // progressDialog.show();
         alertDialog.show();
     }else{
        // progressDialog.dismiss();
         alertDialog.dismiss();
     }
    }



}
