package util;

import android.support.v7.widget.RecyclerView;

/**
 * Created by minnehmugo on 11/06/2017.
 */

public interface OnStartDragListener {
//    called when the user begins a 'drag-and-drop'
// interaction with the touchscreen
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
