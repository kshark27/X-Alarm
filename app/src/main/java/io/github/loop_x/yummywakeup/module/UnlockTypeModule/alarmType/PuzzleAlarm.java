package io.github.loop_x.yummywakeup.module.UnlockTypeModule.alarmType;

import android.app.Activity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import io.github.loop_x.yummywakeup.R;
import io.github.loop_x.yummywakeup.tools.ToastMaster;
import io.github.loop_x.yummywakeup.view.PuzzleLayout;

public class PuzzleAlarm extends UnlockFragment {

    private PuzzleLayout puzzleLayout;
    private OnAlarmAction mListener;

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            mListener.closeAlarm();
        }
    };

    public PuzzleAlarm() {}

    public static PuzzleAlarm newInstance() {
        return new PuzzleAlarm();
    }

    @Override
    public int getLayoutId() { return R.layout.fragment_unlock_puzzle_alarm;}

    @Override
    public void onViewInitial() {

        puzzleLayout = (PuzzleLayout) findViewById(R.id.rl_puzzle);

        puzzleLayout.setPuzzleListener(new PuzzleLayout.PuzzleListener() {
            @Override
            public void unlockAlarm() {

                ToastMaster.setToast(Toast.makeText(getActivity(),
                        getString(R.string.puzzle_complete),
                        Toast.LENGTH_SHORT));
                ToastMaster.showToast();

                Timer timer = new Timer(true);
                timer.schedule(task, 1200);

            }
        });

    }

    @Override
    public void onRefreshData() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (OnAlarmAction) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.closeAlarm();
        mListener = null;
    }

    @Override
    public boolean checkUnlockAlarm() {
        return false;
    }

}
