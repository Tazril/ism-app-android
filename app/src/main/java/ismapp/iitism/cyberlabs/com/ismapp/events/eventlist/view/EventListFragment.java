package ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ismapp.iitism.cyberlabs.com.ismapp.MainActivity;
import ismapp.iitism.cyberlabs.com.ismapp.R;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.presenter.ClubListPresenterImpl;
import ismapp.iitism.cyberlabs.com.ismapp.club.clublist.view.ClubListAdapter;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.EventListModel;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.presenter.EventListPresenterImp;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.presenter.EventListPresenterInterface;
import ismapp.iitism.cyberlabs.com.ismapp.events.eventlist.provider.EventListProviderImp;
import ismapp.iitism.cyberlabs.com.ismapp.helper.SharedPrefs;
import ismapp.iitism.cyberlabs.com.ismapp.helper.ViewUtils;

public class EventListFragment extends Fragment implements EventListFragmentInterface {
    ProgressDialog progressDialog;
    SharedPrefs sharedPrefs;
    RecyclerView recyclerView;
    List<EventListModel> eventListModels;
    EventListAdapter eventListAdapter;
    EventListPresenterInterface eventListPresenterInterface;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    /*CLUBLIST LAYOUT AND ITEM LAYOUT HAS BEEN REUSED*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_list,container,false);
        ButterKnife.bind(this, view);

        recyclerView = (RecyclerView)view.findViewById(R.id.clubrecycler);

        sharedPrefs = new SharedPrefs(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if(eventListModels==null)
        {
            eventListPresenterInterface = new EventListPresenterImp(EventListFragment.this,new EventListProviderImp());
            eventListPresenterInterface.reqEventList(sharedPrefs.getAccessToken());}
        else
        {
            showProgressBar(false);
            eventListAdapter = new EventListAdapter(eventListModels,getContext(),getActivity());
            recyclerView.setAdapter(eventListAdapter);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).addTitletoBar("Events");
        ((MainActivity)getActivity()).addActionBar();
    }

    @Override
    public void getList(List<EventListModel> eventListModels) {
        this.eventListModels = eventListModels;
        eventListAdapter = new EventListAdapter(eventListModels,getContext(),getActivity());
        recyclerView.setAdapter(eventListAdapter);
    }

    @Override
    public void showMessage(String message) {
        ViewUtils.showToast(getContext(), message);
    }

    @Override
    public void showProgressBar(boolean show) {
        if(show){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }
}
